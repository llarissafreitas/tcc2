package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
            }

            if ("CANDIDATO".equalsIgnoreCase(usuario.getTipo()) && usuario.getCandidato() != null) {
                usuario.getCandidato().setUsuario(usuario);
            }

            if ("EMPREGADOR".equalsIgnoreCase(usuario.getTipo()) && usuario.getEmpregador() != null) {
                usuario.getEmpregador().setUsuario(usuario);
            }

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Usuario salvo = usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
