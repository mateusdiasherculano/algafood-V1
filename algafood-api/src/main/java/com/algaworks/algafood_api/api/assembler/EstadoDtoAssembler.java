package com.algaworks.algafood_api.api.assembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.algaworks.algafood_api.api.dto.EstadoDto;
import com.algaworks.algafood_api.domain.model.Estado;
import java.util.List;
import java.util.stream.Collectors;


@Component 
public class EstadoDtoAssembler {


    @Autowired
    private ModelMapper modelMapper;

    public EstadoDtoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EstadoDto toDto(Estado estado) {
        return modelMapper.map(estado, EstadoDto.class);
    }

    public List<EstadoDto> toCollectionDto(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toDto(estado))
                .collect(Collectors.toList());
    }
}
