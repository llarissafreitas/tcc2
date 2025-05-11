package com.example.demo.controller;

import com.example.demo.model.Empregador;
import com.example.demo.model.Usuario;
import com.example.demo.model.Vaga;
import com.example.demo.model.Candidatura;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.EmpregadorRepository;
import com.example.demo.repository.VagaRepository;
import com.example.demo.repository.CandidaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/empregadores")
public class EmpregadorController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpregadorRepository empregadorRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarEmpregador(@RequestBody Empregador empregador) {
        Usuario usuario = empregador.getUsuario();
        if (usuario == null || usuario.getEmail() == null) {
            return ResponseEntity.badRequest().body("Dados de usuário inválidos");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        usuario.setTipo("EMPREGADOR");
        empregador.setUsuario(usuario);

        usuarioRepository.save(usuario);
        empregadorRepository.save(empregador);

        return ResponseEntity.ok(empregador);
    }

    @GetMapping("/minhas-vagas-com-candidatos")
    public ResponseEntity<?> listarVagasComCandidatos(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!"EMPREGADOR".equalsIgnoreCase(usuario.getTipo())) {
            return ResponseEntity.status(403).body("Acesso negado");
        }

        Empregador empregador = usuario.getEmpregador();
        List<Vaga> vagas = vagaRepository.findByEmpregador(empregador);

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Vaga vaga : vagas) {
            Map<String, Object> vagaMap = new HashMap<>();
            vagaMap.put("id", vaga.getId());
            vagaMap.put("titulo", vaga.getTitulo());
            vagaMap.put("descricao", vaga.getDescricao());
            vagaMap.put("local", vaga.getLocal());
            vagaMap.put("salario", vaga.getSalario());

            List<Candidatura> candidaturas = candidaturaRepository.findByVaga(vaga);
            List<Map<String, Object>> candidatos = new ArrayList<>();
            for (Candidatura candidatura : candidaturas) {
                var candidato = candidatura.getCandidato();
                Map<String, Object> dados = new HashMap<>();
                dados.put("nome", candidato.getNome());
                dados.put("email", candidato.getUsuario().getEmail());
                dados.put("telefone", candidato.getTelefone());
                dados.put("habilidades", candidato.getHabilidades());
                dados.put("experiencia", candidato.getExperiencia());
                dados.put("cpf", candidato.getCpf());
                dados.put("endereco", candidato.getEndereco());
                dados.put("candidaturaId", candidatura.getId());
                candidatos.add(dados);
            }

            vagaMap.put("candidatos", candidatos);
            resultado.add(vagaMap);
        }

        return ResponseEntity.ok(resultado);
    }
}
