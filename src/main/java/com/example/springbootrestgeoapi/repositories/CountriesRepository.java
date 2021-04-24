package com.example.springbootrestgeoapi.repositories;

import com.example.springbootrestgeoapi.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CountriesRepository extends MongoRepository<Country, String> {

    List<Country> findByContinentId(String continentId);

}
