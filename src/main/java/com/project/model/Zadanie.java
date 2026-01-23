package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zadanie {
    private Integer zadanieId;
    private String nazwa;
    private Integer kolejnosc;
    private String opis;

    // !!! ИЗМЕНЕНИЕ: Теперь тут СПИСОК ID студентов (List instead of Integer)
    private List<Integer> studentIds = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCzasDodania;
}