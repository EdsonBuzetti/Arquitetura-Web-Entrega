package com.example.Clima_RestAPI.repository;

import com.example.Clima_RestAPI.model.ClimaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimaRepository extends MongoRepository<ClimaEntity, String> {
    List<ClimaEntity> findByPaisIgnoreCase(String pais);

    List<ClimaEntity> findByDataIgnoreCase(String data);

    List<ClimaEntity> findByPaisAndData(String pais, String data);

    List<ClimaEntity> findByPaisStartingWithIgnoreCase(String prefixo);

    List<ClimaEntity> findByPaisContainingIgnoreCase(String substring);
}
