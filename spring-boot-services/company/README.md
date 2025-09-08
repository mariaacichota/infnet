# company

Serviço web REST que permite o cadastro de Funcionários, Produtos, Fornecedores, Clientes e Departamentos de uma empresa.
A aplicação expõe endpoints que permitem criar, consultar, atualizar e deletar, utilizando os métodos HTTP GET, POST, PUT e DELETE.

## Organização do Projeto

```bash
src/
 ├── main/
 │    ├── java/
 │    │    └── com/infnet/company/
 │    │         ├── controller/
 │    │         │     ├── ProductController.java        # Endpoints de Produto
 │    │         │     ├── EmployeeController.java       # Endpoints de Funcionário
 │    │         │     ├── SupplierController.java       # Endpoints de Fornecedor
 │    │         │     ├── ClientController.java         # Endpoints de Cliente
 │    │         │     └── DepartmentController.java     # Endpoints de Departamento
 │    │         │
 │    │         ├── model/
 │    │         │     ├── Product.java                  # Classe de domínio Produto
 │    │         │     ├── Employee.java                 # Classe de domínio Funcionário
 │    │         │     ├── Supplier.java                 # Classe de domínio Fornecedor
 │    │         │     ├── Client.java                   # Classe de domínio Cliente
 │    │         │     └── Department.java               # Classe de domínio Departamento
 │    │         │
 │    │         ├── repository/
 │    │         │     ├── ProductRepository.java        # Repositório Produto
 │    │         │     ├── EmployeeRepository.java       # Repositório Funcionário
 │    │         │     ├── SupplierRepository.java       # Repositório Fornecedor
 │    │         │     ├── ClientRepository.java         # Repositório Cliente
 │    │         │     └── DepartmentRepository.java     # Repositório Departamento
 │    │         │
 │    │         ├── service/
 │    │         │     ├── ProductService.java           # Lógica de negócios Produto
 │    │         │     ├── EmployeeService.java          # Lógica de negócios Funcionário
 │    │         │     ├── SupplierService.java          # Lógica de negócios Fornecedor
 │    │         │     ├── ClientService.java            # Lógica de negócios Cliente
 │    │         │     └── DepartmentService.java        # Lógica de negócios Departamento
 │    │         │
 │    │         └── CompanyApplication.java             # Classe principal Spring Boot
 │    │
 │    └── resources/
 │         ├── application.properties                   # Configurações da aplicação
 │         └── data.sql                                 # Popular DB H2 com dados iniciais
 │
 └── test/
      └── java/
           └── com/infnet/company/
                ├── controller/
                │     ├── ProductControllerTest.java
                │     ├── EmployeeControllerTest.java
                │     ├── SupplierControllerTest.java
                │     ├── ClientControllerTest.java
                │     └── DepartmentControllerTest.java
                │
                └── service/
                      ├── ProductServiceTest.java
                      ├── EmployeeServiceTest.java
                      ├── SupplierServiceTest.java
                      ├── ClientServiceTest.java
                      └── DepartmentServiceTest.java
```

## Dependências Utilizadas

### Spring Boot Starter Web
Fornece suporte para desenvolvimento web e REST APIs com Spring MVC.

### Spring Boot Starter Data JPA
Facilita a persistência de dados com JPA e Hibernate.
Permite utilizar interfaces como JpaRepository para operações CRUD sem necessidade de implementar manualmente queries SQL.

### H2 Database
Banco de dados em memória, leve e ideal para desenvolvimento e testes.
Possui console web (/h2-console) que permite inspecionar dados durante a execução da aplicação.

### Spring Boot Starter Validation
Fornece suporte para validação de dados via Bean Validation (JSR 380).
Permite o uso de anotações como @NotNull, @Size, @Email, garantindo a consistência dos dados antes da persistência.

### Lombok
Biblioteca que reduz o código boilerplate em classes Java.
Gera automaticamente getters, setters, construtores e builders por meio de anotações como @Getter, @Setter, @Builder.

### Spring Boot Starter Test
Fornece infraestrutura completa para testes unitários e de integração.
Inclui JUnit 5, Mockito, Spring Test e bibliotecas de assertions (AssertJ, Hamcrest).
Essencial para garantir a qualidade e confiabilidade das funcionalidades implementadas.

### Spring Context
Gerencia injeção de dependência, ciclo de vida dos beans e configuração da aplicação.
