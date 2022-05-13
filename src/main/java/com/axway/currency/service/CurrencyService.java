package com.axway.currency.service;

import com.axway.currency.vojo.BaseResponse;
import com.axway.currency.vojo.ConvertCurrencyReq;
import com.axway.currency.vojo.HistoryConvertCurrencyReq;

public interface CurrencyService {
    /**
     * convert currency
     *
     * @return
     */
    public BaseResponse convertCurrency(ConvertCurrencyReq convertCurrencyReq);

    /**
     * historical convert currency
     *
     * @return
     */
    public BaseResponse historicalConvertCurrency(HistoryConvertCurrencyReq historyConvertCurrencyReq);
}
