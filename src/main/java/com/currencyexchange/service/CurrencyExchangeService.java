package com.currencyexchange.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currencyexchange.moldel.Bill;

/**
 * @author Ram Brij
 * This class is used to call thrir-party to get exchange rate
 */
@Service
public class CurrencyExchangeService {

    @Value("${currency.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CurrencyExchangeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getExchangeRate(Bill bill) {
        String url = apiUrl + "/" + bill.getOriginalCurrency();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        
        if (response == null || !response.containsKey("rates")) {
            throw new RuntimeException("Failed to retrieve exchange rates");
        }

        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        return rates.get(bill.getTargetCurrency());
    }
}
