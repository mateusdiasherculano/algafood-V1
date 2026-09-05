package com.algaworks.algafood_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Getter 
@Setter 
public class CozinhaInput {

    @NotNull 
    private Long id;

}
