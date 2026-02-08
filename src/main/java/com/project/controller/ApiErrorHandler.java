package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.error.ApiError;
import com.project.error.ApiFieldError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.time.Instant;
import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class ApiErrorHandler {

    private final ObjectMapper objectMapper;

    public ApiErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(RestClientResponseException.class)
    public String handleApiError(RestClientResponseException ex, Model model, HttpServletRequest request) {
        ApiError apiError = parseApiError(ex);
        int status = apiError.getStatus() != null ? apiError.getStatus() : ex.getStatusCode().value();
        String title = titleForStatus(status);
        String userMessage = messageForStatus(status);
        fillModel(model, apiError, title, userMessage, status, request);
        return "error";
    }

    @ExceptionHandler(ResourceAccessException.class)
    public String handleNetworkError(ResourceAccessException ex, Model model, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        fillModel(model, apiError, "Brak połączenia", "Brak połączenia z serwerem.", null, request);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericError(Exception ex, Model model, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        fillModel(model, apiError, "Wystąpił błąd", "Wystąpił błąd aplikacji.", null, request);
        return "error";
    }

    private ApiError parseApiError(RestClientResponseException ex) {
        String body = ex.getResponseBodyAsString();
        if (body != null && !body.isBlank()) {
            try {
                return objectMapper.readValue(body, ApiError.class);
            } catch (Exception ignored) {
                // Fall back to minimal fields.
            }
        }
        ApiError fallback = new ApiError();
        fallback.setStatus(ex.getStatusCode().value());
        fallback.setError(ex.getStatusText());
        fallback.setMessage(ex.getMessage());
        return fallback;
    }

    private void fillModel(Model model,
                           ApiError apiError,
                           String title,
                           String userMessage,
                           Integer statusFallback,
                           HttpServletRequest request) {
        model.addAttribute("errorTitle", title);
        model.addAttribute("userMessage", userMessage);
        List<ApiFieldError> fieldErrors = apiError.getFieldErrors() != null ? apiError.getFieldErrors() : List.of();
        model.addAttribute("fieldErrors", fieldErrors);
        model.addAttribute("technicalDetails", buildTechnicalDetails(apiError, statusFallback, request));
    }

    private ApiError buildTechnicalDetails(ApiError apiError, Integer statusFallback, HttpServletRequest request) {
        ApiError details = new ApiError();
        details.setStatus(apiError.getStatus() != null ? apiError.getStatus() : statusFallback);
        details.setError(apiError.getError());
        details.setMessage(apiError.getMessage());
        details.setPath(apiError.getPath() != null ? apiError.getPath() : request.getRequestURI());
        details.setTimestamp(apiError.getTimestamp() != null ? apiError.getTimestamp() : Instant.now().toString());
        return details;
    }

    private String titleForStatus(int status) {
        return switch (status) {
            case 400 -> "Błąd danych";
            case 401 -> "Brak autoryzacji";
            case 403 -> "Brak dostępu";
            case 404 -> "Nie znaleziono";
            case 409 -> "Konflikt danych";
            case 500 -> "Błąd serwera";
            default -> "Wystąpił błąd";
        };
    }

    private String messageForStatus(int status) {
        return switch (status) {
            case 400 -> "Nieprawidłowe dane. Sprawdź pola formularza.";
            case 401 -> "Brak autoryzacji. Zaloguj się ponownie.";
            case 403 -> "Brak uprawnień do wykonania operacji. Jeśli uważasz, że to błąd, skontaktuj się z administratorem.";
            case 404 -> "Nie znaleziono żądanego zasobu.";
            case 409 -> "Nie można wykonać operacji z powodu powiązanych danych. Usuń/odepnij powiązania i spróbuj ponownie.";
            case 500 -> "Wystąpił błąd serwera.";
            default -> "Wystąpił błąd. Spróbuj ponownie.";
        };
    }
}
