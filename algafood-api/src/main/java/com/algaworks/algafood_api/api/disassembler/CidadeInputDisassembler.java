package com.algaworks.algafood_api.api.disassembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.algaworks.algafood_api.api.dto.input.CidadeInput;
import com.algaworks.algafood_api.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import com.algaworks.algafood_api.api.dto.CidadeDto;

@Component 
public class CidadeInputDisassembler {

    @Autowired 
    private ModelMapper modelMapper;

    public CidadeInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CidadeDto toDto(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDto.class);
    }

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        modelMapper.map(cidadeInput, cidade);
    }


}
