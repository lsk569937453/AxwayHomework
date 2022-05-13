package com.axway.currency.vojo;

public class ConvertResponse {
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
    private String fromAmount;
    /**
     * dst amount
     */
    private String toAmount;
    /**
     * the rate between the two currency
     */
    private String rate;
    /**
     *
     */
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
