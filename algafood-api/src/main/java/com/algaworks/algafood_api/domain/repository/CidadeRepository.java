package com.algaworks.algafood_api.domain.repository;
import com.algaworks.algafood_api.domain.model.Cidade;
import java.util.List;


public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Cidade cidade);
    

}
