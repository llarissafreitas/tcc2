package com.example.demo.repository;

import com.example.demo.model.Candidatura;
import com.example.demo.model.Candidato;
import com.example.demo.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByVaga(Vaga vaga);
    Optional<Candidatura> findByCandidatoAndVaga(Candidato candidato, Vaga vaga);
    List<Candidatura> findByCandidato(Candidato candidato);
}
