package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping("/listar")
    public List<Candidato> listarCandidatos() {
        return candidatoRepository.findAll();
    }
}
