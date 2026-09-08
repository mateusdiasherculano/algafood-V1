package com.algaworks.algafood_api.api.controller;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.service.EstadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import com.algaworks.algafood_api.api.assembler.EstadoDtoAssembler;
import com.algaworks.algafood_api.api.disassembler.EstadoInputDisassembler;
import com.algaworks.algafood_api.api.dto.EstadoDto;
import com.algaworks.algafood_api.api.dto.input.EstadoInput;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

	@Autowired 
	private EstadoInputDisassembler estadoInputDisassembler;

	@Autowired 
	private EstadoDtoAssembler estadoDtoAssembler;

    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService,
		EstadoInputDisassembler estadoInputDisassembler, EstadoDtoAssembler estadoDtoAssembler) {
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
        this.estadoInputDisassembler = estadoInputDisassembler;
        this.estadoDtoAssembler = estadoDtoAssembler;
    }

    @GetMapping
	public List<EstadoDto> listar() {
		return estadoDtoAssembler.toCollectionDto(estadoRepository.findAll());
	}
	
	@GetMapping("/{estadoId}")
	public EstadoDto buscar(@PathVariable Long estadoId) {
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		return estadoDtoAssembler.toDto(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDto adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		estado = estadoService.salvar(estado);
		
		return estadoDtoAssembler.toDto(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoDto atualizar(@PathVariable Long estadoId,
			@RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
		return estadoDtoAssembler.toDto(estadoService.salvar(estadoAtual));
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);	
	}
    
}
