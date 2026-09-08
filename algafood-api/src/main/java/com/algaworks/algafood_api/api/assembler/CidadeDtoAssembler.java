package com.algaworks.algafood_api.api.assembler;
import com.algaworks.algafood_api.api.dto.CidadeDto;
import com.algaworks.algafood_api.domain.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDtoAssembler {

    @Autowired 
    private ModelMapper modelMapper;   

    public CidadeDtoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CidadeDto toDto(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDto.class);
    }

    public List<CidadeDto> toCollectionDto(List<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toDto(cidade))
                .collect(Collectors.toList());
    }

}
