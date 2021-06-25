package com.cathay.test.service;

import com.cathay.test.api.CoindeskApi;
import com.cathay.test.pojo.Coindesk;
import com.cathay.test.pojo.Currency;
import com.cathay.test.utils.CurrencyUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CoindeskServiceImpl implements CoindeskService {

    @Autowired
    private CoindeskApi coindeskApi;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CurrencyUtil currencyUtil;

    private static final String TIME = "time";
    private static final String UPDATE_TIME = "updatedISO";
    private static final String CURRENCY = "bpi";
    private static final String CURRENCY_CODE = "code";
    private static final String RATE = "rate_float";

    public Coindesk getNewCoindesk()  throws Exception {
        String updateTime, currencyCode;
        JsonNode responseEntity, currencies;
        Coindesk coindeskModel = new Coindesk();
        List<Currency> currencyModels = new ArrayList<>();
        Currency currencyModel;
        Map<String, String> currencyMap = this.currencyUtil.getMap();

        try {
            responseEntity = this.getCoindesk();
            updateTime = responseEntity.get(TIME).get(UPDATE_TIME).asText();
            coindeskModel.setUpdateTime(LocalDateTime.parse(updateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME));

            currencies = responseEntity.get(CURRENCY);
            for (JsonNode currency : currencies) {
                currencyModel = new Currency();
                currencyCode = currency.get(CURRENCY_CODE).asText();
                currencyModel.setCode(currencyCode);
                currencyModel.setChinese(currencyMap.get(currencyCode));
                currencyModel.setRate(currency.get(RATE).asDouble());
                currencyModels.add(currencyModel);
            }

            coindeskModel.setCurrency(currencyModels);
        } catch (Exception e) {
            throw e;
        }

        return coindeskModel;
    }

    public JsonNode getCoindesk()  throws Exception {
        String responseString, updateTime, currencyCode;
        JsonNode responseEntity, currencies;

        try {
            responseString = this.coindeskApi.getCoindesk().getBody();
            responseEntity = this.objectMapper.readTree(responseString);

        } catch (Exception e) {
            throw e;
        }

        return responseEntity;
    }
}
