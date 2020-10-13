package com.analise.vendas.model;

import java.util.List;

public class Sale extends Entity {

	private Long id;
	private List<Item> items;
	private String salesmanName;

	public Sale() {
	}

	public Sale(Long id, List<Item> items, String salesmanName) {
		this.id = id;
		this.items = items;
		this.salesmanName = salesmanName;
	}

	public static synchronized Sale create() {
		return new Sale();
	}

	public Sale withId(Long id) {
		this.id = id;
		return this;
	}

	public Sale withItems(List<Item> items) {
		this.items = items;
		return this;
	}

	public Sale withSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public void addItens(Item item) {
		this.items.add(item);
	}
}
