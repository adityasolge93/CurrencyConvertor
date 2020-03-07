package com.currencyConvertor;

import com.currencyConvertor.core.ABCExchangeRate;
import com.currencyConvertor.core.Currency;
import com.currencyConvertor.core.CurrencyManager;

public class CurrencyConvertorClient {
    public static void main(String[] args) {
        ABCExchangeRate abcExchangeRate = new ABCExchangeRate();
        CurrencyManager currencyManager = new CurrencyManager(abcExchangeRate);
        Double amount = currencyManager.convert(Currency.USD, Currency.INR, new Double(10));
        System.out.println(amount);
    }
}
