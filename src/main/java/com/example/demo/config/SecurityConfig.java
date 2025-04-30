package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/vagas.html",
                                "/vagas/listar",
                                "/vagas/listar-sem-paginacao",
                                "/vagas/cadastrar",
                                "/vagas/**",
                                "/candidatos",
                                "/candidatos/**",
                                "/cadastrar-vaga",
                                "/cadastrar-vaga/**",
                                "/cadastrar-vaga.html",
                                "/form-candidato.html",
                                "/form-candidato",
                                "/form-candidato/**",
                                "/form-empregador.html",
                                "/form-empregador",
                                "/form-empregador/**",
                                "/empregadores",
                                "/empregadores/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);
        }
}
