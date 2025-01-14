package com.currencyexchange.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.currencyexchange.moldel.Bill;
import com.currencyexchange.moldel.Item;

public class DiscountServiceTest {

	@InjectMocks
	private DiscountService myCurrencyExchangeService;

	@Mock
	private CurrencyExchangeService currencyExchangeService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName(value = "when customer is employee")
	public void shouldCalculateDiscountWhenCustomer_employee() {
		// Given
		Bill request = new Bill();
		Item item1 = new Item();
		item1.setPrice(200);
		item1.setCategory("electronics");

		Item item2 = new Item();
		item2.setPrice(100);
		item2.setCategory("mobile");

		request.setItems(Arrays.asList(item1, item2));
		request.setUserType("employee");
		request.setCustomerTenure(2);

		when(currencyExchangeService.getExchangeRate(request)).thenReturn(.976); // Assume 1:1 exchange rate

		// When
		double payableAmount = myCurrencyExchangeService.calculatePayableAmount(request);

		// Then
		assertEquals(190.32, payableAmount,
				"Payable amount should match the expected value with the employee discount.");
	}

	@Test
	@DisplayName(value = "when customer is employee and has grocery")
	public void shouldCalculateDiscountWhenCustomerIsEmployee_grocery() {
		// Given
		Bill request = new Bill();
		Item item1 = new Item();
		item1.setPrice(200);
		item1.setCategory("electronics");

		Item item2 = new Item();
		item2.setPrice(100);
		item2.setCategory("grocery");

		request.setItems(Arrays.asList(item1, item2));
		request.setUserType("employee");
		request.setCustomerTenure(2);

		when(currencyExchangeService.getExchangeRate(request)).thenReturn(.976); // Assume .976 exchange rate

		// When
		double payableAmount = myCurrencyExchangeService.calculatePayableAmount(request);

		// Then
		assertEquals(219.6, payableAmount,
				"Payable amount should match the expected value with the employee discount.");
	}

	@Test
	@DisplayName(value = "when customer is affiliate")
	public void shouldCalculateDiscountWithFlatDiscountWhenCustomer_affiliate() {
        // Given	    	
        Bill request = new Bill();
	    Item item1 = new Item();
	    item1.setPrice(200);
	    item1.setCategory("electronics");
	    
	    Item item2 = new Item();
	    item2.setPrice(100);
	    item2.setCategory("mobile");

	    request.setItems(Arrays.asList(item1, item2));
	    request.setUserType("affiliate");
	    request.setCustomerTenure(2);
    	
        when(currencyExchangeService.getExchangeRate(request)).thenReturn(.976); // Assume .976 exchange rate

        // When
        double payableAmount = myCurrencyExchangeService.calculatePayableAmount(request);

        // Then
        assertEquals(248.88, payableAmount, "Payable amount should match the expected value with the affiliate discount.");
    }

	@Test
	@DisplayName(value = "when customer is affiliate and has grocery")
	public void shouldCalculateDiscountWhenCustomerIsAffiliate_grocery() {
        // Given	    	
        Bill request = new Bill();
	    Item item1 = new Item();
	    item1.setPrice(200);
	    item1.setCategory("electronics");
	    
	    Item item2 = new Item();
	    item2.setPrice(100);
	    item2.setCategory("grocery");

	    request.setItems(Arrays.asList(item1, item2));
	    request.setUserType("affiliate");
	    request.setCustomerTenure(2);
    	
        when(currencyExchangeService.getExchangeRate(request)).thenReturn(.976); // Assume .976 exchange rate

        // When
        double payableAmount = myCurrencyExchangeService.calculatePayableAmount(request);

        // Then
        assertEquals(258.64, payableAmount, "Payable amount should match the expected value with the affiliate discount.");
    }

	@Test
	@DisplayName(value = "when Customer Tenure is over 2 years")
	public void shouldCalculateDiscountWithFlatDiscountWhenCustomerTenure_over2year() {
        // Given	    	
        Bill request = new Bill();
	    Item item1 = new Item();
	    item1.setPrice(200);
	    item1.setCategory("electronics");
	    
	    Item item2 = new Item();
	    item2.setPrice(100);
	    item2.setCategory("mobile");

	    request.setItems(Arrays.asList(item1, item2));
	    request.setUserType("regular");
	    request.setCustomerTenure(3);
    	
        when(currencyExchangeService.getExchangeRate(request)).thenReturn(.976); // Assume .976 exchange rate

        // When
        double payableAmount = myCurrencyExchangeService.calculatePayableAmount(request);

        // Then
        assertEquals(263.52, payableAmount, "Payable amount should match the expected value with the over 2 years discount.");
    }

	@Test
	@DisplayName(value = "when Customer Tenure is over 2 years and has grocery")
	public void shouldCalculateDiscountWithFlatDiscountWhenCustomerTenure_over2year_grocery() {
        // Given	    	
        Bill request = new Bill();
	    Item item1 = new Item();
	    item1.setPrice(200);
	    item1.setCategory("electronics");
	    
	    Item item2 = new Item();
	    item2.setPrice(100);
	    item2.setCategory("grocery");

	    request.setItems(Arrays.asList(item1, item2));
	    request.setUserType("regular");
	    request.setCustomerTenure(3);
    	
        when(currencyExchangeService.getExchangeRate(request)).thenReturn(.976); // Assume .976 exchange rate

        // When
        double payableAmount = myCurrencyExchangeService.calculatePayableAmount(request);

        // Then
        assertEquals(268.4, payableAmount, "Payable amount should match the expected value with the over 2 years discount.");
    }

}
