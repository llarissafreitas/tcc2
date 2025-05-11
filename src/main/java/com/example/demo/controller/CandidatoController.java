package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCandidato(@RequestBody Candidato candidato) {
        Usuario usuario = candidato.getUsuario();
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        usuario.setTipo("CANDIDATO");
        usuario.setCandidato(candidato);
        candidato.setUsuario(usuario);

        usuarioRepository.save(usuario); // persiste candidato via cascata

        return ResponseEntity.ok(candidato);
    }
}
