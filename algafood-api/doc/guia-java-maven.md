# Guia rápido: Java, Maven e VS Code

## 1. Compilar o projeto

Entre na pasta que contém o `pom.xml`:

```bash
cd algafood-api
```

Compile o código Java com o Maven Wrapper:

```bash
./mvnw compile
```

Esse comando verifica se o código compila e gera os arquivos compilados em `target/classes`.

## 2. Executar a aplicação

```bash
./mvnw spring-boot:run
```

Para limpar os artefatos antigos, compilar novamente e iniciar a aplicação:

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

## 3. Quando usar `clean`

Use `clean` principalmente quando:

- uma classe for excluída ou renomeada;
- uma implementação de repositório for alterada ou removida;
- o erro mencionar `AbstractMethodError`;
- a aplicação parecer executar uma versão antiga do código;
- houver diferença entre o código-fonte e os arquivos em `target`.

O comando `clean` remove o diretório `target`, incluindo classes compiladas antigas que podem ter ficado órfãs após a exclusão de arquivos-fonte.

Depois, compile novamente:

```bash
./mvnw clean compile
```

Não é necessário usar `clean` a cada compilação normal. Para alterações comuns, `./mvnw compile` costuma ser suficiente.

## 4. Quando o Ctrl+Click não funciona

Se o VS Code mostrar a mensagem:

```text
is a non-project file, only syntax errors are reported
```

o Java Language Server não reconheceu o arquivo como parte do projeto Maven. Para corrigir:

1. Pressione `Ctrl+Shift+P`.
2. Execute **Java: Clean Java Language Server Workspace**.
3. Escolha **Restart and delete** quando solicitado.
4. Aguarde o Java Language Server reimportar o projeto Maven.

Também confirme se as extensões **Extension Pack for Java** e **Language Support for Java** estão instaladas.

## 5. Fluxo recomendado após excluir uma classe

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

Se o Ctrl+Click continuar sem funcionar, limpe o workspace do Java Language Server pelo VS Code e aguarde a reindexação.
