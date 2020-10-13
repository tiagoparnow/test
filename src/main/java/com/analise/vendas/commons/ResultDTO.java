package com.analise.vendas.commons;

import java.util.List;

public class ResultDTO {

	private Long quantityClients;
	private Long quantitySalesman;
	private Long mostExpensiveSaleId;
	private String worstSalesman;
	private List<ErrorDTO> errors;
	
	public ResultDTO() {
	}

	public ResultDTO(Long quantityClients, Long quantitySalesman, Long mostExpensiveSaleId, String worstSalesman, List<ErrorDTO> errors) {
		super();
		this.quantityClients = quantityClients;
		this.quantitySalesman = quantitySalesman;
		this.mostExpensiveSaleId = mostExpensiveSaleId;
		this.worstSalesman = worstSalesman;
		this.setErrors(errors);
	}

	public static synchronized ResultDTO create() {
		return new ResultDTO();
	}

	public ResultDTO withQuantityClients(Long quantityClients) {
		this.quantityClients = quantityClients;
		return this;
	}

	public ResultDTO withQuantitySalesman(Long quantitySalesman) {
		this.quantitySalesman = quantitySalesman;
		return this;
	}

	public ResultDTO withMostExpensiveSaleId(Long mostExpensiveSaleId) {
		this.mostExpensiveSaleId = mostExpensiveSaleId;
		return this;
	}

	public ResultDTO withWorstSalesman(String worstSalesman) {
		this.worstSalesman = worstSalesman;
		return this;
	}
	
	public ResultDTO withErrors(List<ErrorDTO> errors) {
		this.errors = errors;
		return this;
	}

	public Long getQuantityClients() {
		return quantityClients;
	}

	public void setQuantityClients(Long quantityClients) {
		this.quantityClients = quantityClients;
	}

	public Long getQuantitySalesman() {
		return quantitySalesman;
	}

	public void setQuantitySalesman(Long quantitySalesman) {
		this.quantitySalesman = quantitySalesman;
	}

	public Long getMostExpensiveSaleId() {
		return mostExpensiveSaleId;
	}

	public void setMostExpensiveSaleId(Long mostExpensiveSaleId) {
		this.mostExpensiveSaleId = mostExpensiveSaleId;
	}

	public String getWorstSalesman() {
		return worstSalesman;
	}

	public void setWorstSalesman(String worstSalesman) {
		this.worstSalesman = worstSalesman;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDTO> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return String.format("%dç%dç%02dç%s", 
							 quantityClients,
							 quantitySalesman,
							 mostExpensiveSaleId,
							 worstSalesman);
	}
}
