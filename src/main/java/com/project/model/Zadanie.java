package com.project.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.Projekt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zadanie {
    private Integer zadanieId;
    private String nazwa;
    private Integer kolejnosc;
    private String opis;
    private Projekt projekt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonAlias("dataczasDodania")
    private LocalDateTime dataCzasDodania;
}
