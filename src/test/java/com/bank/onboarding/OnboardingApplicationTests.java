package com.bank.onboarding;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.bank.onboarding.controller.TaxCalculatorAPI;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { TaxCalculatorAPI.class })
@WebMvcTest

class OnboardingApplicationTests {

	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void testTax() throws Exception {
		mockMvc.perform(get("/calculatetax/120000")).andExpect(status().isOk())
				.andExpect(jsonPath("$.annualTax").value(27235.0))
				.andExpect(jsonPath("$.superAnnuation").value(12600.0));

	}
	@Test
	public void testTaxWithoutTaxamount() throws Exception {
		mockMvc.perform(get("/calculatetax/12000")).andExpect(status().isOk())
				.andExpect(jsonPath("$.annualTax").value(0.0))
				.andExpect(jsonPath("$.superAnnuation").value(1260.0));

	}

}
