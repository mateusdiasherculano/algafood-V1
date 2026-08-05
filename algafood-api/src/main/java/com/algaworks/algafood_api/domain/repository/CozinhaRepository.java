package com.algaworks.algafood_api.domain.repository;
import com.algaworks.algafood_api.domain.model.Cozinha;
import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);

}
