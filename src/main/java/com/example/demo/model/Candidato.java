package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class  Candidato extends Usuario {
    private String curriculoUrl; // Link para o currículo do candidato
}
