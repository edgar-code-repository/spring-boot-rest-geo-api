package com.example.springbootrestgeoapi.repositories;

import com.example.springbootrestgeoapi.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitiesRepository extends MongoRepository<City, String> {
    List<City> findByCountryId(String countryId);
}
