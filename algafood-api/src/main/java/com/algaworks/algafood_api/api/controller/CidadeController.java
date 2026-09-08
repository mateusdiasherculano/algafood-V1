package com.algaworks.algafood_api.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;
import com.algaworks.algafood_api.domain.service.CidadeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.algaworks.algafood_api.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood_api.domain.exception.NegocioException;
import jakarta.validation.Valid;
import com.algaworks.algafood_api.api.assembler.CidadeDtoAssembler;
import com.algaworks.algafood_api.api.disassembler.CidadeInputDisassembler;
import com.algaworks.algafood_api.api.dto.CidadeDto;
import com.algaworks.algafood_api.api.dto.input.CidadeInput;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;

	@Autowired 
	private CidadeInputDisassembler cidadeInputDisassembler;

	@Autowired
	private CidadeDtoAssembler cidadeDtoAssembler;


    public CidadeController(CidadeRepository cidadeRepository, CidadeService cidadeService,
		CidadeInputDisassembler cidadeInputDisassembler, CidadeDtoAssembler cidadeDtoAssembler) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
        this.cidadeInputDisassembler = cidadeInputDisassembler;
        this.cidadeDtoAssembler = cidadeDtoAssembler;
    }
	
	@GetMapping
	public List<CidadeDto> listar() {
		return cidadeDtoAssembler.toCollectionDto(cidadeRepository.findAll());
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeDto buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

		return cidadeDtoAssembler.toDto(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDto adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			return cidadeDtoAssembler.toDto(cidadeService.salvar(cidade));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeDto atualizar(@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			return cidadeDtoAssembler.toDto(cidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);	
	}
	
}
