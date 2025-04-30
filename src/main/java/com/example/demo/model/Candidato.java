//package com.example.demo.model;
//
//import jakarta.persistence.Entity;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class   Candidato extends Usuario {
//    private String curriculoUrl; // Link para o currículo do candidato
//}

package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Email // valida formato de email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    @Column(unique = true) // garante que CPF não se repita no banco
    private String cpf;

    @NotBlank
    private String endereco;

    private String habilidades;

    private String experiencia;

    private String escolaridade;

    // outros campos relevantes podem ser adicionados aqui
}
