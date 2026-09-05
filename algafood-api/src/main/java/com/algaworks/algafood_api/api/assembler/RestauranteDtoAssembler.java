package com.algaworks.algafood_api.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood_api.api.dto.CozinhaDto;
import com.algaworks.algafood_api.api.dto.RestauranteDto;
import com.algaworks.algafood_api.domain.model.Restaurante;

@Component 
public class RestauranteDtoAssembler {

    public RestauranteDto toDto(Restaurante restaurante) {
		CozinhaDto cozinhaDto = new CozinhaDto();
		cozinhaDto.setId(restaurante.getCozinha().getId());
		cozinhaDto.setNome(restaurante.getCozinha().getNome());
		
		RestauranteDto restauranteDto = new RestauranteDto();
		restauranteDto.setId(restaurante.getId());
		restauranteDto.setNome(restaurante.getNome());
		restauranteDto.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteDto.setCozinha(cozinhaDto);
		return restauranteDto;
	}

}
