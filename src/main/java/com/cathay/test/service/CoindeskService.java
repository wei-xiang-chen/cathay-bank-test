package com.cathay.test.service;

import com.cathay.test.pojo.Coindesk;
import com.fasterxml.jackson.databind.JsonNode;

public interface CoindeskService {

    JsonNode getCoindesk() throws Exception;

    Coindesk getNewCoindesk() throws Exception;
}
