package com.example.Clima_RestAPI.controller;

import com.example.Clima_RestAPI.model.ClimaEntity;
import com.example.Clima_RestAPI.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClimaController {
    @Autowired
    private ClimaService climaService;

    @GetMapping("/clima")
    public String preverTempo(){
        return climaService.preverTempo();
    }

    @GetMapping
    public List<ClimaEntity> obterTodos() {
        return climaService.obterTodos();
    }

    @GetMapping("/{id}")
    public ClimaEntity obterPorId(@PathVariable String id) {
        return climaService.obterPorId(id);
    }

    @PostMapping
    public ClimaEntity inserir(@RequestBody ClimaEntity clima) {
        return climaService.inserir(clima);
    }

    @PutMapping("/{id}")
    public ClimaEntity atualizar(@PathVariable String id, @RequestBody ClimaEntity clima) {
        return climaService.atualizar(id, clima);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id) {
        climaService.excluir(id);
    }

    @GetMapping("/buscarPorPais/{pais}")
    public List<ClimaEntity> buscarPorPais(@PathVariable String pais) {
        return climaService.buscarPorPais(pais);
    }

    @GetMapping("/buscarPorData/{data}")
    public List<ClimaEntity> buscarPorData(@PathVariable String data) {
        return climaService.buscarPorData(data);
    }
    //http://localhost:8080/users/buscarPorNomeEEmail?nome=joao&email=joao@gmail.com usar para achar email

    @GetMapping("/buscarPorPaisEData")
    public List<ClimaEntity> buscarUsuariosPorPaisEData(@RequestParam("pais") String pais,@RequestParam("data") String data) {
        return climaService.buscarPorPaisEData(pais, data);
    }
    @GetMapping("/buscarPorPaisQueComecaCom/{prefixo}")
    public List<ClimaEntity> buscarPorPaisQueComecaCom(@PathVariable String prefixo) {
        return climaService.buscarPorPaisQueComecaCom(prefixo);
    }
    @GetMapping("/buscarPorPaisQueContem/{contem}")
    public List<ClimaEntity> buscarPaisQueContem(@PathVariable String contem) {
        return climaService.buscarPorPaisQueContem(contem);
    }
}
