package com.analise.vendas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.analise.vendas.model.Client;

public class ClientServiceTest {

	@Test
	public void testRegister() {
		String line = "002ç2345675434544345çJose da SilvaçRural";
		Client client = (Client) ClientService.create().register(line);
		assertEquals("Rural", client.getBusinessArea());
		assertEquals("2345675434544345", client.getCnpj());
		assertEquals("Jose da Silva", client.getName());
	}
	
}
