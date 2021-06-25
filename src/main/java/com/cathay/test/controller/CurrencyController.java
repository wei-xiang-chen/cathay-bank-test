package com.cathay.test.controller;

import com.cathay.test.pojo.Currency;
import com.cathay.test.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping(value = "/api/v1/currency")
    public ResponseEntity<?> getList(@RequestParam(name = "code", required = false) String code) throws Exception {
        if (code != null) code = code.toUpperCase();
        List<Currency> currencies = this.currencyService.findByCode(code);

        return new ResponseEntity(currencies, HttpStatus.OK);
    }

    @PostMapping(value = "/api/v1/currency")
    public ResponseEntity<?> create(@RequestBody Currency currency) throws Exception {

        Boolean result = this.currencyService.create(currency);

        if (result)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity("The Code is exist.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/api/v1/currency/{code}")
    public ResponseEntity<?> update(@PathVariable String code, @RequestBody Currency currency) throws Exception {
        currency.setCode(code.toUpperCase());
        Boolean result = this.currencyService.update(currency);

        if (result)
            return new ResponseEntity(currency, HttpStatus.OK);
        else
            return new ResponseEntity("The Code is exist.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/api/v1/currency/{code}")
    public ResponseEntity<?> delete(@PathVariable String code) throws Exception {

        Long result = this.currencyService.deleteByCode(code.toUpperCase());

        if (result > 0)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity("The Code is exist.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
