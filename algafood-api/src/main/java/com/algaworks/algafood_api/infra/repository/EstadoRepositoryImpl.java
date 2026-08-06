package com.algaworks.algafood_api.infra.repository;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        return entityManager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado salvar(Estado estado) {
        return entityManager.merge(estado);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Estado estado = buscar(id);
        
        if (estado != null) {
            entityManager.remove(estado);
        }
    }

}
