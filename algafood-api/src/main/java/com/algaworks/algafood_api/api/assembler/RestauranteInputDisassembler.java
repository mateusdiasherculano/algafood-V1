package com.algaworks.algafood_api.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood_api.api.dto.input.RestauranteInput;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;

@Component 
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());
        
        restaurante.setCozinha(cozinha);
        
        return restaurante;
    }
}
