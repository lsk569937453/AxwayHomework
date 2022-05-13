package com.axway.currency.constant;

public class CommonConstants {

    /**
     * the key of the convert of currency
     */
    public static final String CONVERT_CURRENCY_KEY = "cdata";

    /**
     * the key of the historical convert of currency
     */
    public static final String HISTORY_CONVERT_CURRENCY_KEY = "hcdata";

    /**
     * the time out of the rest template
     */
    public static final int TIMEOUT = 300000;

    /**
     *
     */
    public static final String EXCHANGE_URL = "https://api.apilayer.com/exchangerates_data/convert?to=%s&from=%s&amount=%s&date=%s";


}
