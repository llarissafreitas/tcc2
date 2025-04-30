package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private String local;

    @ElementCollection
    @CollectionTable(name = "vaga_acessibilidade",
            joinColumns = @JoinColumn(name = "vaga_id"))
    @Column(name = "recurso")
    private List<String> acessibilidade;

    @Column
    private String requisitos;

    @Column
    private String tipoDeficiencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empregador_id")
    private Empregador empregador;

    @Enumerated(EnumType.STRING)
    private StatusVaga status = StatusVaga.ATIVA;

    public Vaga() {
    }

    public Vaga(String titulo, String descricao, String local, List<String> acessibilidade, String requisitos, String tipoDeficiencia, Empregador empregador) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.acessibilidade = acessibilidade;
        this.requisitos = requisitos;
        this.tipoDeficiencia = tipoDeficiencia;
        this.empregador = empregador;
    }

    public Long getId() {
        return id;
    }

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

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public void setTipoDeficiencia(String tipoDeficiencia) {
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public Empregador getEmpregador() {
        return empregador;
    }

    public void setEmpregador(Empregador empregador) {
        this.empregador = empregador;
    }

    public StatusVaga getStatus() {
        return status;
    }

    public void setStatus(StatusVaga status) {
        this.status = status;
    }

    public enum StatusVaga {
        ATIVA,
        INATIVA,
        PREENCHIDA
    }
}
