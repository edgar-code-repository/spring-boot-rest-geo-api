package com.example.springbootrestgeoapi.repositories;

import com.example.springbootrestgeoapi.model.Continent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContinentsRepository extends MongoRepository<Continent, String> {

    public Continent findByCode(String code);

}
