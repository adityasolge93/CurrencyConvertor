package com.currencyConvertor.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.InvalidParameterException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyManagerTest {
    @Mock
    private ABCExchangeRate abcExchangeRate;

    @Test
    public void convert_convertsToCADFromUSD() {
        when(abcExchangeRate.getRate(anyString()))
                .thenReturn(new Double(1))
                .thenReturn(new Double(2));
        Double expected = new Double(20);

        CurrencyManager currencyManager = new CurrencyManager(abcExchangeRate);
        Double usdAmount = currencyManager.convert(Currency.USD, Currency.CAD, new Double(10));

        assertThat(usdAmount, is(expected));

        InOrder inOrder = Mockito.inOrder(abcExchangeRate);
        inOrder.verify(abcExchangeRate).getRate(Currency.USD);
        inOrder.verify(abcExchangeRate).getRate(Currency.CAD);
    }

    @Test
    public void convert_throwsInvalidParameterException_forNegativeInputAmount() {
        CurrencyManager currencyManager = new CurrencyManager(abcExchangeRate);

        assertThrows(InvalidParameterException.class, () -> {
            currencyManager.convert(Currency.USD, Currency.CAD, new Double(-10));
        });

        verifyZeroInteractions(abcExchangeRate);
    }

    @Test
    public void convert_throwsInvalidParameterException_forAmountLessThanMinimumAmount() {
        CurrencyManager currencyManager = new CurrencyManager(abcExchangeRate);

        assertThrows(InvalidParameterException.class, () -> {
            currencyManager.convert(Currency.USD, Currency.CAD, new Double(5));
        });

        verifyZeroInteractions(abcExchangeRate);
    }
}