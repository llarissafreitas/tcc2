package com.example.demo.service;

import com.example.demo.model.Candidato;
import com.example.demo.model.Candidatura;
import com.example.demo.model.Vaga;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.CandidaturaRepository;
import com.example.demo.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    /**
     * Cadastra um candidato em uma vaga, se não estiver já inscrito.
     *
     * @param vagaId        ID da vaga
     * @param emailUsuario  Email do candidato logado
     */
    public void candidatar(Long vagaId, String emailUsuario) {
        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        Candidato candidato = candidatoRepository.findByUsuarioEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        boolean jaCandidatado = candidaturaRepository.findByCandidatoAndVaga(candidato, vaga).isPresent();
        if (jaCandidatado) {
            throw new RuntimeException("Você já se candidatou a esta vaga.");
        }

        Candidatura candidatura = new Candidatura();
        candidatura.setCandidato(candidato);
        candidatura.setVaga(vaga);

        candidaturaRepository.save(candidatura);
    }
}
