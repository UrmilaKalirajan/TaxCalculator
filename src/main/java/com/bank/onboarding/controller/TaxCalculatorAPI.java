package com.bank.onboarding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.onboarding.model.TaxResponse;

@RestController
@RequestMapping(path = "/calculatetax")
public class TaxCalculatorAPI {

	Logger log = LoggerFactory.getLogger(TaxCalculatorAPI.class);

	@GetMapping(path = "/{salary}")
	public ResponseEntity<TaxResponse> getTax(@PathVariable Double salary) {

		double superAnnuation = (salary * 10.5) / 100;
		salary = salary - superAnnuation;
		double incomeTaxAmt = calculateIncomeTax(salary);

		TaxResponse taxResponse = new TaxResponse();
		taxResponse.setAnnualTax(incomeTaxAmt);
		taxResponse.setSuperAnnuation(superAnnuation);

		return ResponseEntity.status(HttpStatus.OK).body(taxResponse);
	}

	public static double calculateIncomeTax(double i) {

		double tax;
		if (i <= 18200)
			tax = 0;
		else if (i <= 37000)
			tax = 0.19 * (i - 18200);
		else if (i <= 90000)
			tax = (0.325 * (i - 37000)) + 3572;
		else if (i <= 180000)
			tax = (0.37 * (i - 90000)) + 20797;
		else
			tax = (0.45 * (i - 180000)) + 54097;
		return tax;

	}

}
