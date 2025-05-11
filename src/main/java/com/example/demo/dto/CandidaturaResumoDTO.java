package com.example.demo.dto;

public class CandidaturaResumoDTO {
    private String tituloVaga;
    private String local;
    private double salario;
    private String nomeEmpresa;
    private Long candidaturaId;

    public CandidaturaResumoDTO(String tituloVaga, String local, double salario, String nomeEmpresa, Long candidaturaId) {
        this.tituloVaga = tituloVaga;
        this.local = local;
        this.salario = salario;
        this.nomeEmpresa = nomeEmpresa;
        this.candidaturaId = candidaturaId;
    }

    // Getters e Setters
    public String getTituloVaga() {
        return tituloVaga;
    }

    public void setTituloVaga(String tituloVaga) {
        this.tituloVaga = tituloVaga;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Long getCandidaturaId() {
        return candidaturaId;
    }

    public void setCandidaturaId(Long candidaturaId) {
        this.candidaturaId = candidaturaId;
    }
}
