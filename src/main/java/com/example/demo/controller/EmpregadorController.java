package com.example.demo.controller;

import com.example.demo.model.Empregador;
import com.example.demo.repository.EmpregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empregadores")
public class EmpregadorController {

    @Autowired
    private EmpregadorRepository empregadorRepository;

    @GetMapping("/listar")
    public List<Empregador> listarEmpregadores() {
        return empregadorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Empregador> cadastrarEmpregador(@RequestBody Empregador empregador) {
        Empregador salvo = empregadorRepository.save(empregador);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}
