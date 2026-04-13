# 🏦 Sistema Bancário API (NotsBank)

API bancária desenvolvida com Java e Spring Boot, simulando operações reais de contas bancárias com foco em boas práticas de backend.

---

## 📌 Funcionalidades

- Criação de contas
- Depósito
- Saque
- Transferência entre contas
- Consulta de extrato
- Login com autenticação
- Alteração de senha

---

## 🔐 Segurança

- Senhas criptografadas com BCrypt
- Senha não exposta nas respostas da API
- Validação de login
- Alteração de senha com confirmação da senha atual

---

## 🛠 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Banco de dados (H2 / MySQL)
- BCrypt (Spring Security Crypto)

---

## 📡 Endpoints principais

POST /contas
POST /contas/login
POST /contas/{numero}/deposito
POST /contas/{numero}/saque
POST /contas/{numeroOrigem}/transferencia
GET /contas/{numero}/extrato
POST /contas/{numero}/alterar-senha


---

## 📈 Objetivo do projeto

Projeto desenvolvido com o objetivo de praticar conceitos de backend com Java, incluindo:

- Arquitetura em camadas
- Uso de DTOs
- Tratamento de exceções
- Segurança básica com criptografia de senha

---

## 🚀 Autor

Desenvolvido por Weligton Backend
