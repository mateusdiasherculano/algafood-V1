package com.algaworks.algafood_api.api.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.groups.Default;
import com.algaworks.algafood_api.core.validation.Groups;

@Getter 
@Setter 
public class CidadeInput {

    private Long id;

    @NotBlank 
    private String nome;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    @Valid
    @NotNull 
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    private EstadoInput estado;
}
