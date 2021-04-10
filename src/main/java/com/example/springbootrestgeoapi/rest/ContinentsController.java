package com.example.springbootrestgeoapi.rest;

import com.example.springbootrestgeoapi.model.Continent;
import com.example.springbootrestgeoapi.repositories.ContinentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ContinentsController {

    private static final Logger logger = LoggerFactory.getLogger(ContinentsController.class);

    @Autowired
    private ContinentsRepository continentsRepository;

    @GetMapping("/continents")
    public List<Continent> getAllContinents() {
        logger.info("[ContinentsController][getAllContinents][START]");

        List<Continent> continents = null;
        continents = continentsRepository.findAll();

        logger.info("[ContinentsController][getAllContinents][continents size: " + (continents==null?"0":continents.size()) + "]");
        logger.info("[ContinentsController][getAllContinents][END]");
        return continents;
    }

    @PostMapping("/continents")
    public Continent createContinent(@Valid @RequestBody Continent continent) {
        logger.info("[ContinentsController][createContinent][START]");
        logger.info("[ContinentsController][createContinent][continent parameter: " + continent.toString() + "]");

        continent = continentsRepository.save(continent);

        logger.info("[ContinentsController][createContinent][continent created: " + continent.toString() + "]");
        logger.info("[ContinentsController][createContinent][END]");

        return continent;
    }

    @GetMapping("/continents/{code}")
    public Continent getContinentById(@PathVariable("code") String code) {
        logger.info("[ContinentsController][getContinentById][START]");

        Continent continent = continentsRepository.findByCode(code);
        if (continent != null) {
            logger.info("[ContinentsController][getContinentById][continent retrieved: " + continent.toString() + "]");
        }

        logger.info("[ContinentsController][getContinentById][END]");
        return continent;
    }

    @PutMapping("/continents/{code}")
    public Continent updateContinent(@PathVariable("code") String code, @Valid @RequestBody Continent continent) {
        logger.info("[ContinentsController][updateContinent][START]");
        logger.info("[ContinentsController][updateContinent][code: " + code + "]");
        logger.info("[ContinentsController][updateContinent][continent parameter: " + continent.toString() + "]");

        continent = continentsRepository.save(continent);

        logger.info("[ContinentsController][updateContinent][continent updated: " + continent.toString() + "]");
        logger.info("[ContinentsController][updateContinent][END]");

        return continent;
    }

    @DeleteMapping("/continents/{code}")
    public boolean deleteContinent(@PathVariable("code") String code) {
        logger.info("[ContinentsController][deleteContinent][START]");
        logger.info("[ContinentsController][deleteContinent][code: " + code + "]");

        boolean flag = false;

        Continent continent = continentsRepository.findByCode(code);
        if (continent != null) {
            continentsRepository.delete(continent);
            flag = true;
        }
        else {
            logger.info("[ContinentsController][deleteContinent][continent not found]");
        }

        logger.info("[ContinentsController][deleteContinent][flag: " + flag + "]");
        logger.info("[ContinentsController][deleteContinent][END]");

        return flag;
    }


}
