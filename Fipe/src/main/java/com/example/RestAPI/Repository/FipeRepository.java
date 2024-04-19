package com.example.RestAPI.Repository;

import com.example.RestAPI.Model.FipeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FipeRepository extends MongoRepository<FipeEntity, String> {

}
