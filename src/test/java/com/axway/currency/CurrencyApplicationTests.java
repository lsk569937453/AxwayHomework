package com.axway.currency;

import com.axway.currency.constant.ErrorConstants;
import com.axway.currency.service.CommandLineService;
import com.axway.currency.vojo.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

@SpringBootTest
class CurrencyApplicationTests {
    @MockBean
    CommandLineRunner commandLineRunner;

    @Autowired
    CommandLineService commandLineService;

    //null input
    @Test
    public void currencyConvertError1() {
        String input = "";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.ARGS_ERROR);
        Assertions.assertEquals(baseResponse.getResponseObject(), ErrorConstants.ERROR_MAP.get(ErrorConstants.ARGS_ERROR));
    }

    //the input has error key
    @Test
    public void currencyConvertError2() {
        String input = "data={\"a\":123}";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.ARGS_ERROR);
        Assertions.assertEquals(baseResponse.getResponseObject(), ErrorConstants.ERROR_MAP.get(ErrorConstants.ARGS_ERROR));
    }

    //the input has error value
    @Test
    public void currencyConvertError3() {
        String input = "cdata=a";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.ARGS_ERROR);
        Assertions.assertEquals(baseResponse.getResponseObject(), ErrorConstants.ERROR_MAP.get(ErrorConstants.ARGS_ERROR));
    }

    //the input format is right,but the currency format is wrong
    @Test
    public void currencyConvertError4() {
        String input = "cdata={\"fromCurrency\":\"abc\",\"toCurrency\":\"def\",\"fromAmount\":30.222}";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.CALL_API_ERROR);
        Assertions.assertEquals(baseResponse.getResponseObject(), ErrorConstants.ERROR_MAP.get(ErrorConstants.CALL_API_ERROR));
    }

    //the input format is right,but the amount is wrong
    @Test
    public void currencyConvertError5() {
        String input = "cdata={\"fromCurrency\":\"eur\",\"toCurrency\":\"gbp\",\"fromAmount\":-30.222}";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.CALL_API_ERROR);
        Assertions.assertEquals(baseResponse.getResponseObject(), ErrorConstants.ERROR_MAP.get(ErrorConstants.CALL_API_ERROR));
    }

    //the input format is right,but the date format is wrong
    @Test
    public void currencyConvertError6() {
        String input = "hcdata={\"fromCurrency\":\"eur\",\"toCurrency\":\"gbp\",\"fromAmount\":30.222,\"date\":\"20200802\"}";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.CALL_API_ERROR);
        Assertions.assertEquals(baseResponse.getResponseObject(), ErrorConstants.ERROR_MAP.get(ErrorConstants.CALL_API_ERROR));
    }

    //convert the currency successfully
    @Test
    public void currencyConvertSuccess1() throws Exception {
        String input = "cdata={\"fromCurrency\":\"eur\",\"toCurrency\":\"gbp\",\"fromAmount\":30.222}";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.NORMAL);
    }
    //read the request from json file
    @Test
    public void currencyConvertSuccess2() throws Exception {
        File resource = new ClassPathResource("convertCurrency.json").getFile();
        String input = "cdata="+resource.getAbsolutePath();
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.NORMAL);
    }
    //read the request from yaml file
    @Test
    public void currencyConvertSuccess3() throws Exception {
        File resource = new ClassPathResource("convertCurrency.yml").getFile();
        String input = "cdata="+resource.getAbsolutePath();
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.NORMAL);
    }
    //convert the historical currency successfully
    @Test
    public void historicalCurrencyConvertSuccess1() throws Exception {
        String input = "hcdata={\"fromCurrency\":\"eur\",\"toCurrency\":\"gbp\",\"fromAmount\":30.222,\"date\":\"2022-05-10\"}";
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.NORMAL);
    }
    //read the request from json file
    @Test
    public void historicalCurrencyConvertSuccess2() throws Exception {
        File resource = new ClassPathResource("historicalConvertCurrency.json").getFile();
        String input = "hcdata="+resource.getAbsolutePath();
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.NORMAL);
    }
    //read the request from xml file
    @Test
    public void historicalCurrencyConvertSuccess3() throws Exception {
        File resource = new ClassPathResource("historicalConvertCurrency.yml").getFile();
        String input = "hcdata="+resource.getAbsolutePath();
        BaseResponse baseResponse = commandLineService.parseAndRequest(input);
        Assertions.assertEquals(baseResponse.getResponseCode(), ErrorConstants.NORMAL);
    }

}
