package com.algaworks.algafood_api.api.disassembler;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.api.dto.input.EstadoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class EstadoInputDisassembler {

    @Autowired 
    private ModelMapper modelMapper;

    public EstadoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Estado toDomainObject(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }
}
