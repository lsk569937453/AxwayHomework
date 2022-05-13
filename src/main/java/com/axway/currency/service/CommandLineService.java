package com.axway.currency.service;

import com.axway.currency.vojo.BaseResponse;

public interface CommandLineService {

    /**
     * resolve the args and get the result of the convert
     * @param args
     */
    public BaseResponse parseAndRequest(String args);
}
