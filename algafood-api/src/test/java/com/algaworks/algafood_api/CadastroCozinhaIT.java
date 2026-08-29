package com.algaworks.algafood_api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.hamcrest.Matchers.hasSize;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.util.DatabaseCleaner;
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(locations = "/application-test.properties")
class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter15Cozinhas_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(3));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		String novaCozinha = "{ \"nome\": \"Chinesa\" }";
		
		given()
			.body(novaCozinha)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Brasileira");

		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Indiana");

		cozinhaRepository.save(cozinha1);
		cozinhaRepository.save(cozinha2);
		cozinhaRepository.save(cozinha3);
	}
}
