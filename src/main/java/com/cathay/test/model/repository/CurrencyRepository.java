package com.cathay.test.model.repository;

import com.cathay.test.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    List<CurrencyEntity> findAll();

    List<CurrencyEntity> findByCode(String code);

    @Modifying
    @Transactional
    Long deleteByCode(String code);
}
