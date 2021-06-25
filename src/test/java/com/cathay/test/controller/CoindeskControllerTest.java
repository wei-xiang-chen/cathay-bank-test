package com.cathay.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoindeskControllerTest {

    @Autowired
    protected MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Get new coindesk.")
    @Test
    @Order(1)
    protected void getCoindesk() throws Exception {

        String uri = "/api/v1/coindesk";

        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString(Charset.forName("UTF8"));


        System.out.println(json);
    }

    @DisplayName("Get new coindesk.")
    @Test
    @Order(2)
    protected void getNewCoindesk() throws Exception {

        String uri = "/api/v1/coindesk/new";

        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString(Charset.forName("UTF8"));


        System.out.println(json);
    }
}
