package com.example.springbootrestgeoapi.rest;

import com.example.springbootrestgeoapi.model.Continent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContinentsControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ContinentsControllerTest.class);

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    private static final String BASE_URL = "/continents/";

    @Test
    public void testContinentsController() throws Exception {
        logger.info("[ContinentsControllerTest][testContinentsController][START]");
        logger.info("[ContinentsControllerTest][testContinentsController][Creating continent...]");

        String continentName = "Pangea";
        Continent continentForTest = new Continent(continentName);
        String continentJson = mapper.writeValueAsString(continentForTest);

        MvcResult resultCreate = mvc.perform(
            post(BASE_URL)
            .content(continentJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk())
                .andReturn();

        String strResponse = resultCreate.getResponse().getContentAsString();
        continentForTest = mapper.readValue(resultCreate.getResponse().getContentAsString(), Continent.class);

        assertNotNull(continentForTest.getId());
        assertTrue(continentForTest.getName().equals(continentName));

        logger.info("[ContinentsControllerTest][testContinentsController][strResponse: " + strResponse + "]");
        logger.info("[ContinentsControllerTest][testContinentsController][continentForTest: " + continentForTest + "]");


        logger.info("[ContinentsControllerTest][testContinentsController][Retrieving one continent...]");

        MvcResult resultsGetOne = mvc.perform
            (
                get(BASE_URL + continentForTest.getId())
                .accept(MediaType.APPLICATION_JSON)
            )
                .andReturn();

        continentForTest = mapper.readValue(resultsGetOne.getResponse().getContentAsString(), Continent.class);

        assertNotNull(continentForTest);
        assertTrue(continentForTest.getName().equals(continentName));



        logger.info("[ContinentsControllerTest][testContinentsController][continentForTest: " + continentForTest + "]");

        logger.info("[ContinentsControllerTest][testContinentsController][Updating continent...]");
        logger.info("[ContinentsControllerTest][testContinentsController][Continent id: " + continentForTest.getId() + "]");


        continentName = "The Pangea Continent";
        continentForTest.setName(continentName);

        continentJson = mapper.writeValueAsString(continentForTest);

        MvcResult resultsUpdate = mvc.perform(
            put(BASE_URL + continentForTest.getId())
            .content(continentJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk())
                .andReturn();

        strResponse = resultsUpdate.getResponse().getContentAsString();
        continentForTest = mapper.readValue(resultsUpdate.getResponse().getContentAsString(), Continent.class);

        assertTrue(continentForTest.getName().equals(continentName));

        logger.info("[ContinentsControllerTest][testContinentsController][strResponse: " + strResponse + "]");
        logger.info("[ContinentsControllerTest][testContinentsController][continentForTest: " + continentForTest + "]");


        logger.info("[ContinentsControllerTest][testContinentsController][Retrieving continents...]");

        MvcResult resultsGetAll = mvc.perform(
            get(BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        Continent[] continentsList = mapper.readValue(resultsGetAll.getResponse().getContentAsString(), Continent[].class);

        assertTrue(continentsList.length > 0);

        int continentsLength = continentsList.length;

        logger.info("[ContinentsControllerTest][testContinentsController][Continents count: " + continentsList.length + "]");


        logger.info("[ContinentsControllerTest][testContinentsController][Deleting continent - id: " + continentForTest.getId() + "]");

        MvcResult resultDelete = mvc.perform(
            delete(BASE_URL + continentForTest.getId())
            )
                .andExpect(status().isOk())
                .andReturn();

        strResponse = resultDelete.getResponse().getContentAsString();

        assertTrue(strResponse.equals("true"));

        logger.info("[ContinentsControllerTest][testContinentsController][strResponse: " + strResponse + "]");

        logger.info("[ContinentsControllerTest][testContinentsController][END]");
    }



}