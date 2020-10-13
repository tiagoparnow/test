package com.analise.vendas.model;

import java.math.BigDecimal;

public class Item extends Entity {

	private Long id;
	private Long quantity;
	private BigDecimal price;

	public Item() {
	}

	public Item(Long id, Long quantity, BigDecimal price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public static synchronized Item create() {
		return new Item();
	}

	public Item withId(Long id) {
		this.id = id;
		return this;
	}

	public Item withQuantity(Long quantity) {
		this.quantity = quantity;
		return this;
	}

	public Item withPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
