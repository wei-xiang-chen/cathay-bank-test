package com.cathay.test.controller;

import com.cathay.test.pojo.Currency;
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
public class CurrencyControllerTest {

    @Autowired
    protected MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String CURRENCY_CODE = "NTD";

    @DisplayName("Create currency test.")
    @Test
    @Order(1)
    protected void create() throws Exception {
        String uri = "/api/v1/currency";
        Currency currency = new Currency();
        currency.setCode(CURRENCY_CODE);
        currency.setChinese("臺幣");

        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.post(uri)
                    .content(this.objectMapper.writeValueAsString(currency))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("Get currency list test.")
    @Test
    @Order(2)
    protected void list() throws Exception {

        String uri = "/api/v1/currency";

        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString(Charset.forName("UTF8"));
        System.out.println(json);
    }

    @DisplayName("Update currency test.")
    @Test
    @Order(3)
    protected void update() throws Exception {
        String uri = "/api/v1/currency/{code}";
        Currency currency = new Currency();
        currency.setCode(CURRENCY_CODE);
        currency.setChinese("台幣");

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put(uri, CURRENCY_CODE)
                        .content(this.objectMapper.writeValueAsString(currency))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString(Charset.forName("UTF8"));
        System.out.println(json);
    }

    @DisplayName("Delete currency test.")
    @Test
    @Order(4)
    protected void delete() throws Exception {
        String uri = "/api/v1/currency/{code}";

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.delete(uri, CURRENCY_CODE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
