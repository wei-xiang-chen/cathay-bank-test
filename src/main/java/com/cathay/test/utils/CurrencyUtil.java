package com.cathay.test.utils;

import com.cathay.test.model.entity.CurrencyEntity;
import com.cathay.test.model.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyUtil {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Map<String, String> getMap() {

        List<CurrencyEntity> currencyEntities = this.currencyRepository.findAll();
        Map<String, String> resultMap = currencyEntities.stream().collect(Collectors.toMap(CurrencyEntity::getCode, CurrencyEntity::getChinese));

        return  resultMap;
    }
}
