package com.analise.vendas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.analise.vendas.model.Item;

public class ItemServiceTest {

	@Test
	public void testRegister() {
		String line = "1-10-100";
		Item item = (Item) ItemService.create().register(line);
		assertEquals(1L, item.getId());
		assertEquals(new BigDecimal("100"), item.getPrice());
		assertEquals(10L, item.getQuantity());
	}
	
}
