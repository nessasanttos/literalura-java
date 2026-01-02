# 📚 LiterAlura

Projeto desenvolvido em **Java + Spring Boot + JPA + PostgreSQL**, com integração à API pública **Gutendex**, para busca, cadastro e consulta de livros e autores.

Este projeto foi construído com foco em **boas práticas**, **organização de código**, **persistência de dados** e **segurança de configurações**, seguindo padrões profissionais.

---

## 🚀 Funcionalidades

O sistema funciona via **menu interativo no terminal**, permitindo:

1. 🔍 Buscar livro pelo título (API Gutendex)
2. 📚 Listar livros registrados no banco de dados
3. ✍️ Listar autores registrados
4. 👤 Listar autores vivos em um determinado ano
5. 🌍 Listar livros por idioma
6. ❌ Encerrar a aplicação

Os dados obtidos da API são **persistidos automaticamente** no banco de dados PostgreSQL.

---

## 🛠️ Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* API Gutendex

---

## 🗂️ Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── com.alura.literalura
│   │       ├── model
│   │       ├── repository
│   │       ├── service
│   │       └── principal
│   └── resources
│       ├── application.properties
│       └── application-example.properties
└── test
```

---

## ⚙️ Configuração do Banco de Dados

Este projeto utiliza PostgreSQL.

Por segurança, o arquivo real de configuração **não é versionado**.

### ▶️ Para executar localmente:

1. Crie um banco de dados no PostgreSQL chamado:

   ```
   literalura
   ```
2. Copie o arquivo:

   ```
   application-example.properties
   ```
3. Renomeie para:

   ```
   application.properties
   ```
4. Substitua `YOUR_PASSWORD_HERE` pela sua senha local

---

## ▶️ Como Executar o Projeto

1. Clone o repositório
2. Abra o projeto em uma IDE (IntelliJ IDEA recomendado)
3. Configure o banco conforme descrito acima
4. Execute a classe:

   ```
   LiterAluraApplication
   ```
5. Utilize o menu exibido no terminal

---

## 📌 Observações Importantes

* ✔️ Dados sensíveis não são expostos no GitHub
* ✔️ Projeto segue padrão MVC
* ✔️ Código organizado por responsabilidade
* ✔️ Persistência automática com JPA/Hibernate

---

## 👩‍💻 Autora

Projeto desenvolvido por **Ângela Santos** .

---


