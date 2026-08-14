package com.algaworks.algafood_api.domain.repository;
import com.algaworks.algafood_api.domain.model.Cozinha;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    
}
