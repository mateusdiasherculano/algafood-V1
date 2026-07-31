package com.algaworks.algafood_api.domain.repository;
import com.algaworks.algafood_api.domain.model.FormaPagamento;
import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento formaPagamento);
    void remover(FormaPagamento formaPagamento);
    
}
