
# 🛠️ API REST - Catálogo de Produtos (Spring Boot + MySQL + Docker)

Este projeto é uma API RESTful para gerenciamento de produtos, desenvolvida com **Java + Spring Boot**, utilizando **MySQL** como banco de dados e **Docker** para containerização.

---

## ✅ Funcionalidades da API

- Criar, listar, buscar, atualizar e excluir produtos
- Paginação com parâmetros `page` e `pageSize`
- Validações de campos obrigatórios
- Decrementar quantidade de estoque (sem permitir número negativo)

---

## 🐳 Como subir o MySQL com Docker

1. Use o arquivo `docker-compose.yml` na raiz do projeto:

2. Suba o container com:

```bash
docker-compose up -d
```

---

## ⚙️ Configurar `application.properties`

Edite o arquivo `src/main/resources/application.properties` com:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product
spring.datasource.username=dev
spring.datasource.password=dev123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

spring.application.name=produtos-api
```

---

## 🧪 Importar Collection do Postman

> Acesse o arquivo `vgxRequestsPostman.json` (gerar via Postman com os endpoints) e importe no Postman para testar os seguintes endpoints:

- `GET /products?page=0&pageSize=10` — listar produtos com paginação
- `GET /products/{id}` — buscar produto por ID
- `POST /products` — criar produto
- `PUT /products/{id}` — atualizar produto
- `DELETE /products/{id}` — deletar produto
- `PUT /products/{id}/decrease-quantity` — decrementar quantidade

---

## 🧾 Usar o MySQL via terminal

1. Acesse o container MySQL:

```bash
docker exec -it mysql-container mysql -u root -p
```

2. Digite a senha `root`.

3. Execute comandos SQL, como:

```sql
USE product_api;
SHOW TABLES;
SELECT * FROM products;
```

---

## 📥 Scripts úteis

- Arquivo com consultas SQL`contatos_script_completo.sql`:
- Criação e inserção de dados na tabela `product` usada na API
- Criação e consulta da tabela `contatos` usada nos testes SQL.

---

## 🧠 Prova de Lógica

Para rodar a prova de lógica use o arquivo `src/logicTest/logicPracticeTest.java`.

---

## 💡 Extras implementados

- Validação com mensagens personalizadas usando `@NotBlank`, `@NotNull`, `@Min`
- Tratamento de erros com `ResponseEntity`
- Paginação manual e flexível com `page` e `pageSize`
- Testes com base de dados pré-carregada (30 produtos via script)

---

## 🚀 Rodar o projeto

Instale as dependências
```bash
./mvn clean install
```
Após subir o MySQL:
```bash
./mvn spring-boot:run
```

Ou execute diretamente na sua IDE (IntelliJ, VS Code etc.)

---

## 👨‍💻 Desenvolvedor

Flávio — Prova prática com foco em Java, Spring Boot, SQL, Docker e APIs RESTful.
