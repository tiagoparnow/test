package com.analise.vendas.service;

import com.analise.vendas.client.ServiceClient;
import com.analise.vendas.model.Client;
import com.analise.vendas.model.Entity;

public class ClientService extends AbstractService implements ServiceClient {

	public static ClientService create() {
		return new ClientService();
	}
	
	@Override
	public Entity register(String record) {
		String[] clientSplit = record.split("ç");
		return Client.create().withCnpj(clientSplit[1])
							  .withName(clientSplit[2])
							  .withBusinessArea(clientSplit[3]);
	}

}
