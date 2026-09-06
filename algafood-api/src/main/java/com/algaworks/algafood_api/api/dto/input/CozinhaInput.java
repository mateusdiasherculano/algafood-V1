package com.algaworks.algafood_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.algaworks.algafood_api.core.validation.Groups;


@Getter 
@Setter 
public class CozinhaInput {

    @NotNull(groups = Groups.CozinhaId.class) 
    private Long id;

    @NotBlank
    private String nome;

}
