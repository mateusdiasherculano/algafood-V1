package com.algaworks.algafood_api.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.ResponseEntity;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.domain.service.RestauranteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




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

    @GetMapping()
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscar(id);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {

        try {
            Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
            return ResponseEntity.created(null).body(restauranteSalvo);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
