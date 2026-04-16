# NotsBank API

API bancária desenvolvida com Spring Boot para gerenciamento de contas e transações financeiras.

## 🚀 Tecnologias

* Java 21
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* H2 / MySQL
* Swagger (OpenAPI)

## 🔐 Autenticação

A API utiliza autenticação via JWT.

Para acessar endpoints protegidos:

1. Realizar login em `/auth/login`
2. Copiar o token retornado
3. Inserir no Swagger no botão **Authorize**

Formato:
Bearer SEU_TOKEN

## 📌 Funcionalidades

* Criar conta
* Listar contas
* Consultar saldo
* Realizar depósito
* Realizar saque
* Transferência entre contas
* Extrato de transações

## 📖 Documentação

Acesse via Swagger:
http://localhost:8080/swagger-ui/index.html

## ▶️ Como rodar o projeto

```bash
git clone https://github.com/SEU-USUARIO/notsbank.git
cd notsbank
mvn spring-boot:run
```

## 👨‍💻 Autor

Desenvolvido por Welington
