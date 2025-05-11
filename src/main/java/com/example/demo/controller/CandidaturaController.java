package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.model.Candidatura;
import com.example.demo.model.Vaga;
import com.example.demo.dto.CandidaturaResumoDTO;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.CandidaturaRepository;
import com.example.demo.repository.VagaRepository;
import com.example.demo.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private MailService mailService;

    @GetMapping("/minhas")
    public ResponseEntity<List<CandidaturaResumoDTO>> listarMinhasCandidaturas(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();

        Candidato candidato = candidatoRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        List<Candidatura> candidaturas = candidaturaRepository.findByCandidato(candidato);

        List<CandidaturaResumoDTO> dtos = candidaturas.stream().map(c -> {
            Vaga vaga = c.getVaga();
            String empresa = vaga.getEmpregador() != null ? vaga.getEmpregador().getNome() : "Empresa não informada";
            return new CandidaturaResumoDTO(
                    vaga.getTitulo(),
                    vaga.getLocal(),
                    vaga.getSalario(),
                    empresa,
                    c.getId()

            );
        }).toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCandidatura(@RequestParam Long vagaId, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();

        Candidato candidato = candidatoRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        Candidatura candidatura = new Candidatura();
        candidatura.setCandidato(candidato);
        candidatura.setVaga(vaga);

        candidaturaRepository.save(candidatura);

        return ResponseEntity.ok("Candidatura realizada com sucesso.");
    }

    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionarCandidato(@RequestParam Long candidaturaId) {
        Candidatura candidatura = candidaturaRepository.findById(candidaturaId)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));

        Vaga vaga = candidatura.getVaga();
        Candidato candidato = candidatura.getCandidato();

        String emailDestino = candidato.getUsuario().getEmail();
        String nomeCandidato = candidato.getNome();
        String nomeEmpresa = vaga.getEmpregador().getNome();

        String assunto = "Você foi selecionado!";
        String corpo = "Parabéns, " + nomeCandidato +
                "! Você foi selecionado para a vaga: " + vaga.getTitulo() +
                " pela empresa " + nomeEmpresa + ".";

        mailService.enviarEmail(emailDestino, assunto, corpo);

        return ResponseEntity.ok("Candidato selecionado e notificado por e-mail.");
    }
}
