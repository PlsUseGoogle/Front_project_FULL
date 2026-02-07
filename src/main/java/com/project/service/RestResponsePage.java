package com.project.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponsePage<T> extends PageImpl<T> {
    private static final long serialVersionUID = 1L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestResponsePage(@JsonProperty("content") List<T> content,
                            @JsonProperty("page") JsonNode page,
                            @JsonProperty("number") Integer number,
                            @JsonProperty("size") Integer size,
                            @JsonProperty("totalElements") Long totalElements,
                            @JsonProperty("pageable") JsonNode pageable,
                            @JsonProperty("last") Boolean last,
                            @JsonProperty("totalPages") Integer totalPages,
                            @JsonProperty("sort") JsonNode sort,
                            @JsonProperty("first") Boolean first,
                            @JsonProperty("numberOfElements") Integer numberOfElements) {
        super(
                content,
                PageRequest.of(resolveNumber(page, number), resolveSize(page, size, content)),
                resolveTotal(page, totalElements, content)
        );
    }

    public RestResponsePage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestResponsePage(List<T> content) {
        super(content);
    }

    public RestResponsePage() {
        super(new ArrayList<>());
    }

    private static int getPageInt(JsonNode page, String field, int defaultValue) {
        if (page != null && page.has(field) && page.get(field).canConvertToInt()) {
            return page.get(field).asInt();
        }
        return defaultValue;
    }

    private static long getPageLong(JsonNode page, String field, long defaultValue) {
        if (page != null && page.has(field) && page.get(field).canConvertToLong()) {
            return page.get(field).asLong();
        }
        return defaultValue;
    }

    private static int resolveNumber(JsonNode page, Integer number) {
        return number != null ? number : getPageInt(page, "number", 0);
    }

    private static int resolveSize(JsonNode page, Integer size, List<?> content) {
        int resolvedSize = size != null ? size : getPageInt(page, "size", 0);
        if (resolvedSize > 0) {
            return resolvedSize;
        }
        int fallback = content != null ? content.size() : 0;
        return Math.max(fallback, 1);
    }

    private static long resolveTotal(JsonNode page, Long totalElements, List<?> content) {
        if (totalElements != null) {
            return totalElements;
        }
        long fallback = content != null ? content.size() : 0L;
        return getPageLong(page, "totalElements", fallback);
    }
}
