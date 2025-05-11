package com.example.demo.service;

import com.example.demo.model.Candidato;
import com.example.demo.model.Empregador;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());
        if (existente.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Criptografa a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    // Pode ser removido se o Spring Security estiver autenticando via AuthenticationManager
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(senha, usuario.getSenha())) {
                return usuario;
            }
        }
        throw new RuntimeException("Credenciais inválidas");
    }

    public void vincularCandidato(Usuario usuario, Candidato candidato) {
        usuario.setTipo("CANDIDATO");
        candidato.setUsuario(usuario);
    }

    public void vincularEmpregador(Usuario usuario, Empregador empregador) {
        usuario.setTipo("EMPREGADOR");
        empregador.setUsuario(usuario);
    }
}
