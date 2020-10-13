package com.analise.vendas.commons;

public class ErrorDTO {

	private String line;
	private String error;

	public ErrorDTO() {
	}

	public ErrorDTO(String line, String error) {
		super();
		this.line = line;
		this.error = error;
	}

	public static synchronized ErrorDTO create() {
		return new ErrorDTO();
	}

	public ErrorDTO withLine(String line) {
		this.line = line;
		return this;
	}

	public ErrorDTO withError(String error) {
		this.error = error;
		return this;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Erro na linha: ")
								  .append(line)
								  .append(" - ")
								  .append("Mensagem: ")
								  .append(error)
								  .toString();
	}
	
}
