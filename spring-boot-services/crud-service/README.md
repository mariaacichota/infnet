# math-discipline-service

Projeto Spring Boot para gerenciamento de produtos com uma API RESTful.
A aplicação expõe endpoints que permitem criar, consultar, atualizar e deletar produtos, utilizando os métodos HTTP GET, POST, PUT e DELETE.

## Organização do Projeto

```bash
src/
 ├── main/
 │    ├── java/
 │    │    └── com/infnet/crud_service/
 │    │         ├── controller/
 │    │         │     └── ProductController.java        # Controller REST para expor endpoints
 │    │         ├── model/
 │    │         │     └── Product.java                  # Classe de domínio
 │    │         └── CrudServiceApplication.java         # Classe principal para rodar a aplicação Spring Boot
 │    └── resources/
 │          └── application.properties               # Arquivo de propriedades da aplicação (porta, configs mail.integracao, etc.)
```

## Dependências Utilizadas

### Spring Boot Starter Web
Fornece suporte para desenvolvimento web e REST APIs com Spring MVC.

### Spring Context
Gerencia injeção de dependência, ciclo de vida dos beans e configuração da aplicação.
