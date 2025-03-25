package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.model.Empregador;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @PostMapping("/cadastrar/candidato")
    public Candidato cadastrarCandidato(@RequestBody Candidato candidato) {
        return usuarioService.cadastrarCandidato(candidato);
    }

    @PostMapping("/cadastrar/empregador")
    public Empregador cadastrarEmpregador(@RequestBody Empregador empregador) {
        return usuarioService.cadastrarEmpregador(empregador);
    }
}
