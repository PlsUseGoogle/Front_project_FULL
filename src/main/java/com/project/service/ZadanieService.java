package com.project.service;

import com.project.model.Zadanie;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List; // Важно
import java.util.Arrays; // Важно
import java.util.Optional;

@Service
public class ZadanieService {
    private List<Zadanie> zadania = new ArrayList<>();

    public ZadanieService() {
        // Тестовая задача: назначаем сразу двум студентам (ID 1 и ID 2)
        List<Integer> ids = new ArrayList<>(Arrays.asList(1, 2));
        zadania.add(new Zadanie(1, "Zrobić Frontend", 1, "HTML i CSS", ids, LocalDateTime.now()));
    }

    public List<Zadanie> getAllZadania() { return zadania; }

    public Optional<Zadanie> getZadanie(Integer id) {
        return zadania.stream().filter(z -> z.getZadanieId().equals(id)).findFirst();
    }

    public void saveZadanie(Zadanie zadanie) {
        if (zadanie.getStudentIds() == null) {
            zadanie.setStudentIds(new ArrayList<>());
        }

        if (zadanie.getZadanieId() == null) {
            zadanie.setZadanieId(zadania.size() + 1);
            zadanie.setDataCzasDodania(LocalDateTime.now());
            zadania.add(zadanie);
        } else {
            deleteZadanie(zadanie.getZadanieId());
            zadanie.setDataCzasDodania(LocalDateTime.now());
            zadania.add(zadanie);
        }
    }

    public void deleteZadanie(Integer id) {
        zadania.removeIf(z -> z.getZadanieId().equals(id));
    }
}