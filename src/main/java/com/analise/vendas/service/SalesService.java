package com.analise.vendas.service;

import java.util.Arrays;
import java.util.List;

import com.analise.vendas.client.ServiceClient;
import com.analise.vendas.model.Entity;
import com.analise.vendas.model.Item;
import com.analise.vendas.model.Sale;

public class SalesService extends AbstractService implements ServiceClient {

	public static SalesService create() {
		return new SalesService();
	}
	
	@Override
	public Entity register(String record) {
		String[] saleSplit = record.split("ç");
        return Sale.create().withId(Long.valueOf(saleSplit[1]))
        					.withItems(this.getItems(saleSplit[2]))
        					.withSalesmanName(saleSplit[3]);
	}
	
	private List<Item> getItems(String recordItem){
        List<String> items = Arrays.asList(recordItem.replace("[", "").replace("]", "").split(","));
        return ItemService.create().buildItems(items);
    }

}
