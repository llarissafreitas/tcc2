package com.example.demo.repository;

import com.example.demo.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByStatus(Vaga.StatusVaga status);

    List<Vaga> findByEmpregadorIdAndStatus(Long empregadorId, Vaga.StatusVaga status);

    List<Vaga> findByAcessibilidadeInAndStatus(List<String> acessibilidade, Vaga.StatusVaga status);
}
