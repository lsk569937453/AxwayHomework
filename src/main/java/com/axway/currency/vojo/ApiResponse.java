package com.axway.currency.vojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * {
 * "date": "2020-04-13",
 * "historical": true,
 * "info": {
 * "rate": 0.872036,
 * "timestamp": 1586822399
 * },
 * "query": {
 * "amount": 3,
 * "from": "EUR",
 * "to": "GBP"
 * },
 * "result": 2.616108,
 * "success": true
 * }
 */
public class ApiResponse {

    /**
     * the rateinfo
     */
    private Info info;
    /**
     * the request
     */
    private Query query;
    /**
     * the convert result
     */
    private BigDecimal result;

    @JsonIgnore
    private String historical;

    /**
     * request success or not
     */
    private boolean success;
    /**
     * use which day rate
     */
    private String date;

    public String getHistorical() {
        return historical;
    }

    public void setHistorical(String historical) {
        this.historical = historical;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class Info {
        @JsonIgnore
        private long timestamp;
        private BigDecimal rate;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }
    }

    public static class Query {
        private BigDecimal amount;
        private String from;
        private String to;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}
