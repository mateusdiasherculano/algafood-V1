package com.algaworks.algafood_api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.service.CozinhaService;
import jakarta.validation.ConstraintViolationException;
import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;


@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CozinhaService cozinhaService;

	@Test
	public void deveCadastrarCozinhaComNome() {

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Chinesa");

		Cozinha novaCozinha = cozinhaService.salvar(cozinha);

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
		assertThat(novaCozinha.getNome()).isEqualTo("Chinesa");
	}

	@Test
	public void deveFalharQuandoCadastrarCozinhaSemNome() {

		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);

		assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(cozinha));
	}

	@Test 
	public void deveFalharQuandoExcluirCozinhaEmUso() {
		EntidadeEmUsoException erroEsperado = assertThrows(EntidadeEmUsoException.class,
			() -> cozinhaService.excluir(1L));

		assertThat(erroEsperado).isNotNull();
	}
}
