package com.example.demo.controller;

import com.example.demo.security.JwtService;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha)
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user.getUsername());

        // Busca o tipo do usuário no banco
        String tipo = usuarioRepository.findByEmail(email)
                .map(Usuario::getTipo)
                .orElse("DESCONHECIDO");

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getUsername());
        response.put("tipo", tipo); // importante para o front-end

        return ResponseEntity.ok(response);
    }
}
