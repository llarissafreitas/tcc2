package com.example.demo.model;

public class VagaDTO {
    private String titulo;
    private String descricao;
    private String local;
    private Double salario;
    private String requisitos;
    private Long empregadorId; // <- este campo é essencial



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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Long getEmpregadorId() {
        return empregadorId;
    }

    public void setEmpregadorId(Long empregadorId) {
        this.empregadorId = empregadorId;
    }
}
