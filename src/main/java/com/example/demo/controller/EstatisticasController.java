package com.example.demo.controller;

import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.VagaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class EstatisticasController {

    private final CandidatoRepository candidatoRepo;
    private final VagaRepository vagaRepo;

    public EstatisticasController(CandidatoRepository candidatoRepo, VagaRepository vagaRepo) {
        this.candidatoRepo = candidatoRepo;
        this.vagaRepo = vagaRepo;
    }

    @GetMapping("/api/estatisticas")
    public Map<String, Long> getEstatisticas() {
        long candidatos = candidatoRepo.count();
        long vagas = vagaRepo.count();
        return Map.of(
                "candidatos", candidatos,
                "vagas", vagas
        );
    }
}
