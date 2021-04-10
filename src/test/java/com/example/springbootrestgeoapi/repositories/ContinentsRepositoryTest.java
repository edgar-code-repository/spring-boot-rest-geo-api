package com.example.springbootrestgeoapi.repositories;

import com.example.springbootrestgeoapi.model.Continent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContinentsRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ContinentsRepositoryTest.class);

    @Autowired
    private ContinentsRepository continentsRepository;

    private Continent continent1;
    private Continent continent2;
    private Continent continent3;

    @Before
    public void setUp() throws Exception {
        continent1 = new Continent("Antártica");
        continent2 = new Continent("Asia");
        continent3 = new Continent("Africa");

        assertNull(continent1.getCode());
        assertNull(continent2.getCode());
        assertNull(continent3.getCode());

        continentsRepository.save(continent1);
        continentsRepository.save(continent2);
        continentsRepository.save(continent3);

        assertNotNull(continent1.getCode());
        assertNotNull(continent2.getCode());
        assertNotNull(continent3.getCode());

    }

    @After
    public void tearDown() throws Exception {
        continentsRepository.deleteById(continent1.getCode());
        continentsRepository.deleteById(continent2.getCode());
        continentsRepository.deleteById(continent3.getCode());
    }

    @Test
    public void testFetchData() {
        logger.info("[ContinentsRepositoryTest][testFetchData][START]");

        List<Continent> continents = continentsRepository.findAll();

        long count = 0;

        if (continents != null) {
            count = continents.stream().count();
        }

        assertTrue(count > 0);

        logger.info("[ContinentsRepositoryTest][testFetchData][count: " + count + "]");
        logger.info("[ContinentsRepositoryTest][testFetchData][END]");
    }

}