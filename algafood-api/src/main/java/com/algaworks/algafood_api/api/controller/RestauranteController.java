package com.algaworks.algafood_api.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import com.algaworks.algafood_api.api.dto.CozinhaDto;
import com.algaworks.algafood_api.api.dto.RestauranteDto;
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


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<RestauranteDto> listar() {
        return toCollectionDto(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
	public RestauranteDto buscar(@PathVariable Long restauranteId) {
		return toModel(restauranteService.buscarOuFalhar(restauranteId));
	}
	

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDto adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return toModel(restauranteService.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

    @PutMapping("/{restauranteId}")
	public RestauranteDto atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
			
			BeanUtils.copyProperties(restaurante, restauranteAtual, 
					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

			return toModel(restauranteService.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

    private RestauranteDto toModel(Restaurante restaurante) {
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
	
	private List<RestauranteDto> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
}
