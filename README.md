# 📦 SeplagAPI - Desafio Técnico

Este repositório contém a implementação do desafio técnico proposto, com autenticação JWT, CRUDs, upload e recuperação de imagens via MinIO, paginação, consulta por parâmetros e orquestração via Docker Compose.

---

## 🏛 Sobre o Projeto

A **API SEPLAG** é um sistema de gerenciamento de recursos humanos desenvolvido para a Secretaria de Estado de Planejamento e Gestão de Mato Grosso (**SEPLAG-MT**).

A aplicação permite o cadastro e administração de servidores públicos (efetivos e temporários), suas lotações, unidades administrativas, endereços e documentação pessoal — incluindo fotos armazenadas via **MinIO**.

**Objetivo**: fornecer uma base estruturada e segura para controle interno, promovendo **agilidade**, **transparência** e **modernização** da gestão pública.

---

## 👤 Dados de Inscrição

- **Processo Seletivo:** PSS 02/2025/SEPLAG (Analista de TI - Perfil Junior, Pleno e Sênior)
- **Nome:** Vinícius Hiago Martins
- **Número de inscrição:** 9730 - DESENVOLVEDOR JAVA (BACK-END) - JÚNIOR
---

## 🐛 Funcionalidades

- Autenticação com expiração e renovação de token JWT
- CRUDs completos para entidades do modelo
- Upload e recuperação de imagens via MinIO
- Consultas parametrizadas por nome e unidade
- Paginação de resultados
- Orquestração completa com Docker Compose
---

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem principal utilizada no desenvolvimento da API.

- **Spring Boot**: Framework para facilitar a criação de aplicações Java, com configuração mínima e arquitetura robusta.

- **Spring Security + JWT**: Implementa autenticação e autorização baseadas em tokens JWT, garantindo segurança no acesso aos endpoints.

- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações da aplicação.

- **Hibernate / JPA**: Framework de mapeamento objeto-relacional (ORM) que facilita a persistência de dados no banco.

- **MinIO**: Armazenamento de objetos compatível com S3 usado para upload e recuperação de fotos de servidores.

- **Swagger (OpenAPI 3)**: Ferramenta de documentação interativa que permite testar e explorar os endpoints da API com facilidade.

- **Docker & Docker Compose**: Permite a containerização da aplicação e orquestração de serviços como API, banco de dados e MinIO.

- **Lombok**: Biblioteca que reduz o boilerplate no código Java com anotações para getters, setters, construtores, etc.
---

## 🗂 Estrutura do Projeto

```
servers-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/vinicius/serversapi/
│       │       ├── config/
│       │       ├── controller/
│       │       ├── domain/
│       │       ├── dto/
│       │       ├── repository/
│       │       ├── service/
│       │       └── security/
│       └── resources/
│           ├── application.properties
│           └── application-docker.properties
├── target/
│   └── api-1.0.0.jar # (gerado após o build)
├── docker-compose.yml
├── Dockerfile
├── .env
└── README.md
```
---

## ⚙️ Como Executar

### 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/)

### 🛠️ Configuração do Ambiente

Clone o repositório:

```bash
git clone https://github.com/Viniciusm15/seplag-api.git
cd seplag-api
```

Crie um arquivo .env na raiz do projeto:

```bash
# Banco de Dados PostgreSQL
POSTGRES_DB=serversdb
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# Minio (Armazenamento de Objetos)
MINIO_ROOT_USER=minio
MINIO_ROOT_PASSWORD=minio123

# API
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/serversdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
MINIO_ENDPOINT=http://minio:9000
MINIO_ACCESS_KEY=minio
MINIO_SECRET_KEY=minio123
```
Crie um arquivo docker-compose.yml na raiz do projeto:

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
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    volumes:
      - pgdata:/var/lib/postgresql/data
    networks:
      - seplag-network

  minio:
    image: minio/minio
    container_name: minio-container
    restart: always
    ports:
      - "9000:9000"
      - "9001:9001"
    environment:
      MINIO_ROOT_USER: ${MINIO_ROOT_USER}
      MINIO_ROOT_PASSWORD: ${MINIO_ROOT_PASSWORD}
    command: server /data --console-address ":9001"
    volumes:
      - miniodata:/data
    networks:
      - seplag-network

  api:
    build: .
    container_name: api-container
    restart: always
    ports:
      - "8080:8080"
    depends_on:
      - postgres
      - minio
    environment:
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
      MINIO_ENDPOINT: ${MINIO_ENDPOINT}
      MINIO_ACCESS_KEY: ${MINIO_ACCESS_KEY}
      MINIO_SECRET_KEY: ${MINIO_SECRET_KEY}
    networks:
      - seplag-network

volumes:
  pgdata:
  miniodata:

networks:
  seplag-network:
    driver: bridge
```

Crie um arquivo Dockerfile na raiz do projeto:

```bash
# Usar uma imagem base do OpenJDK
FROM openjdk:11-jre-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR da aplicação
COPY target/api-1.0.0.jar app.jar

# Copiar o arquivo de configuração para o diretório de recursos da aplicação
COPY src/main/resources/application-docker.properties /app/resources/application-docker.properties

# Expor a porta que a aplicação vai rodar
EXPOSE 8080

# Definir a variável de ambiente para Spring usar o arquivo Docker
ENV SPRING_PROFILES_ACTIVE=docker

# Rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-Dspring.config.location=classpath:/application-docker.properties", "-jar", "/app/app.jar"]
```

Execute o comando abaixo na raiz do projeto:
```bash
docker-compose up --build
```
Isso irá iniciar:

- 🌐 API: http://localhost:8080
- 🐘 PostgreSQL: localhost:5432
- 📦 MinIO: http://localhost:9000
- 🖥️ Console MinIO: http://localhost:9001
---

## 🧪 Testando a Aplicação

### Documentação da API (Swagger)

Disponível em:  
```
http://localhost:8080/swagger-ui/index.html
```

### 🔐 Login e Autenticação

1. Acesse a rota: `POST /auth/login` com um usuário válido
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

### 🔗 Exemplos de Endpoints da Aplicação

---

#### 🛡️ Autenticação
- `POST /auth/register`: Registro de usuário
- `POST /auth/login`: Login de usuário

#### 👤 Pessoas
- `POST /persons`: Cadastrar pessoa
- `GET /persons/{id}`: Buscar pessoa por ID
- `GET /persons`: Listar todas as pessoas (paginado)
- `PUT /persons/{id}`: Atualizar pessoa
- `DELETE /persons/{id}`: Remover pessoa

#### 🖼️ Fotos de Pessoas
- `POST /person-photos/upload`: Enviar múltiplas fotos de uma pessoa
- `GET /person-photos/{id}`: Buscar foto por ID
- `GET /person-photos`: Listar fotos (paginado)
- `DELETE /person-photos/{id}`: Remover foto

#### 🏠 Endereços
- `POST /addresses`: Cadastrar endereço
- `GET /addresses/{id}`: Buscar endereço por ID
- `GET /addresses`: Listar endereços (paginado)
- `PUT /addresses/{id}`: Atualizar endereço
- `DELETE /addresses/{id}`: Remover endereço

#### 🏙️ Cidades
- `POST /cities`: Cadastrar cidade
- `GET /cities/{id}`: Buscar cidade por ID
- `GET /cities`: Listar cidades (paginado)
- `PUT /cities/{id}`: Atualizar cidade
- `DELETE /cities/{id}`: Remover cidade

#### 🏢 Unidades
- `POST /units`: Cadastrar unidade
- `GET /units/{id}`: Buscar unidade por ID
- `GET /units`: Listar unidades (paginado)
- `PUT /units/{id}`: Atualizar unidade
- `DELETE /units/{id}`: Remover unidade

#### 📋 Lotação
- `POST /assignments` – Cadastrar lotação
- `GET /assignments/{id}` – Buscar lotação por ID
- `GET /assignments` – Listar lotações (paginado)
- `GET /assignments/permanent-employees/by-unit/{unitId}` – Listar servidores efetivos por unidade (paginado)
- `PUT /assignments/{id}` – Atualizar lotação
- `DELETE /assignments/{id}` – Remover lotação

#### 👨‍💼 Servidores Efetivos
- `POST /permanent-employees`: Cadastrar servidor efetivo
- `GET /permanent-employees/{id}`: Buscar servidor efetivo por ID
- `GET /permanent-employees`: Listar servidores efetivos (paginado)
- `PUT /permanent-employees/{id}`: Atualizar servidor efetivo
- `DELETE /permanent-employees/{id}`: Remover servidor efetivo

#### 👨‍🔧 Servidores Temporários
- `POST /temporary-employees`: Cadastrar servidor temporário
- `GET /temporary-employees/{id}`: Buscar servidor temporário por ID
- `GET /temporary-employees`: Listar servidores temporários (paginado)
- `PUT /temporary-employees/{id}`: Atualizar servidor temporário
- `DELETE /temporary-employees/{id}`: Remover servidor temporário

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
