package com.algaworks.algafood_api.domain.repository;
import com.algaworks.algafood_api.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
