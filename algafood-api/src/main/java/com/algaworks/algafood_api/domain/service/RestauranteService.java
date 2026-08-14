package com.algaworks.algafood_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.model.Cozinha;
import java.util.Optional;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.buscar(id);
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(
        () -> new EntidadeNaoEncontradaException(String.format("Cozinha com ID %d não encontrada.", cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }

}
