package com.analise.vendas.exception;

public class ProcessingApplicationException extends RuntimeException {

	private static final long serialVersionUID = -337825864096322756L;

	// constrói um objeto NumeroNegativoException com a mensagem passada por
	// parâmetro
	public ProcessingApplicationException(String msg) {
		super(msg);
	}

	// contrói um objeto NumeroNegativoException com mensagem e a causa dessa
	// exceção, utilizado para encadear exceptions
	public ProcessingApplicationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
