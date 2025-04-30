package com.example.demo.service;

import com.example.demo.model.Empregador;
import com.example.demo.model.Vaga;
import com.example.demo.repository.EmpregadorRepository;
import com.example.demo.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EmpregadorRepository empregadorRepository;

    public Vaga cadastrarVaga(Vaga vaga) {
        Long empregadorId = vaga.getEmpregador().getId();

        // Busca o empregador diretamente pelo repositório correto
        Empregador empregador = empregadorRepository.findById(empregadorId)
                .orElseThrow(() -> new RuntimeException("Empregador não encontrado"));

        vaga.setEmpregador(empregador);
        return vagaRepository.save(vaga);
    }

    public Page<Vaga> listarVagasComPagina(Pageable pageable) {
        return vagaRepository.findAll(pageable);
    }

    public List<Vaga> listarVagasSemPaginacao() {
        return vagaRepository.findAll();
    }
}
