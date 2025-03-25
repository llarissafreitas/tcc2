package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Empregador extends Usuario {

    private String empresa;
    private String cnpj;
}
