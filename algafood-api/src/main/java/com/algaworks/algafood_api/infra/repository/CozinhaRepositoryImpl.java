package com.algaworks.algafood_api.infra.repository;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

   @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }
    
    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Override
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

}
