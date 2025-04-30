//package com.example.demo.repository;
//
//import com.example.demo.model.Candidato;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
//}

package com.example.demo.repository;

import com.example.demo.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
