# Algafood API

API REST para gerenciamento de restaurantes, cozinhas, cidades e estados, construída com Java e Spring Boot.

Este projeto está sendo desenvolvido como parte prática do curso **Especialista Spring Rest**, da [Algaworks](https://www.algaworks.com/), com foco em fundamentos e boas práticas para construção de APIs REST profissionais.

## Sobre o projeto

O Algafood representa uma base de uma plataforma de delivery. A aplicação organiza informações de restaurantes, suas cozinhas, localizações e relacionamentos com outras entidades do domínio.

Além de implementar operações CRUD, o projeto explora preocupações comuns em aplicações reais:

- Separação entre controllers, services, repositories e entidades de domínio
- Persistência relacional com Spring Data JPA e Hibernate
- Evolução controlada do banco de dados com Flyway
- Validação de dados com Bean Validation
- Tratamento centralizado de exceções e respostas de erro padronizadas
- Atualização parcial de restaurantes com HTTP PATCH
- Conversão e validação de payloads JSON com Jackson
- Uso de Lombok para reduzir código repetitivo

## Destaques técnicos

| Área | Tecnologias e práticas |
| --- | --- |
| Linguagem | Java 17 |
| Framework | Spring Boot 4.1.0 |
| API web | Spring MVC / Spring Web |
| Persistência | Spring Data JPA, Hibernate e MySQL |
| Migrações | Flyway |
| Validação | Jakarta Bean Validation |
| JSON | Jackson 3 |
| Utilitários | Apache Commons Lang 3 e Lombok |
| Build | Maven Wrapper |
| Testes | JUnit e Spring Boot Test |

## Funcionalidades disponíveis

### Cozinhas

- Listar cozinhas
- Buscar cozinha por ID
- Cadastrar cozinha
- Atualizar cozinha
- Remover cozinha

### Cidades

- Listar cidades
- Buscar cidade por ID
- Cadastrar cidade associada a um estado
- Atualizar cidade
- Remover cidade

### Estados

- Listar estados
- Buscar estado por ID
- Cadastrar estado
- Atualizar estado
- Remover estado

### Restaurantes

- Listar restaurantes
- Buscar restaurante por ID
- Cadastrar restaurante
- Atualizar restaurante
- Atualizar parcialmente um restaurante
- Validar campos obrigatórios do restaurante

## Endpoints principais

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `GET` | `/cozinhas` | Lista cozinhas |
| `GET` | `/cozinhas/{cozinhaId}` | Busca uma cozinha |
| `POST` | `/cozinhas` | Cadastra uma cozinha |
| `PUT` | `/cozinhas/{cozinhaId}` | Atualiza uma cozinha |
| `DELETE` | `/cozinhas/{cozinhaId}` | Remove uma cozinha |
| `GET` | `/cidades` | Lista cidades |
| `GET` | `/cidades/{cidadeId}` | Busca uma cidade |
| `POST` | `/cidades` | Cadastra uma cidade |
| `PUT` | `/cidades/{cidadeId}` | Atualiza uma cidade |
| `DELETE` | `/cidades/{cidadeId}` | Remove uma cidade |
| `GET` | `/estados` | Lista estados |
| `GET` | `/estados/{estadoId}` | Busca um estado |
| `POST` | `/estados` | Cadastra um estado |
| `PUT` | `/estados/{estadoId}` | Atualiza um estado |
| `DELETE` | `/estados/{estadoId}` | Remove um estado |
| `GET` | `/restaurantes` | Lista restaurantes |
| `GET` | `/restaurantes/{restauranteId}` | Busca um restaurante |
| `POST` | `/restaurantes` | Cadastra um restaurante |
| `PUT` | `/restaurantes/{restauranteId}` | Atualiza um restaurante |
| `PATCH` | `/restaurantes/{restauranteId}` | Atualiza parcialmente um restaurante |

## Exemplos de requisição

### Cadastrar cozinha

```http
POST /cozinhas
Content-Type: application/json

{
	"nome": "Brasileira"
}
```

### Cadastrar restaurante

```http
POST /restaurantes
Content-Type: application/json

{
	"nome": "Restaurante da Vila",
	"taxaFrete": 12.50,
	"cozinha": {
		"id": 1
	}
}
```

### Atualizar parcialmente um restaurante

```http
PATCH /restaurantes/1
Content-Type: application/json

{
	"nome": "Restaurante da Vila Centro",
	"taxaFrete": 10.00
}
```

## Respostas de erro

As exceções são tratadas de forma centralizada por `ApiExceptionHandler`. Os erros retornam uma estrutura padronizada com informações como status HTTP, tipo do problema, título, detalhe, mensagem para o usuário e timestamp.

Exemplo de validação:

```json
{
	"status": 400,
	"type": "https://algafood.com.br/mensagem-incompreensivel",
	"title": "Mensagem incompreensível",
	"detail": "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
	"userMessage": "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
	"timestamp": "2026-08-23T12:00:00",
	"fields": [
		{
			"name": "nome",
			"userMessage": "O nome do restaurante é obrigatório"
		}
	]
}
```

## Arquitetura

O código está organizado por responsabilidades:

```text
src/main/java/com/algaworks/algafood_api
├── api
│   ├── controller          # Endpoints HTTP
│   └── exceptionHandle     # Tratamento e representação de erros
└── domain
		├── exception           # Exceções de negócio e domínio
		├── model               # Entidades JPA e enums
		├── repository          # Interfaces de acesso a dados
		└── service             # Regras de negócio e operações de domínio
```

As migrações do banco estão em `algafood-api/src/main/resources/db/migration` e são executadas pelo Flyway durante a inicialização da aplicação.

## Pré-requisitos

- JDK 17 ou superior
- MySQL acessível pela aplicação
- Git

O projeto está configurado para conectar ao MySQL usando:

```text
Host: mysql
Porta: 3306
Banco: algafood
Usuário: root
Senha: root
```

Esses valores podem ser alterados em `algafood-api/src/main/resources/application.properties` antes da execução.

## Como executar

Clone o repositório e entre na pasta da aplicação:

```bash
git clone <URL-DO-SEU-REPOSITORIO>
cd <NOME-DO-REPOSITORIO>/algafood-api
```

Execute a aplicação usando o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

No Windows, use:

```bash
mvnw.cmd spring-boot:run
```

## Executar os testes

```bash
./mvnw test
```

Para apenas compilar o projeto:

```bash
./mvnw clean compile
```

## Migrações do banco

As migrações são versionadas e executadas automaticamente pelo Flyway:

- `V001__criacao-inicial.sql`
- `V002__cria-tabela-cidade.sql`
- `V003__cria-tabela-estado.sql`
- `V004__cria-varias-tabelas.sql`

O arquivo de dados de teste fica separado das migrações normais em `db/testdata`.

## Aprendizados demonstrados

Este projeto registra uma evolução prática em temas importantes para backend Java:

- Como estruturar uma API REST com Spring
- Como modelar relacionamentos com JPA
- Como tratar erros de domínio sem expor detalhes internos
- Como validar requests com `@Valid` e `@NotNull`
- Como construir respostas de erro consistentes
- Como lidar com mudanças de API entre versões do Spring Boot e Jackson
- Como usar `PATCH` para atualização parcial de recursos
- Como manter o schema do banco versionado junto com o código

## Próximos passos

Algumas evoluções naturais para o projeto são:

- Documentação interativa com OpenAPI/Swagger
- Testes de controller, service e integração com banco
- Paginação, ordenação e filtros
- DTOs para separar contratos HTTP das entidades JPA
- Autenticação e autorização
- Configuração por perfis e variáveis de ambiente
- Containerização com Docker
- Pipeline de integração contínua

## Contexto de formação

O Algafood faz parte da minha jornada prática no curso **Especialista Spring**, da Algaworks. O projeto é utilizado para consolidar conhecimentos de Java, Spring Boot, APIs REST, persistência, validação e tratamento de exceções em um domínio próximo de aplicações reais.

## Autor

Desenvolvido por **[Seu nome]**.

- GitHub: [seu-usuario](https://github.com/seu-usuario)
- LinkedIn: [seu-perfil](https://www.linkedin.com/in/seu-perfil/)
