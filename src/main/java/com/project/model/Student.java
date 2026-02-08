package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private Integer studentId;
    private String imie;
    private String nazwisko;
    private String nrIndeksu;
    private String email;
    private boolean stacjonarny;
    private String password;
    private List<Projekt> projekty = new ArrayList<>();
    private List<Integer> projektIds = new ArrayList<>();

    // --- МЫ ПИШЕМ ЭТОТ КОД ВРУЧНУЮ, ЧТОБЫ ОШИБКА ИСЧЕЗЛА ---
    public Student(Integer studentId, String imie, String nazwisko, String nrIndeksu, String email, boolean stacjonarny, String password) {
        this.studentId = studentId;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.email = email;
        this.stacjonarny = stacjonarny;
        this.password = password;
        this.projekty = new ArrayList<>();
        this.projektIds = new ArrayList<>();
    }

    public void setProjekty(List<Projekt> projekty) {
        this.projekty = projekty != null ? projekty : new ArrayList<>();
        this.projektIds = this.projekty.stream()
                .filter(Objects::nonNull)
                .map(Projekt::getProjektId)
                .filter(Objects::nonNull)
                .toList();
    }
}
