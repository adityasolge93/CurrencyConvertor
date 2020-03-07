package com.currencyConvertor.core;

import java.security.InvalidParameterException;

public class CurrencyManager {
    private static final double MIN_AMOUNT = 10;

    private ABCExchangeRate abcExchangeRate;

    public CurrencyManager(ABCExchangeRate abcExchangeRate) {
        this.abcExchangeRate = abcExchangeRate;
    }

    public Double convert(String from, String to, Double amount) {
        if (amount < MIN_AMOUNT) {
            throw new InvalidParameterException(String.format("Currency amount cannot be less than %s", MIN_AMOUNT));
        }

        Double fromCurrencyUSDRate = abcExchangeRate.getRate(from);
        Double toCurrencyUSDRate = abcExchangeRate.getRate(to);
        return (amount/fromCurrencyUSDRate)*toCurrencyUSDRate;
    }
}
