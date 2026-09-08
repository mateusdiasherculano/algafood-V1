package com.algaworks.algafood_api.core.modelmapper;

import com.algaworks.algafood_api.api.dto.input.CidadeInput;
import com.algaworks.algafood_api.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.createTypeMap(CidadeInput.class, Cidade.class)
                .addMappings(mapping -> mapping.skip(Cidade::setId));
        return modelMapper;
    }
}