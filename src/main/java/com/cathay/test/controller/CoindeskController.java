package com.cathay.test.controller;

import com.cathay.test.pojo.Coindesk;
import com.cathay.test.service.CoindeskService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @GetMapping(value = "/api/v1/coindesk/new")
    public ResponseEntity<?> getNewCoindesk() {

        Coindesk coindeskModel;

        try {
            coindeskModel = this.coindeskService.getNewCoindesk();
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(coindeskModel, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/coindesk")
    public ResponseEntity<?> getCoindesk() {

        JsonNode jsonNode;

        try {
            jsonNode = this.coindeskService.getCoindesk();
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(jsonNode, HttpStatus.OK);
    }
}
