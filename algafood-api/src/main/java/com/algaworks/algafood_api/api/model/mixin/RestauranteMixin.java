package com.algaworks.algafood_api.api.model.mixin;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Endereco;
import com.algaworks.algafood_api.domain.model.FormaPagamento;
import com.algaworks.algafood_api.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import org.springframework.boot.jackson.JacksonMixin;
import com.algaworks.algafood_api.domain.model.Restaurante;

@JacksonMixin(Restaurante.class)
public class RestauranteMixin {
    
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private LocalDateTime dataCadastro;

	@JsonIgnore
	private LocalDateTime dataAtualizacao;

    @JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();
}
