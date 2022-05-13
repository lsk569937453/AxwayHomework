package com.axway.currency.service.impl;

import com.axway.currency.configuration.AppConfigReader;
import com.axway.currency.constant.CommonConstants;
import com.axway.currency.constant.ErrorConstants;
import com.axway.currency.service.CurrencyService;
import com.axway.currency.vojo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    public static Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AppConfigReader appConfigReader;

    @Override
    public BaseResponse convertCurrency(ConvertCurrencyReq convertCurrencyReq) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String today = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date());
            ApiResponse apiResponse = request(convertCurrencyReq.getToCurrency(), convertCurrencyReq.getFromCurrency(), convertCurrencyReq.getFromAmount(), today);
            ConvertResponse convertResponse = new ConvertResponse();
            convertResponse.setFromCurrency(convertCurrencyReq.getFromCurrency());
            convertResponse.setToCurrency(convertCurrencyReq.getToCurrency());
            convertResponse.setRate(apiResponse.getInfo().getRate().toString());
            convertResponse.setFromAmount(convertCurrencyReq.getFromAmount().toString());
            convertResponse.setToAmount(apiResponse.getResult().toString());
            convertResponse.setDate(today);
            baseResponse.setResponseObject(convertResponse);
        } catch (Exception e) {
            logger.error("call remote api error:", e);
            baseResponse.setResponseCode(ErrorConstants.CALL_API_ERROR);
            baseResponse.setResponseObject(ErrorConstants.ERROR_MAP.get(ErrorConstants.CALL_API_ERROR));

        }


        return baseResponse;
    }

    /**
     * request the remote api
     *
     * @param toCurrency   the dst currency name
     * @param fromCurrency the source currency name
     * @param amount       the source amount
     * @param date         convert the currency in which day
     * @throws Exception
     */
    private ApiResponse request(String toCurrency, String fromCurrency, BigDecimal amount, String date) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", appConfigReader.getApikey());

        final String baseUrl = String.format(CommonConstants.EXCHANGE_URL, toCurrency, fromCurrency, amount, date);
        URI uri = new URI(baseUrl);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.GET, requestEntity, String.class);

        if (!result.getStatusCode().is2xxSuccessful()) {
            throw new Exception("the status code is not 2xx!The current status code is  " + result.getStatusCodeValue());
        }
        String response = result.getBody();

        if (response.contains("error")) {
            ApiErrorResponse apiErrorResponse = objectMapper.readValue(response, ApiErrorResponse.class);
            throw new Exception(objectMapper.writeValueAsString(apiErrorResponse));
        } else {
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            return apiResponse;
        }

    }

    @Override
    public BaseResponse historicalConvertCurrency(HistoryConvertCurrencyReq historyConvertCurrencyReq) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            ApiResponse apiResponse = request(historyConvertCurrencyReq.getToCurrency(), historyConvertCurrencyReq.getFromCurrency(), historyConvertCurrencyReq.getFromAmount(), historyConvertCurrencyReq.getDate());
            ConvertResponse convertResponse = new ConvertResponse();
            convertResponse.setFromCurrency(historyConvertCurrencyReq.getFromCurrency());
            convertResponse.setToCurrency(historyConvertCurrencyReq.getToCurrency());
            convertResponse.setRate(apiResponse.getInfo().getRate().toString());
            convertResponse.setFromAmount(historyConvertCurrencyReq.getFromAmount().toString());
            convertResponse.setToAmount(apiResponse.getResult().toString());
            convertResponse.setDate(apiResponse.getDate());

            baseResponse.setResponseObject(convertResponse);
        } catch (Exception e) {
            logger.error("call remote api error:", e);
            baseResponse.setResponseCode(ErrorConstants.CALL_API_ERROR);
            baseResponse.setResponseObject(ErrorConstants.ERROR_MAP.get(ErrorConstants.CALL_API_ERROR));

        }


        return baseResponse;
    }
}
