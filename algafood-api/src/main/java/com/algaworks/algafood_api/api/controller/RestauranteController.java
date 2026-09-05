package com.algaworks.algafood_api.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.algaworks.algafood_api.api.dto.RestauranteDto;
import com.algaworks.algafood_api.api.dto.input.RestauranteInput;
import com.algaworks.algafood_api.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood_api.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.domain.service.RestauranteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import com.algaworks.algafood_api.api.assembler.RestauranteDtoAssembler;
import com.algaworks.algafood_api.api.assembler.RestauranteInputDisassembler;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired 
    private RestauranteDtoAssembler restauranteDtoAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService,
        RestauranteDtoAssembler restauranteDtoAssembler, RestauranteInputDisassembler restauranteInputDisassembler) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
        this.restauranteDtoAssembler = restauranteDtoAssembler;
        this.restauranteInputDisassembler = restauranteInputDisassembler;
    }

    @GetMapping
    public List<RestauranteDto> listar() {
        return restauranteDtoAssembler.toCollectionDto(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
	public RestauranteDto buscar(@PathVariable Long restauranteId) {
		return restauranteDtoAssembler.toDto(restauranteService.buscarOuFalhar(restauranteId));
	}
	

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDto adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

			return restauranteDtoAssembler.toDto(restauranteService.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

    @PutMapping("/{restauranteId}")
	public RestauranteDto atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
        
			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

			return restauranteDtoAssembler.toDto(restauranteService.salvar(restauranteAtual));

		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	
}