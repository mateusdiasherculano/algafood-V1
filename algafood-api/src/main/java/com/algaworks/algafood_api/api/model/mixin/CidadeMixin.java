package com.algaworks.algafood_api.api.model.mixin;

import org.springframework.boot.jackson.JacksonMixin;
import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JacksonMixin(Cidade.class)
public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;


}
