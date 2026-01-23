package com.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private Integer studentId;
    private String imie;
    private String nazwisko;
    private String nrIndeksu;
    private String email;
    private boolean stacjonarny;
    private String password;

    // --- МЫ ПИШЕМ ЭТОТ КОД ВРУЧНУЮ, ЧТОБЫ ОШИБКА ИСЧЕЗЛА ---
    public Student(Integer studentId, String imie, String nazwisko, String nrIndeksu, String email, boolean stacjonarny, String password) {
        this.studentId = studentId;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.email = email;
        this.stacjonarny = stacjonarny;
        this.password = password;
    }
}