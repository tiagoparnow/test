package com.analise.vendas.service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.analise.vendas.client.ServiceClient;
import com.analise.vendas.commons.ErrorDTO;
import com.analise.vendas.commons.ResultDTO;
import com.analise.vendas.model.Client;
import com.analise.vendas.model.Entity;
import com.analise.vendas.model.Item;
import com.analise.vendas.model.Sale;
import com.analise.vendas.model.Salesman;
import com.analise.vendas.model.domain.DomainLineId;

public class ResultService {

	private List<Entity> registers;
	private List<ErrorDTO> errors;

	public ResultService() {
		this.registers = new ArrayList<Entity>();
		this.errors = new ArrayList<ErrorDTO>();
	}
	
	public static ResultService create() {
		return new ResultService();
	}
	
	/**
	 * Analisa os dados do arquivo, retornando o resultado da analise
	 * 
	 * @param lines
	 * @return ResultDTO
	 */
	public ResultDTO buildResult(List<String> lines) {
		lines.stream()
			 .forEach(line -> process(line));
		
		return ResultDTO.create().withQuantityClients(this.getQuantityClients())
								 .withQuantitySalesman(this.getQuantitySalesman())
								 .withMostExpensiveSaleId(this.getMostExpensiveSaleId())
								 .withWorstSalesman(this.getWorstSalesman())
								 .withErrors(errors);
	}
	
	/**
	 * Processa a linha do arquivo, gerando seus registros de acordo
	 * com seu identificador. Os erros serao registrados para que
	 * a analise nao termine.
	 * 
	 * @param line
	 */
	public void process(String line) {
        try {
        	ServiceClient register = getService(line);
            registers.add(register.register(line));
		} catch (Exception e) {
			final String error = new StringBuilder().append("Trace -> ")
													.append(e.fillInStackTrace())
													.append(" / Message -> ")
													.append(e.getMessage())
													.toString();
			errors.add(ErrorDTO.create().withLine(line)
										.withError(error));
		}
    }
	
	/**
	 * Retorna a service de acordo com o identificador da linha
	 * atraves do mapeamento do dominio.
	 * 
	 * @param line
	 * @return ServiceClient
	 */
	private ServiceClient getService(String line) {
		DomainLineId tipo = DomainLineId.find(StringUtils.substring(line, 0, 3));
		ServiceClient service = null;

        switch (tipo) {
            case SALESMAN:
            	service = SalesmanService.create();
                break;
            case CLIENT:
            	service = ClientService.create();
                break;
            case SALES:
            	service = SalesService.create();
                break;
            default:
                throw new InvalidParameterException("Identificador da linha inválido");
        }
        return service;
    }
	
	/**
	 * Retorna a quantidade total de clientes
	 * 
	 * @return Long
	 */
	private Long getQuantityClients() {
        return registers.stream()
                		.filter(entity -> entity instanceof Client)
                		.count();
    }
	
	/**
	 * Retorna a quantidade total de vendedores
	 * 
	 * @return Long
	 */
	private Long getQuantitySalesman() {
        return registers.stream()
                		.filter(entity -> entity instanceof Salesman)
                		.count();
    }
	
	/**
	 * Retorna o identificador da venda mais cara
	 * 
	 * @return Long
	 */
	private Long getMostExpensiveSaleId() {
		BigDecimal mostExpensivePrice = BigDecimal.ZERO;
		Long mostExpensiveSaleId = 0L;

		for (Sale sale : getSales()) {
			BigDecimal total = getTotal(sale);
			if (mostExpensivePrice.compareTo(total) <= 0) {
				mostExpensiveSaleId = sale.getId();
				mostExpensivePrice = total;
			}
		}
		return mostExpensiveSaleId;
	}
	
	/**
	 * Retorna o nome do pior vendedor
	 * 
	 * @return String
	 */
	public String getWorstSalesman(){
        List<Sale> sales = getSales();
        if (CollectionUtils.isEmpty(sales)) {
        	return StringUtils.EMPTY;
        }
        BigDecimal worstSalePrice = getTotal(sales.get(0));
        Sale worstSale = sales.get(0);
        for (Sale sale : sales) {
            BigDecimal total = getTotal(sale);
        	if (worstSalePrice.compareTo(total) > 0){
                worstSalePrice = getTotal(sale);
                worstSale = sale;
            }
        }
        return worstSale.getSalesmanName();
    }
	
	/**
	 * Somatorio da venda
	 * 
	 * @param sale
	 * @return BigDecimal
	 */
	private BigDecimal getTotal(Sale sale) {
        return sale.getItems().stream()
        					  .map(Item::getPrice)
        					  .reduce(BigDecimal::add)
        					  .get();
    }
	
	/**
	 * Retorna vendas
	 * 
	 * @return List<Sale>
	 */
	public List<Sale> getSales() {
        return registers.stream()
                .filter(reg -> reg instanceof Sale)
                .map(reg -> (Sale) reg)
                .collect(Collectors.toList());
    }
}
