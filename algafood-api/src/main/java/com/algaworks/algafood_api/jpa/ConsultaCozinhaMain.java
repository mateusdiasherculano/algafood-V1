package com.algaworks.algafood_api.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.domain.model.Cozinha;


public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        //cadastroCozinha.listar().forEach(cozinha -> System.out.println(cozinha.getNome()));

            Cozinha cozinha = new Cozinha();
            cozinha.setId(1L);

        // Cozinha cozinha2 = new Cozinha();
        // cozinha2.setNome("Italiana");

        // cadastroCozinha.Adicionar(cozinha);
        // cadastroCozinha.Adicionar(cozinha2);

        //Cozinha cozinha = cadastroCozinha.buscar(1L);
        cadastroCozinha.remover(cozinha);
        System.out.println(cozinha.getNome());

    }
}
