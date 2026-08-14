package com.algaworks.algafood_api.domain.service;
import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;
import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.model.Estado;


@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public CidadeService(CidadeRepository cidadeRepository, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscar(estadoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Estado com ID %d não encontrado.", estadoId));
        }

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long id) {
        try {
            Cidade cidade = cidadeRepository.findById(id).orElse(null);
            if (cidade != null) {
                cidadeRepository.deleteById(id);
            }
        } catch(EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cidade com ID: %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade de ID %d não pode ser removida, pois está em uso", id), e);
        }
        

    }

}
