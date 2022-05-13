package com.axway.currency.service.impl;

import com.axway.currency.constant.CommonConstants;
import com.axway.currency.constant.ErrorConstants;
import com.axway.currency.service.CommandLineService;
import com.axway.currency.service.CurrencyService;
import com.axway.currency.vojo.BaseResponse;
import com.axway.currency.vojo.ConvertCurrencyReq;
import com.axway.currency.vojo.HistoryConvertCurrencyReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CommandLineServiceImpl implements CommandLineService {
    public static Logger logger = LoggerFactory.getLogger(CommandLineServiceImpl.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CurrencyService currencyService;


    @Override
    public BaseResponse parseAndRequest(String args) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            //check args and unmarshall the data
            baseResponse = parse(args);
            if (baseResponse.getResponseCode() != ErrorConstants.NORMAL) {
                return baseResponse;
            }
            //separate the different param
            if (baseResponse.getResponseObject().getClass().equals(ConvertCurrencyReq.class)) {
                baseResponse = currencyService.convertCurrency((ConvertCurrencyReq) baseResponse.getResponseObject());
            } else {
                baseResponse = currencyService.historicalConvertCurrency((HistoryConvertCurrencyReq) baseResponse.getResponseObject());
            }

        } catch (Exception e) {
            logger.error("parseAndRequest error", e);
        }
        return baseResponse;
    }

    /**
     * parse request and unmarshall data
     *
     * @param args
     * @return
     */
    public BaseResponse parse(String args) {
        BaseResponse baseResponse = new BaseResponse();
        String[] strings = args.split("=");

        //the length of the args should be 2
        if (strings.length != 2) {
            //the key of the param should be cdata or hcdata
            boolean flag = strings[0].equals(CommonConstants.CONVERT_CURRENCY_KEY) || strings[0].equals(CommonConstants.HISTORY_CONVERT_CURRENCY_KEY);
            if (!flag) {
                baseResponse.setResponseCode(ErrorConstants.ARGS_ERROR);
                baseResponse.setResponseObject(ErrorConstants.ERROR_MAP.get(ErrorConstants.ARGS_ERROR));
                return baseResponse;
            }
        }
        Object unmarshallObject = null;
        try {
            //unmarshall data with two different class
            if (strings[0].equals(CommonConstants.HISTORY_CONVERT_CURRENCY_KEY)) {
                unmarshallObject = unmarshallData(strings[1], HistoryConvertCurrencyReq.class);
            } else {
                unmarshallObject = unmarshallData(strings[1], ConvertCurrencyReq.class);
            }
        } catch (Exception e) {
            logger.error("marshallData error:", e);
            baseResponse.setResponseCode(ErrorConstants.ARGS_ERROR);
            baseResponse.setResponseObject(ErrorConstants.ERROR_MAP.get(ErrorConstants.ARGS_ERROR));
            return baseResponse;
        }

        baseResponse.setResponseObject(unmarshallObject);
        return baseResponse;
    }

    /**
     * check string is json or file
     *
     * @param data
     * @return
     */
    private <T> T unmarshallData(String data, Class<T> tClass) throws Exception {
        //the input is json data.
        if (isValidJson(data)) {
            T t = objectMapper.readValue(data, tClass);
            return t;
        } else {
            //the input is json file and yml file
            if (!data.contains(".json") && !data.contains(".yml")) {
                throw new Exception("can not find the file in the command line!");
            }
            Path p = Paths.get(data);
            //is absolute
            if (p.isAbsolute()) {
                T t = objectMapper.readValue(p.toFile(), tClass);
                return t;
            } else {
                //get the current dir
                Path currentRelativePath = Paths.get("");
                //get the absolut path of file
                Path filePath = Paths.get(currentRelativePath.toString(), data);

                T t = objectMapper.readValue(filePath.toFile(), tClass);
                return t;
            }
        }

    }

    /**
     * check string is validJson
     *
     * @param json
     * @return
     */
    private boolean isValidJson(String json) {
        try {
            ObjectMapper newObjectMapper = new ObjectMapper();
            newObjectMapper.readTree(json);

        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }
}
