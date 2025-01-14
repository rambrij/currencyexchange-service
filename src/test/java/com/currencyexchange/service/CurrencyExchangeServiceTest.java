package com.currencyexchange.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.currencyexchange.moldel.Bill;

public class CurrencyExchangeServiceTest {

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertCurrency_WithDiscount() {
        // Given
        Bill bill = new Bill(); 
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");
        String response = "{\"rates\":{\"EUR\":0.85}}";

        // Mock the API response
        when(restTemplate.getForObject(any(String.class), any(Class.class)))
                .thenReturn(createMockResponse(response));

        // When
        double payableAmount = currencyExchangeService.getExchangeRate(bill);

        // Then
        assertEquals(0.85, payableAmount, 0.01);
    }

    private Map<String, Object> createMockResponse(String response) {
        Map<String, Object> rates = new HashMap<>();
        rates.put("rates", new HashMap<String, Double>() {{
            put("EUR", 0.85);
        }});

        return rates;
    }
}

