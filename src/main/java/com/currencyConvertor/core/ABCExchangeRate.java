package com.currencyConvertor.core;

import java.util.HashMap;
import java.util.Map;

public class ABCExchangeRate {
    private Map<String, Double> CURRENCY_TO_RATE = new HashMap<String, Double>() {{
        put(Currency.USD, new Double(1));
        put(Currency.INR, new Double(71.52));
        put(Currency.CAD, new Double(1.3));
    }};

    public Double getRate(String currencyCode) {
        return CURRENCY_TO_RATE.get(currencyCode);
    }
}
