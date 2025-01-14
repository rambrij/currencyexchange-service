package com.currencyexchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyexchange.moldel.Bill;
import com.currencyexchange.moldel.Item;

/**
 * @author Ram Brij
 * This class is used to calculate discount amount
 */
@Service
public class DiscountService {

	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	public double calculatePayableAmount(Bill bill) {
		double totalAmount = calculateTotalAmount(bill.getItems());
		return calculateDiscount(totalAmount, bill);
	}

	private double calculateDiscount(double totalAmount, Bill bill) {
		double discount = 0.0;
		double totalAmountEligibleForDiscount = bill.getItems().stream()
				.filter(item -> !"grocery".equalsIgnoreCase(item.getCategory())).mapToDouble(Item::getPrice).sum();

		double userTypeDiscount = applyUserTypeDiscount(bill);
		discount = totalAmountEligibleForDiscount * userTypeDiscount;
		discount += getFlatDiscount(totalAmount);
		double discountedAmount = totalAmount - discount;

		double exchangeRate = currencyExchangeService.getExchangeRate(bill);
		return discountedAmount * exchangeRate;

	}

	private double applyUserTypeDiscount(Bill bill) {
		double discount = 0.0;
		if (bill.getUserType().equalsIgnoreCase("employee")) {
			discount = 0.30; // 30% discount
		} else if (bill.getUserType().equalsIgnoreCase("affiliate")) {
			discount = 0.10; // 10% discount
		} else if (bill.getCustomerTenure() > 2) {
			discount = 0.05; // 5% discount for customers over 2 years
		}
		return discount;
	}

	private double calculateTotalAmount(List<Item> items) {
		return items.stream().mapToDouble(Item::getPrice).sum();
	}

	private double getFlatDiscount(double totalAmount) {
		return Math.floor(totalAmount / 100) * 5; // $5 for every $100
	}

}
