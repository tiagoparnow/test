package com.analise.vendas.service;

import java.math.BigDecimal;

import com.analise.vendas.client.ServiceClient;
import com.analise.vendas.model.Entity;
import com.analise.vendas.model.Salesman;

public class SalesmanService extends AbstractService implements ServiceClient {

	public static SalesmanService create() {
		return new SalesmanService();
	}
	
	@Override
	public Entity register(String record) {
		 String[] salesmanSplit = record.split("ç");
	     return Salesman.create().withCpf(salesmanSplit[1])
	    		 				 .withName(salesmanSplit[2])
	    		 				 .withSalary(new BigDecimal(salesmanSplit[3]));   
	}

}
