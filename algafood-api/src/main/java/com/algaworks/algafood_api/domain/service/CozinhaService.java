package com.algaworks.algafood_api.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.model.Cozinha;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com ID: %d", id));
        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de ID %d não pode ser removida, pois está em uso", id), e);
        }
    
    }
}
