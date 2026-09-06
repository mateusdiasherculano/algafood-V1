package com.algaworks.algafood_api.api.disassembler;
import com.algaworks.algafood_api.api.dto.CozinhaDto;
import com.algaworks.algafood_api.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.algaworks.algafood_api.api.dto.input.CozinhaInput;

@Component 
public class CozinhaInputDisassembler {

    private ModelMapper modelMapper;

    public CozinhaInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CozinhaDto toDto(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDto.class);
    }

    public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }
}
