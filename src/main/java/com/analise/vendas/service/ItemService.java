package com.analise.vendas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.analise.vendas.client.ServiceClient;
import com.analise.vendas.model.Entity;
import com.analise.vendas.model.Item;

public class ItemService extends AbstractService implements ServiceClient {

	public static ItemService create() {
		return new ItemService();
	}
	
	public List<Item> buildItems(List<String> records) {
		return records.stream().map(r -> {
				return this.registerItem(r);
			}).collect(Collectors.toList());
	}
	
	public Item registerItem(String record) {
		return (Item) this.register(record);
	}
	
	@Override
	public Entity register(String record) {
		String[] itemSplit = record.split("-");
        return Item.create().withId(Long.valueOf(itemSplit[0]))
        					.withQuantity(Long.valueOf(itemSplit[1]))
        					.withPrice(new BigDecimal(itemSplit[2]));
	}

}
