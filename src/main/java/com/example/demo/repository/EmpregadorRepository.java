package com.example.demo.repository;

import com.example.demo.model.Empregador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpregadorRepository extends JpaRepository<Empregador, Long> {
}
