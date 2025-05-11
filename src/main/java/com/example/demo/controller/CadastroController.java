package com.example.demo.controller;

import com.example.demo.model.CandidatoCadastroDTO;
import com.example.demo.model.EmpregadorCadastroDTO;
import com.example.demo.model.Candidato;
import com.example.demo.model.Empregador;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.EmpregadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EmpregadorRepository empregadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/candidato")
    public ResponseEntity<?> cadastrarCandidato(@Valid @RequestBody CandidatoCadastroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", "Email já cadastrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setTipo("CANDIDATO");

        Candidato candidato = new Candidato();
        candidato.setNome(dto.getNome());
        candidato.setTelefone(dto.getTelefone());
        candidato.setCpf(dto.getCpf());
        candidato.setEndereco(dto.getEndereco());
        candidato.setHabilidades(dto.getHabilidades());
        candidato.setExperiencia(dto.getExperiencia());
        candidato.setUsuario(usuario);

        usuario.setCandidato(candidato);

        usuarioRepository.save(usuario);
        candidatoRepository.save(candidato);

        return ResponseEntity.ok(Map.of("mensagem", "Candidato cadastrado com sucesso."));
    }

    @PostMapping("/empregador")
    public ResponseEntity<?> cadastrarEmpregador(@Valid @RequestBody EmpregadorCadastroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", "Email já cadastrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setTipo("EMPREGADOR");

        Empregador empregador = new Empregador();
        empregador.setNome(dto.getNome());
        empregador.setCnpj(dto.getCnpj());
        empregador.setDescricao(dto.getDescricao());
        empregador.setSetor(dto.getSetor());
        empregador.setTelefone(dto.getTelefone());
        empregador.setEndereco(dto.getEndereco());

        // Corrige vinculação bidirecional antes de salvar
        usuario.setEmpregador(empregador);
        empregador.setUsuario(usuario);

        usuarioRepository.save(usuario);
        // Se Cascade estiver corretamente configurado, essa linha pode ser opcional:
        empregadorRepository.save(empregador);

        return ResponseEntity.ok(Map.of("mensagem", "Empregador cadastrado com sucesso."));
    }
}
