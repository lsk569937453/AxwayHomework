package com.axway.currency.vojo;

import java.math.BigDecimal;

public class ConvertCurrencyReq {
    /**
     * source currency
     */
    private String fromCurrency;
    /**
     * dst currency
     */
    private String toCurrency;
    /**
     * source amount
     */
    private BigDecimal fromAmount;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }
}
