package com.example.Clima_RestAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.Clima_RestAPI.repository.ClimaRepository;
import com.example.Clima_RestAPI.model.ClimaEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@Service
public class ClimaService {
    public String preverTempo() {
        String dadosMeteorologicos = "";
        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=9fe25332679ebce952fdd9f7f9a83c3e";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dadosMeteorologicos = responseEntity.getBody();
        } else {
            dadosMeteorologicos = "Falha ao obter dados meteorológicos. Código de status: " + responseEntity.getStatusCode();
        }

        ClimaEntity clima = new ClimaEntity(dadosMeteorologicos, a,,a,a,a);
        inserir(clima);

        return dadosMeteorologicos;
    }
    @Autowired
    private ClimaRepository climaRepository;

    public List<ClimaEntity> obterTodos() {
        return climaRepository.findAll();
    }

    public ClimaEntity obterPorId(String id) {
        return climaRepository.findById(id).orElse(null);
    }

    public ClimaEntity inserir(ClimaEntity clima) {
        return climaRepository.save(clima);
    }

    public ClimaEntity atualizar(String id, ClimaEntity newClima) {
        ClimaEntity existingClima = climaRepository.findById(id).orElse(null);

        if (existingClima!= null) {
            existingClima. setPais(newClima.getPais());
            existingClima.setData(newClima.getData());
            return climaRepository.save(existingClima);
        } else {
            return null;
        }
    }
    public void excluir (String id){
        climaRepository.deleteById(id);
    }
    public List<ClimaEntity> buscarPorPais(String pais) {
        return climaRepository.findByPaisIgnoreCase(pais);
    }

    public List<ClimaEntity> buscarPorData(String data) {
        return climaRepository.findByDataIgnoreCase(data);
    }

    public List<ClimaEntity> buscarPorPaisEData(String pais, String data) {
        return climaRepository.findByPaisAndData(pais, data);
    }

    public List<ClimaEntity> buscarPorPaisQueComecaCom(String prefixo) {
        return climaRepository.findByPaisStartingWithIgnoreCase(prefixo);
    }

    public List<ClimaEntity> buscarPorPaisQueContem(String substring) {
        return climaRepository.findByPaisContainingIgnoreCase(substring);
    }
}

