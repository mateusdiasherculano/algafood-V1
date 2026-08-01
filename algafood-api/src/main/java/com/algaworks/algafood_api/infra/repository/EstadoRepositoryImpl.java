package com.algaworks.algafood_api.infra.repository;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
    public Estado salvar(Estado estado) {
        return entityManager.merge(estado);
    }

    @Override
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        entityManager.remove(estado);
    }

}
