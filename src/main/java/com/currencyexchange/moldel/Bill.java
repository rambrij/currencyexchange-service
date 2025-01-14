package com.currencyexchange.moldel;



import java.util.List;

import lombok.Data;

@Data
public class Bill {
    private List<Item> items;
    private String userType; // e.g. "employee", "affiliate", etc.
    private int customerTenure; // in years
    private String originalCurrency; // Original currency
    private String targetCurrency; // Target currency
}

