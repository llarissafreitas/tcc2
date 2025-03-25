package com.example.demo.service;

import com.example.demo.model.Candidato;
import com.example.demo.model.Empregador;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.EmpregadorRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EmpregadorRepository empregadorRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Candidato cadastrarCandidato(Candidato candidato) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(candidato.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este e-mail.");
        }
        return candidatoRepository.save(candidato);
    }

    public Empregador cadastrarEmpregador(Empregador empregador) {
        return empregadorRepository.save(empregador);
    }
}
