package com.algaworks.algafood_api.api.assembler;

import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.algaworks.algafood_api.api.dto.RestauranteDto;
import com.algaworks.algafood_api.domain.model.Restaurante;

@Component 
public class RestauranteDtoAssembler {

    @Autowired 
	private ModelMapper modelMapper;

	public RestauranteDtoAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

    public RestauranteDto toDto(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteDto.class);
	}


	public List<RestauranteDto> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toDto(restaurante))
				.collect(Collectors.toList());
	}

}
