package com.algaworks.algafood_api.domain.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

    @Column(nullable = false)
	private String nome;

    private BigDecimal taxaFrete;

    @ManyToOne
    private Cozinha cozinha;

}
