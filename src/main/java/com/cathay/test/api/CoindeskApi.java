package com.cathay.test.api;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoindeskApi {
    public static final String coindeskUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

    public ResponseEntity<String> getCoindesk() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(coindeskUrl, HttpMethod.GET, null, String.class);
    }
}
