//package com.example.demo.controller;
//
//import com.example.demo.model.Candidato;
//import com.example.demo.repository.CandidatoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/candidatos")
//public class CandidatoController {
//
//    @Autowired
//    private CandidatoRepository candidatoRepository;
//
//    @GetMapping("/listar")
//    public List<Candidato> listarCandidatos() {
//        return candidatoRepository.findAll();
//    }
//}

package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping
    public ResponseEntity<List<Candidato>> listarCandidatos() {
        List<Candidato> candidatos = candidatoRepository.findAll();
        return ResponseEntity.ok(candidatos);
    }

    @PostMapping
    public ResponseEntity<Candidato> cadastrarCandidato(@Valid @RequestBody Candidato candidato) {
        Candidato salvo = candidatoRepository.save(candidato);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}
