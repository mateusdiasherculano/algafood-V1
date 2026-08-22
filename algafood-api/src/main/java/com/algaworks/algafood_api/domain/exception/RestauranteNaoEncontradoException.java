package com.algaworks.algafood_api.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
	}
	
}
