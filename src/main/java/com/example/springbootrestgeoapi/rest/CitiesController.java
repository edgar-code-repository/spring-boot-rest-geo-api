package com.example.springbootrestgeoapi.rest;

import com.example.springbootrestgeoapi.model.City;
import com.example.springbootrestgeoapi.model.Country;
import com.example.springbootrestgeoapi.repositories.CitiesRepository;
import com.example.springbootrestgeoapi.repositories.CountriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CitiesController {

    private static final Logger logger = LoggerFactory.getLogger(CitiesController.class);

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        logger.info("[CitiesController][getAllCities][START]");

        List<City> cities = null;
        cities = citiesRepository.findAll();

        logger.info("[CitiesController][getAllCities][cities size: " + (cities==null?"0":cities.size()) + "]");
        logger.info("[CitiesController][getAllCities][END]");

        return cities;
    }

    @GetMapping("/countries/{countryId}/cities")
    public List<City> getAllCitiesByCountry(@PathVariable String countryId) {
        logger.info("[CitiesController][getAllCitiesByCountry][START]");
        logger.info("[CitiesController][getAllCitiesByCountry][countryId: " + countryId + "]");

        List<City> cities = null;
        cities = citiesRepository.findByCountryId(countryId);

        logger.info("[CitiesController][getAllCitiesByCountry][cities size: " + (cities==null?"0":cities.size()) + "]");
        logger.info("[CitiesController][getAllCitiesByCountry][END]");

        return cities;
    }

    @PostMapping("/countries/{countryId}/cities")
    public City createCity(@PathVariable String countryId, @Valid @RequestBody City city) {
        logger.info("[CitiesController][createCity][START]");
        logger.info("[CitiesController][createCity][city parameter: " + city.toString()  + "]");

        Country country = null;
        if (city.getCountry() == null) {
            Optional<Country> optionalCountry = countriesRepository.findById(countryId);
            if (optionalCountry.isPresent()) {
                country = optionalCountry.get();
                city.setCountry(country);
            }
        }

        city = citiesRepository.save(city);

        logger.info("[CitiesController][createCity][city created: " + city.toString() + "]");
        logger.info("[CitiesController][createCity][END]");

        return city;
    }

    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable String id) {
        logger.info("[CitiesController][getCityById][START]");
        logger.info("[CitiesController][getCityById][id parameter: " + id  + "]");

        City city = null;
        Optional<City> optionalCity = citiesRepository.findById(id);
        if (optionalCity.isPresent()) {
            city = optionalCity.get();
        }

        logger.info("[CitiesController][getCityById][city retrieved: " + (city==null?"":city.toString())  + "]");
        logger.info("[CitiesController][getCityById][END]");

        return city;
    }

    @PutMapping("/countries/{countryId}/cities/{id}")
    public City updateCity(@PathVariable String countryId, @PathVariable String id, @Valid @RequestBody City city) {
        logger.info("[CitiesController][updateCity][START]");
        logger.info("[CitiesController][updateCity][city parameter: " + city.toString()  + "]");

        Country country = null;
        if (city.getCountry() == null) {
            Optional<Country> optionalCountry = countriesRepository.findById(countryId);
            if (optionalCountry.isPresent()) {
                country = optionalCountry.get();
                city.setCountry(country);
            }
        }

        if (city.getId() == null) {
            city.setId(id);
        }

        city = citiesRepository.save(city);

        logger.info("[CitiesController][updateCity][city retrieved: " + (city==null?"":city.toString())  + "]");
        logger.info("[CitiesController][updateCity][END]");

        return city;
    }


    @DeleteMapping("/cities/{id}")
    public boolean deleteCity(@PathVariable String id) {
        logger.info("[CitiesController][deleteCity][START]");
        logger.info("[CitiesController][deleteCity][id parameter: " + id  + "]");

        boolean flag = false;

        Optional<City> optionalCity = citiesRepository.findById(id);
        if (optionalCity.isPresent()) {
            citiesRepository.deleteById(id);
            flag = true;
        }

        logger.info("[CitiesController][getCityById][flag delete: " + flag  + "]");
        logger.info("[CitiesController][getCityById][END]");

        return flag;
    }

}
