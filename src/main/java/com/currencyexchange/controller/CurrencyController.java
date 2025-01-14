package com.currencyexchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchange.moldel.Bill;
import com.currencyexchange.service.DiscountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Ram Brij
 * This class is used to handle currency exchange request
 */
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final DiscountService discountService;

    public CurrencyController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Operation(
        summary = "Calculate payable amount",
        description = "This endpoint calculates the payable amount based on the bill and currency exchange rates."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully calculated the payable amount"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/calculate")
    public ResponseEntity<Double> calculatePayable(
        @Parameter(description = "Bill request containing amount and currency details") @RequestBody Bill request) {
        
        Double response = discountService.calculatePayableAmount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
