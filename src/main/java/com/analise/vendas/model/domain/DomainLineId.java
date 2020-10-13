package com.analise.vendas.model.domain;

import java.util.Arrays;

public enum DomainLineId {

	SALESMAN ("001", "Data Salesman"),
	CLIENT	 ("002", "Data Client"),
	SALES	 ("003", "Data Sales");
	
	private String value;

	private String description;
	
	
	private DomainLineId(final String value, final String description) {
		this.value = value;
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	public static DomainLineId find(final String value) {
		return Arrays
				.asList(DomainLineId.values())
				.stream()
				.filter(filtro -> filtro.value.equals(value))
				.findFirst()
				.orElse(null);
	}
}
