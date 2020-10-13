package com.analise.vendas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.analise.vendas.commons.ResultDTO;

public class ResultServiceTest {

	private List<String> lines;
	
	@Before
	public void init() {
		lines = new ArrayList<String>();
		lines.add("001ç1234567891234çPedroç50000");
		lines.add("001ç3245678865434çPauloç40000.99");
		lines.add("002ç2345675434544345çJose da SilvaçRural");
		lines.add("002ç2345675433444345çEduardo PereiraçRural");
		lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
		lines.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
	}
	
	@Test
	public void testResult() {
		ResultDTO result = ResultService.create().buildResult(lines);
		assertEquals(2L, result.getQuantityClients());
		assertEquals(2L, result.getQuantitySalesman());
		assertEquals(10L, result.getMostExpensiveSaleId());
		assertEquals("Paulo", result.getWorstSalesman());
	}

}
