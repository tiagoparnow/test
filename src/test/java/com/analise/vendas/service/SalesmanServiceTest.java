package com.analise.vendas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.analise.vendas.model.Salesman;

public class SalesmanServiceTest {

	@Test
	public void testRegister() {
		String line = "001ç1234567891234çPedroç50000";
		Salesman salesman = (Salesman) SalesmanService.create().register(line);
		assertEquals("1234567891234", salesman.getCpf());
		assertEquals("Pedro", salesman.getName());
		assertEquals(new BigDecimal("50000"), salesman.getSalary());
	}
	
}
