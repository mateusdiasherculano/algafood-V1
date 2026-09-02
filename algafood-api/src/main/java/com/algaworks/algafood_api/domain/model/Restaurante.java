package com.algaworks.algafood_api.domain.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.algaworks.algafood_api.core.validation.Groups;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
    @Column(nullable = false)
	private String nome;

	@NotNull
	@PositiveOrZero
    private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @ManyToOne 
	@JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
	
	@Embedded
	private Endereco endereco;

	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataAtualizacao;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
		joinColumns = @JoinColumn(name = "restaurante_id"),
		inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
}
