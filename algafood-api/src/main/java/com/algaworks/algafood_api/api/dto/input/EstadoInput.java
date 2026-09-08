package com.algaworks.algafood_api.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.algaworks.algafood_api.core.validation.Groups;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class EstadoInput {

    @NotNull(groups = Groups.EstadoId.class)
    private Long id;
    
    @NotBlank 
    private String nome;
}
