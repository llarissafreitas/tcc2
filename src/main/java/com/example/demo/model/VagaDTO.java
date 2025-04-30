package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VagaDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "O local é obrigatório")
    private String local;

    private List<String> acessibilidade;

    @NotNull(message = "O ID do empregador é obrigatório")
    private Long empregadorId;

    @NotNull(message = "O salário é obrigatório")
    private Double salario;

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }


    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<String> getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(List<String> acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public Long getEmpregadorId() {
        return empregadorId;
    }

    public void setEmpregadorId(Long empregadorId) {
        this.empregadorId = empregadorId;
    }
}
