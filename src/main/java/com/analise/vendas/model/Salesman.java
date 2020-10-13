package com.analise.vendas.model;

import java.math.BigDecimal;

public class Salesman extends Entity {

	private String cpf;
	private String name;
	private BigDecimal salary;

	public Salesman() {
	}

	public Salesman(String cpf, String name, BigDecimal salary) {
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public static synchronized Salesman create() {
		return new Salesman();
	}

	public Salesman withCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public Salesman withName(String name) {
		this.name = name;
		return this;
	}

	public Salesman withSalary(BigDecimal salary) {
		this.salary = salary;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
