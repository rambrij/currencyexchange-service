package com.currencyexchange.moldel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;
    private double price;
    private String category; // e.g. "grocery", "electronics", etc.
}

