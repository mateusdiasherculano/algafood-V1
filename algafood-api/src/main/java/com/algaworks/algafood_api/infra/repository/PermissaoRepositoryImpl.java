package com.algaworks.algafood_api.infra.repository;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.algaworks.algafood_api.domain.model.Permissao;
import com.algaworks.algafood_api.domain.repository.PermissaoRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
        return entityManager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return entityManager.find(Permissao.class, id);
    }

    @Override
    public Permissao salvar(Permissao permissao) {
        return entityManager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        entityManager.remove(permissao);
    }

}
