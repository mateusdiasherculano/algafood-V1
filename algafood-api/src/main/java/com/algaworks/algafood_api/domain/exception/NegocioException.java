package com.algaworks.algafood_api.domain.exception;

public class NegocioException extends RuntimeException {


	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
