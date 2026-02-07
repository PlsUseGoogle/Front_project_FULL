package com.project.service;

import com.project.model.Projekt;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class ProjektServiceImpl implements ProjektService {

    private static final String RESOURCE_PATH = "/projekty";

    private final ApiRestClientProvider restClientProvider;

    public ProjektServiceImpl(ApiRestClientProvider restClientProvider) {
        this.restClientProvider = restClientProvider;
    }

    @Override
    public Optional<Projekt> getProjekt(Integer projektId) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        Projekt projekt = restClient.get()
                .uri(RESOURCE_PATH + "/{projektId}", projektId)
                .retrieve()
                .body(Projekt.class);
        return Optional.ofNullable(projekt);
    }

    @Override
    public Projekt setProjekt(Projekt projekt) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        if (projekt.getProjektId() == null) {
            Projekt created = restClient.post()
                    .uri(RESOURCE_PATH)
                    .body(projekt)
                    .retrieve()
                    .body(Projekt.class);
            return created != null ? created : projekt;
        }

        Projekt updated = restClient.put()
                .uri(RESOURCE_PATH + "/{projektId}", projekt.getProjektId())
                .body(projekt)
                .retrieve()
                .body(Projekt.class);
        return updated != null ? updated : projekt;
    }

    @Override
    public void deleteProjekt(Integer projektId) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        restClient.delete()
                .uri(RESOURCE_PATH + "/{projektId}", projektId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Page<Projekt> getProjekty(Pageable pageable) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Projekt> page = restClient.get()
                .uri(uriBuilder -> ServiceUtil.applySort(
                                uriBuilder.path(RESOURCE_PATH)
                                        .queryParam("page", pageable.getPageNumber())
                                        .queryParam("size", pageable.getPageSize()),
                                pageable.getSort())
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page : new RestResponsePage<>();
    }

    @Override
    public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Projekt> page = restClient.get()
                .uri(uriBuilder -> ServiceUtil.applySort(
                                uriBuilder.path(RESOURCE_PATH)
                                        .queryParam("nazwa", nazwa)
                                        .queryParam("page", pageable.getPageNumber())
                                        .queryParam("size", pageable.getPageSize()),
                                pageable.getSort())
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page : new RestResponsePage<>();
    }
}
