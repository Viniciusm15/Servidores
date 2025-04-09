# 📦 SeplagAPI - Desafio Técnico

Este repositório contém a implementação do desafio técnico proposto, com autenticação JWT, CRUDs, upload e recuperação de imagens via MinIO, paginação, consulta por parâmetros e orquestração via Docker Compose.

---

## 👤 Dados de Inscrição

- **Nome:** Vinícius Hiago Martins
- **Email:** viniciushiagomartins00@gmail.com
- **LinkedIn:** [https://www.linkedin.com/in/vinicius-hiago-martins-a33ab617b/](https://www.linkedin.com/in/vinicius-hiago-martins-a33ab617b/)

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Security + JWT  
- PostgreSQL  
- Hibernate / JPA  
- MinIO  
- Swagger (OpenAPI 3)  
- Docker & Docker Compose  
- Lombok  

---

## ⚙️ Como Executar

### Pré-requisitos

- Docker e Docker Compose instalados
- Git instalado

### Clonando o Projeto

```bash
git clone https://github.com/Viniciusm15/seplag-api.git
cd seplag-api
```

### Subindo com Docker Compose

```bash
docker-compose up --build
```

Isso irá iniciar:

- API: http://localhost:8080  
- PostgreSQL: localhost:5432  
- MinIO: http://localhost:9000  
- Console MinIO: http://localhost:9001  

---

## 🧪 Testando a Aplicação

### Documentação da API (Swagger)

Disponível em:  
```
http://localhost:8080/swagger-ui/index.html
```

### Login e Autenticação

1. Acesse a rota `/auth/login` com um usuário válido
2. Exemplo de payload:
```json
{
  "email": "vinicius@example.com",
  "password": "123456"
}
```
3. Você receberá um token JWT
4. No Swagger, clique em **Authorize** e insira:
```
Bearer SEU_TOKEN_AQUI
```

### Exemplo de Endpoints da Aplicação

---

#### Autenticação
- `POST /auth/register`: Registro de usuário
- `POST /auth/login`: Login de usuário

---

#### Pessoas
- `POST /persons`: Cadastrar pessoa
- `GET /persons/{id}`: Buscar pessoa por ID
- `GET /persons`: Listar todas as pessoas (paginado)
- `PUT /persons/{id}`: Atualizar pessoa
- `DELETE /persons/{id}`: Remover pessoa

---

#### Fotos de Pessoas
- `POST /person-photos/upload`: Enviar múltiplas fotos de uma pessoa
- `GET /person-photos/{id}`: Buscar foto por ID
- `GET /person-photos`: Listar fotos (paginado)
- `DELETE /person-photos/{id}`: Remover foto

---

#### Endereços
- `POST /addresses`: Cadastrar endereço
- `GET /addresses/{id}`: Buscar endereço por ID
- `GET /addresses`: Listar endereços (paginado)
- `PUT /addresses/{id}`: Atualizar endereço
- `DELETE /addresses/{id}`: Remover endereço

---

#### Cidades
- `POST /cities`: Cadastrar cidade
- `GET /cities/{id}`: Buscar cidade por ID
- `GET /cities`: Listar cidades (paginado)
- `PUT /cities/{id}`: Atualizar cidade
- `DELETE /cities/{id}`: Remover cidade

---

#### Unidades
- `POST /units`: Cadastrar unidade
- `GET /units/{id}`: Buscar unidade por ID
- `GET /units`: Listar unidades (paginado)
- `PUT /units/{id}`: Atualizar unidade
- `DELETE /units/{id}`: Remover unidade

---

#### Vínculos (Assignments)
- `POST /assignments`: Cadastrar vínculo
- `GET /assignments/{id}`: Buscar vínculo por ID
- `GET /assignments`: Listar vínculos (paginado)
- `GET /assignments/permanent-employees/by-unit/{unitId}`: Listar servidores efetivos por unidade (paginado)
- `PUT /assignments/{id}`: Atualizar vínculo
- `DELETE /assignments/{id}`: Remover vínculo

---

#### Servidores Efetivos
- `POST /permanent-employees`: Cadastrar servidor efetivo
- `GET /permanent-employees/{id}`: Buscar servidor efetivo por ID
- `GET /permanent-employees`: Listar servidores efetivos (paginado)
- `PUT /permanent-employees/{id}`: Atualizar servidor efetivo
- `DELETE /permanent-employees/{id}`: Remover servidor efetivo

---

#### Servidores Temporários
- `POST /temporary-employees`: Cadastrar servidor temporário
- `GET /temporary-employees/{id}`: Buscar servidor temporário por ID
- `GET /temporary-employees`: Listar servidores temporários (paginado)
- `PUT /temporary-employees/{id}`: Atualizar servidor temporário
- `DELETE /temporary-employees/{id}`: Remover servidor temporário

---

## 🐳 Serviços Docker

O projeto é totalmente orquestrado com Docker Compose, incluindo:

- API Java Spring Boot (porta `8080`)
- PostgreSQL (porta `5432`)
- MinIO (porta `9000`) e Console do MinIO (porta `9001`)

Para subir o ambiente:

```bash
docker-compose up --build
```

### `docker-compose.yml`

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: postgres-container
    restart: always
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: serversdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    volumes:
      - pgdata:/var/lib/postgresql/data

  minio:
    image: minio/minio
    container_name: minio-container
    ports:
      - "9000:9000"
      - "9001:9001"
    environment:
      MINIO_ROOT_USER: minio
      MINIO_ROOT_PASSWORD: minio123
    command: server /data --console-address ":9001"
    volumes:
      - miniodata:/data

volumes:
  pgdata:
  miniodata:
```

---

## 🛠 Configurações

### application.properties

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/serversdb
spring.datasource.username=postgres
spring.datasource.password=postgres

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Porta da aplicação
server.port=8080

# JWT
jwt.secret=E7d9F4gH2kLmN0pR3tUvXyZ5aBcDeFgHiJkLmNoPqRsTuVwX

# MinIO
minio.url=http://localhost:9000
minio.access-key=minio
minio.secret-key=minio123
minio.bucket.photos=photos
```

---

## 🗂 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/vinicius/serversapi/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── domain/
│   │   ├── dto/
│   │   ├── repository/
│   │   ├── service/
│   │   └── security/
│   └── resources/
│       ├── application.properties
│       └── application-docker.properties
docker-compose.yml
README.md
```

---

## 📦 Dependências

Além das dependências padrões do Spring Boot, foram adicionadas:

- `spring-boot-starter-security`
- `jjwt` (JWT auth)
- `springdoc-openapi-ui`
- `aws-java-sdk-s3` (para integração com MinIO)
- `postgresql`
- `lombok`

---
