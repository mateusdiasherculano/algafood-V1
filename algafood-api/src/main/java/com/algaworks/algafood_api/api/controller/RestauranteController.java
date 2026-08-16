package com.algaworks.algafood_api.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import org.springframework.util.ReflectionUtils;
import tools.jackson.databind.json.JsonMapper;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.ResponseEntity;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.domain.service.RestauranteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





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
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
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

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        try {
           
            Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);

            if (restauranteExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Restaurante restauranteExistenteEntity = restauranteExistente.get();

            BeanUtils.copyProperties(restaurante, restauranteExistenteEntity, "id", "formasPagamento");
            Restaurante restauranteAtualizado = restauranteService.salvar(restauranteExistenteEntity);
            return ResponseEntity.ok(restauranteAtualizado);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
            Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);

            if (restauranteExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Restaurante restauranteExistenteEntity = restauranteExistente.get();

            merge(campos, restauranteExistenteEntity);
            
            return atualizar(id, restauranteExistenteEntity);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        JsonMapper jsonMapper = new JsonMapper();
        Restaurante restauranteOrigem = jsonMapper.convertValue(dadosOrigem, Restaurante.class);

        
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
            
        });
    }

}
