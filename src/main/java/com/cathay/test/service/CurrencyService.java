package com.cathay.test.service;

import com.cathay.test.model.entity.CurrencyEntity;
import com.cathay.test.pojo.Currency;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface CurrencyService {

    Boolean create(Currency currency) throws Exception;

    Boolean update(Currency currency) throws Exception;

    List<Currency> findByCode(String code) throws Exception;

    Long deleteByCode(String code) throws Exception;
}
