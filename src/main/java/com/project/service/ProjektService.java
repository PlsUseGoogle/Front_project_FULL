package com.project.service;

import com.project.model.Projekt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjektService {
    // Получить один проект
    Optional<Projekt> getProjekt(Integer projektId);

    // Сохранить проект (ВАЖНО: теперь возвращает Projekt, а не void)
    Projekt setProjekt(Projekt projekt);

    // Удалить проект
    void deleteProjekt(Integer projektId);

    // Получить список страниц
    Page<Projekt> getProjekty(Pageable pageable);

    // Поиск
    Page<Projekt> searchByNazwa(String nazwa, Pageable pageable);
}