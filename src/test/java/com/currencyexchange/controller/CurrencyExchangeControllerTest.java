package com.currencyexchange.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.currencyexchange.moldel.Bill;
import com.currencyexchange.service.DiscountService;

public class CurrencyExchangeControllerTest {

    @InjectMocks
    private CurrencyController currencyExchangeController;

    @Mock
    private DiscountService currencyExchangeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculate_Success() {
        // Arrange
    	Bill bill = new Bill();
        bill.setUserType("employee");
        bill.setCustomerTenure(3);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");
        // Assume the calculation will return 132.5
        when(currencyExchangeService.calculatePayableAmount(any(Bill.class))).thenReturn(132.5);

        // Act
        ResponseEntity<Double> response = currencyExchangeController.calculatePayable(bill);

        // Assert
        assertEquals(132.5, response.getBody());
        assertTrue(response.getStatusCode().is2xxSuccessful()); // HTTP status 200
    }

    @Test
    public void testCalculate_InvalidBill() {
        // Arrange
    	Bill bill = new Bill();
        bill.setUserType("employee");
        bill.setCustomerTenure(3);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("INVALID");
        when(currencyExchangeService.calculatePayableAmount(any(Bill.class))).thenThrow(new RuntimeException("Invalid bill"));

        // Act & Assert
        Exception exception = null;
        try {
            currencyExchangeController.calculatePayable(bill);
        } catch (RuntimeException e) {
            exception = e;
        }

        assertEquals("Invalid bill", exception.getMessage());
    }
}
