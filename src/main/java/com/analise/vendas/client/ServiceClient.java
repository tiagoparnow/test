package com.analise.vendas.client;

import com.analise.vendas.model.Entity;

public interface ServiceClient {

	/**
	 * Criar a entidade atraves dos separadores de 
	 * suas respectivas linhas.
	 * 
	 * @param register
	 * @return Entity
	 */
	abstract Entity register(String register);
	
}
