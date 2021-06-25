package com.cathay.test.service;

import com.cathay.test.model.entity.CurrencyEntity;
import com.cathay.test.model.repository.CurrencyRepository;
import com.cathay.test.pojo.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Boolean create(Currency currency) throws Exception {

        List<CurrencyEntity> exist = this.currencyRepository.findByCode(currency.getCode());

        if (exist.size() == 0) {
            CurrencyEntity currencyEntity = new CurrencyEntity();
            currencyEntity.setCode(currency.getCode().toUpperCase());
            currencyEntity.setChinese(currency.getChinese());

            this.currencyRepository.save(currencyEntity);
            return true;
        }

        return false;
    }

    public Boolean update(Currency currency) throws Exception {

        List<CurrencyEntity> exist = this.currencyRepository.findByCode(currency.getCode());

        if (exist.size() != 0) {
            CurrencyEntity currencyEntity = exist.get(0);
            currencyEntity.setChinese(currency.getChinese());

            this.currencyRepository.save(currencyEntity);
            return true;
        }

        return false;
    }

    public List<Currency> findByCode(String code) throws Exception {

        List<Currency> currencies = new ArrayList<>();
        Currency currency;
        List<CurrencyEntity> currencyEntities;

        if (code != null)
            currencyEntities = this.currencyRepository.findByCode(code);
        else
            currencyEntities = this.currencyRepository.findAll();

        for (CurrencyEntity c : currencyEntities) {
            currency = new Currency();
            currency.setCode(c.getCode());
            currency.setChinese(c.getChinese());

            currencies.add(currency);
        }

        return currencies;
    }

    public Long deleteByCode(String code) throws Exception {
        return this.currencyRepository.deleteByCode(code);
    }
}
