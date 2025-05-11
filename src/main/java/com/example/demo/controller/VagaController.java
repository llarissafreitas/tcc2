package com.example.demo.controller;

import com.example.demo.model.Vaga;
import com.example.demo.service.VagaService;
import com.example.demo.model.Usuario;
import com.example.demo.model.Empregador;
import com.example.demo.model.VagaDTO;
import com.example.demo.repository.VagaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.EmpregadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpregadorRepository empregadorRepository;

    @GetMapping("/listar-sem-paginacao")
    public List<Vaga> listarVagasSemPaginacao() {
        return vagaService.listarVagasSemPaginacao();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarVaga(@RequestBody VagaDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getEmpregadorId());
        if (usuarioOpt.isEmpty() || !"EMPREGADOR".equals(usuarioOpt.get().getTipo())) {
            return ResponseEntity.badRequest().body("ID de empregador inválido");
        }

        Usuario usuario = usuarioOpt.get();
        Empregador empregador = usuario.getEmpregador();

        if (empregador == null) {
            return ResponseEntity.badRequest().body("Usuário não está vinculado a um empregador");
        }

        Vaga vaga = new Vaga();
        vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setLocal(dto.getLocal());
        vaga.setSalario(dto.getSalario());
        vaga.setRequisitos(dto.getRequisitos());
        vaga.setEmpregador(empregador);

        vagaRepository.save(vaga);

        return ResponseEntity.ok("Vaga cadastrada com sucesso.");
    }

}
