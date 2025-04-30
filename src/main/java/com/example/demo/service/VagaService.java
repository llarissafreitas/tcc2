package com.example.demo.service;

import com.example.demo.model.Empregador;
import com.example.demo.model.Vaga;
import com.example.demo.model.VagaDTO;
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

    public Vaga converterDTOparaVaga(VagaDTO dto) {
        try {
            Empregador empregador = empregadorRepository.findById(dto.getEmpregadorId())
                    .orElseThrow(() -> new RuntimeException("Empregador não encontrado"));

            Vaga vaga = new Vaga();
            vaga.setTitulo(dto.getTitulo());
            vaga.setDescricao(dto.getDescricao());
            vaga.setLocalizacao(dto.getLocal());
            vaga.setEmpregador(empregador);
            vaga.setSalario(dto.getSalario());

            return vaga;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao converter VagaDTO: " + e.getMessage());
        }
    }

    public Vaga salvarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public Page<Vaga> listarVagasComPagina(Pageable pageable) {
        return vagaRepository.findAll(pageable);
    }

    public List<Vaga> listarVagasSemPaginacao() {
        return vagaRepository.findAll();
    }
}
