package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaEncoder {
    public static void main(String[] args) {
        String senha = "larissa1"; // Altere aqui se quiser outra senha
        String hash = new BCryptPasswordEncoder().encode(senha);
        System.out.println("Senha criptografada: " + hash);
    }
}
