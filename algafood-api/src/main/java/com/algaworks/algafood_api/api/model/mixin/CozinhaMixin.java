package com.algaworks.algafood_api.api.model.mixin;

import org.springframework.boot.jackson.JacksonMixin;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@JacksonMixin(Cozinha.class)
public abstract class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes;
	
}