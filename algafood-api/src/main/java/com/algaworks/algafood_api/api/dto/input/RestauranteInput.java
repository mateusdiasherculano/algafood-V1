package com.algaworks.algafood_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import com.algaworks.algafood_api.core.validation.Groups;


@Getter 
@Setter 
public class RestauranteInput {

    @NotBlank 
    private String nome;

    @NotNull 
    @PositiveOrZero 
    private BigDecimal taxaFrete;

    @Valid 
    @NotNull 
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    private CozinhaInput cozinha;
}
