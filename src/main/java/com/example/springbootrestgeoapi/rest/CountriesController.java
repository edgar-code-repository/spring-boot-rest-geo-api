package com.example.springbootrestgeoapi.rest;

import com.example.springbootrestgeoapi.model.Continent;
import com.example.springbootrestgeoapi.model.Country;
import com.example.springbootrestgeoapi.repositories.ContinentsRepository;
import com.example.springbootrestgeoapi.repositories.CountriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CountriesController {

    private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);

    @Autowired
    private ContinentsRepository continentsRepository;

    @Autowired
    private CountriesRepository countriesRepository;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        logger.info("[CountriesController][getAllCountries][START]");

        List<Country> countries = null;
        countries = countriesRepository.findAll();

        logger.info("[CountriesController][getAllCountries][countries size: " + (countries==null?"0":countries.size()) + "]");
        logger.info("[CountriesController][getAllCountries][END]");

        return countries;
    }

    @GetMapping("/countries/{countryId}")
    public Country getCountryById(@PathVariable String countryId) {
        logger.info("[CountriesController][getCountryById][START]");
        logger.info("[CountriesController][getCountryById][id parameter: " + countryId + "]");

        Country country = null;

        Optional<Country> optionalCountry = countriesRepository.findById(countryId);
        if (optionalCountry.isPresent()) {
            country = optionalCountry.get();
        }

        logger.info("[CountriesController][createCountry][country retrieved: " + (country==null?"":country.toString()) + "]");
        logger.info("[CountriesController][getCountryById][END]");

        return country;
    }

    @PostMapping("/continents/{continentId}/countries")
    public Country createCountry(@PathVariable String continentId, @RequestBody Country country) {
        logger.info("[CountriesController][createCountry][START]");
        logger.info("[CountriesController][createCountry][country parameter: " + country.toString() + "]");
        logger.info("[CountriesController][createCountry][continentId: " + continentId + "]");

        Continent continent = null;
        if (country.getContinent() == null) {
            Optional<Continent> optionalContinent = continentsRepository.findById(continentId);
            if (optionalContinent.isPresent()) {
                continent = optionalContinent.get();
                country.setContinent(continent);
            }
        }

        country = countriesRepository.save(country);

        logger.info("[CountriesController][createCountry][country created: " + country.toString() + "]");
        logger.info("[CountriesController][createCountry][END]");

        return country;
    }

    @PutMapping("/continents/{continentId}/countries/{countryId}")
    public Country updateCountry(@PathVariable String continentId, @PathVariable String countryId, @RequestBody Country country) {
        logger.info("[CountriesController][updateCountry][START]");
        logger.info("[CountriesController][updateCountry][country parameter: " + country.toString() + "]");
        logger.info("[CountriesController][updateCountry][continentId: " + continentId + "]");

        Continent continent = null;
        if (country.getContinent() == null) {
            Optional<Continent> optionalContinent = continentsRepository.findById(continentId);
            if (optionalContinent.isPresent()) {
                continent = optionalContinent.get();
                country.setContinent(continent);
            }
        }

        if (country.getId() == null) {
            country.setId(countryId);
        }

        country = countriesRepository.save(country);

        logger.info("[CountriesController][updateCountry][country updated: " + country.toString() + "]");
        logger.info("[CountriesController][updateCountry][END]");

        return country;
    }

    @DeleteMapping("/countries/{id}")
    public boolean deleteCountry(@PathVariable String id) {
        logger.info("[CountriesController][deleteCountry][START]");
        logger.info("[CountriesController][deleteCountry][id parameter: " + id + "]");

        boolean flag = false;

        Optional<Country> optionalCountry = countriesRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            countriesRepository.delete(country);
            flag = true;
        }
        else {
            logger.info("[CountriesController][deleteCountry][country not found]");
        }

        logger.info("[CountriesController][deleteCountry][flag: " + flag + "]");
        logger.info("[CountriesController][deleteCountry][END]");

        return flag;
    }

}
