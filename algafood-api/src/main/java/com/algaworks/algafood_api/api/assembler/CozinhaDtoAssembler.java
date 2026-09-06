package com.algaworks.algafood_api.api.assembler;
import com.algaworks.algafood_api.api.dto.CozinhaDto;
import com.algaworks.algafood_api.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component 
public class CozinhaDtoAssembler {

    private ModelMapper modelMapper;

    public CozinhaDtoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CozinhaDto toDto(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDto.class);
    }

    public List<CozinhaDto> toCollectionDto(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toDto(cozinha))
                .collect(Collectors.toList());
    }
}
