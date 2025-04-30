package com.example.demo.controller;

import com.example.demo.model.Vaga;
import com.example.demo.model.VagaDTO;
import com.example.demo.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Vaga> cadastrarVaga(@RequestBody VagaDTO vagaDTO) {
        Vaga vaga = vagaService.converterDTOparaVaga(vagaDTO);
        Vaga vagaSalva = vagaService.salvarVaga(vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaSalva);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<Vaga>> listarVagas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return ResponseEntity.ok(vagaService.listarVagasComPagina(pageable));
    }

    @GetMapping("/listar-sem-paginacao")
    public ResponseEntity<List<Vaga>> listarVagasSemPaginacao() {
        return ResponseEntity.ok(vagaService.listarVagasSemPaginacao());
    }
}
