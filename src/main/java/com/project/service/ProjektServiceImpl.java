package com.project.service;

import com.project.model.Projekt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjektServiceImpl implements ProjektService {

    private List<Projekt> projekty = new ArrayList<>();

    public ProjektServiceImpl() {
        projekty.add(new Projekt(1, "Projekt Java", "Aplikacja webowa w Spring Boot", LocalDateTime.now(), LocalDate.now().plusDays(7)));
        projekty.add(new Projekt(2, "Strona HTML", "Front-end dla sklepu", LocalDateTime.now().minusDays(1), LocalDate.now().plusDays(3)));
        projekty.add(new Projekt(3, "Baza Danych", "Projekt SQL i Hibernate", LocalDateTime.now(), LocalDate.now().plusDays(14)));
    }

    @Override
    public Optional<Projekt> getProjekt(Integer projektId) {
        return projekty.stream()
                .filter(p -> p.getProjektId().equals(projektId))
                .findFirst();
    }

    @Override
    public Projekt setProjekt(Projekt projekt) {
        if (projekt.getProjektId() == null) {
            projekt.setProjektId(projekty.size() + 1);
            projekt.setDataCzasUtworzenia(LocalDateTime.now());
        } else {
            deleteProjekt(projekt.getProjektId());
        }

        if (projekt.getDataOddania() == null) {
            projekt.setDataOddania(LocalDate.now().plusDays(7));
        }

        projekty.add(projekt);
        return projekt;
    }

    @Override
    public void deleteProjekt(Integer projektId) {
        projekty.removeIf(p -> p.getProjektId().equals(projektId));
    }

    @Override
    public Page<Projekt> getProjekty(Pageable pageable) {
        return new PageImpl<>(projekty, pageable, projekty.size());
    }

    @Override
    public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable) {
        List<Projekt> filtered = projekty.stream()
                .filter(p -> p.getNazwa().toLowerCase().contains(nazwa.toLowerCase()))
                .collect(Collectors.toList());
        return new PageImpl<>(filtered, pageable, filtered.size());
    }
}