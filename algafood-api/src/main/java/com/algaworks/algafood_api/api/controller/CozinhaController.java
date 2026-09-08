package com.algaworks.algafood_api.api.controller;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.service.CozinhaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.algaworks.algafood_api.api.assembler.CozinhaDtoAssembler;
import com.algaworks.algafood_api.api.disassembler.CozinhaInputDisassembler;
import com.algaworks.algafood_api.api.dto.CozinhaDto;
import com.algaworks.algafood_api.api.dto.input.CozinhaInput;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

	@Autowired 
	private CozinhaDtoAssembler cozinhaDtoAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;


    CozinhaController(CozinhaRepository cozinhaRepository, CozinhaService cozinhaService,
		CozinhaDtoAssembler cozinhaDtoAssembler,
		 CozinhaInputDisassembler cozinhaInputDisassembler) {
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
        this.cozinhaDtoAssembler = cozinhaDtoAssembler;
        this.cozinhaInputDisassembler = cozinhaInputDisassembler;
    }
    
    @GetMapping
	public List<CozinhaDto> listar() {
		return cozinhaDtoAssembler.toCollectionDto(cozinhaRepository.findAll());
	}
	
	@GetMapping("/{cozinhaId}")
	public CozinhaDto buscar(@PathVariable Long cozinhaId) {
		return cozinhaDtoAssembler.toDto(cozinhaService.buscarOuFalhar(cozinhaId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaDto adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		return cozinhaDtoAssembler.toDto(cozinhaService.salvar(cozinha));
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaDto atualizar(@PathVariable Long cozinhaId,
			@RequestBody @Valid CozinhaInput cozinha) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
		
		cozinhaInputDisassembler.copyToDomainObject(cozinha, cozinhaAtual);
		
		return cozinhaDtoAssembler.toDto(cozinhaService.salvar(cozinhaAtual));
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cozinhaService.excluir(cozinhaId);
	}
}
