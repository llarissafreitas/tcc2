package com.example.demo.repository;

import com.example.demo.model.Vaga;
import com.example.demo.model.Empregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByEmpregador(Empregador empregador);
}
