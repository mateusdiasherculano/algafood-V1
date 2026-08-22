package com.algaworks.algafood_api.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {


	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
	}
	
}
