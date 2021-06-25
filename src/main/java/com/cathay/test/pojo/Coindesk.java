package com.cathay.test.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class Coindesk {

    @JsonFormat(pattern="yyyy-MM-dd hh:MM:ss")
    private LocalDateTime updateTime;

    private List<Currency> currency;

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<Currency> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Currency> currency) {
        this.currency = currency;
    }
}
