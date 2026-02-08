package com.project.service;

import com.project.model.Zadanie;
import org.springframework.stereotype.Service;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class ZadanieService {
    private static final String RESOURCE_PATH = "/zadania";

    private final ApiRestClientProvider restClientProvider;

    public ZadanieService(ApiRestClientProvider restClientProvider) {
        this.restClientProvider = restClientProvider;
    }

    public List<Zadanie> getAllZadania() {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Zadanie> page = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(RESOURCE_PATH)
                        .queryParam("page", 0)
                        .queryParam("size", 1000)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page.getContent() : List.of();
    }

    public Optional<Zadanie> getZadanie(Integer id) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        Zadanie zadanie = restClient.get()
                .uri(RESOURCE_PATH + "/{zadanieId}", id)
                .retrieve()
                .body(Zadanie.class);
        return Optional.ofNullable(zadanie);
    }

    public void saveZadanie(Zadanie zadanie) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        ZadanieRequest payload = ZadanieRequest.fromZadanie(zadanie);
        if (zadanie.getZadanieId() == null) {
            restClient.post()
                    .uri(RESOURCE_PATH)
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        } else {
            restClient.put()
                    .uri(RESOURCE_PATH + "/{zadanieId}", zadanie.getZadanieId())
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    public void deleteZadanie(Integer id) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        restClient.delete()
                .uri(RESOURCE_PATH + "/{zadanieId}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public long countZadaniaForProjekt(Integer projektId) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Zadanie> page = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/projekty/{projektId}/zadania")
                        .queryParam("page", 0)
                        .queryParam("size", 1)
                        .build(projektId))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page.getTotalElements() : 0L;
    }

    private record ZadanieRequest(String nazwa, String opis, Integer kolejnosc, ProjektRef projekt) {
        static ZadanieRequest fromZadanie(Zadanie zadanie) {
            ProjektRef projekt = zadanie.getProjekt() != null && zadanie.getProjekt().getProjektId() != null
                    ? new ProjektRef(zadanie.getProjekt().getProjektId())
                    : null;
            return new ZadanieRequest(
                    zadanie.getNazwa(),
                    zadanie.getOpis(),
                    zadanie.getKolejnosc(),
                    projekt
            );
        }
    }

    private record ProjektRef(Integer projektId) {}
}
