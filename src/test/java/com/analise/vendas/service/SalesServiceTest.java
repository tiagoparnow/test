package com.analise.vendas.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import com.analise.vendas.model.Sale;

public class SalesServiceTest {

	@Test
	public void testRegister() {
		String line = "003ç10ç[1-10-100]çPedro";
		Sale sale = (Sale) SalesService.create().register(line);
		assertEquals(10L, sale.getId());
		assertEquals("Pedro", sale.getSalesmanName());
		assertNotNull(sale.getItems());
		assertFalse(sale.getItems().isEmpty());
		assertEquals(1L, sale.getItems().get(0).getId());
		assertEquals(new BigDecimal("100"), sale.getItems().get(0).getPrice());
		assertEquals(10L, sale.getItems().get(0).getQuantity());
	}
	
}
