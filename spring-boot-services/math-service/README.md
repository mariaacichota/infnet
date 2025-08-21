# math-discipline-service
Serviço web REST sem estado para realizar operações matemáticas básicas. O serviço deve disponibilizar cinco endpoints distintos para as operações de adição, subtração, multiplicação, divisão e exponenciação. Cada endpoint deve ser acessível via métodos GET e POST, ambos com o mesmo comportamento.

## Organização do Projeto

```bash
src/
 ├── main/
 │    ├── java/
 │    │    └── com/infnet/math_service/
 │    │         ├── controller/
 │    │         │     └── MathController.java        # Controller REST para expor endpoints
 │    │         └── MathServiceApplication.java      # Classe principal para rodar a aplicação Spring Boot
 │    └── resources/
 │          └── application.properties               # Arquivo de propriedades da aplicação (porta, configs mail.integracao, etc.)
```

## Dependências Utilizadas

### Spring Boot Starter Web
Fornece suporte para desenvolvimento web e REST APIs com Spring MVC.

### Spring Context
Gerencia injeção de dependência, ciclo de vida dos beans e configuração da aplicação.
