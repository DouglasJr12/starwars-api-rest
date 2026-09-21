# 🌌 Star Wars API — Gerenciamento e Integração de Mundos

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Swagger/OpenAPI](https://img.shields.io/badge/Documentation-Swagger-85EA2D.svg)](https://swagger.io/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Projeto backend desenvolvido em **Java com Spring Boot** com foco em arquitetura limpa em camadas, boas práticas de APIs RESTful, consumo de serviço externo (SWAPI) e interface interativa para testes.

---

## 📌 Sobre o Projeto

A **Star Wars API** é uma aplicação Fullstack pensada como projeto de estudo prático e portfólio. Ela permite o gerenciamento completo (CRUD) de mundos do universo Star Wars salvos localmente, além de se integrar a API pública **SWAPI** para consulta de dados canônicos em tempo real.

O projeto inclui:
- **API REST documentada** com OpenAPI / Swagger.
- **Banco de dados em memória (H2)** para persistência rápida durante o ciclo de desenvolvimento.
- **Frontend nativo (HTML e JavaScript)** integrado aos endpoints da API.
- **Padrão de Commits Semânticos** (*Conventional Commits*).

---

## 🏛️ Arquitetura e Decisões de Design

A aplicação segue uma arquitetura orientada a camadas com estrita separação de responsabilidades:

```
[ Frontend / Cliente HTTP ]
           │
           ▼
    [ WorldController ] ◄──── Documentação Swagger (@Tag, @Operation)
           │
           ▼
     [ WorldService ]  ──────► [ WorldRepository ] ──► [ Banco H2 ]
           │                         ▲
           │                         │
     [ WorldMapper ] ─── Converte DTO ↔ Entity
           │
           ▼
    [ SwapInfoApi ]  ────────► [ RestTemplate ] ────► [ SWAPI Externa ]
```

### Principais Decisões Técnicas:
1. **Isolamento de Entidades (DTO Pattern):** A entidade JPA (`WorldModel`) nunca é exposta diretamente aos clientes da API. A entrada é protegida via `WorldRequest` (com validações de formato) e a saída via `WorldResponse`.
2. **Camada de Mapeamento Dedicada (`WorldMapper`):** A conversão entre DTOs e Model foi encapsulada em um componente próprio (`@Component`), desonerando Controllers e Services dessa responsabilidade.
3. **Validação de Entrada:** Uso do **Jakarta Bean Validation** (`@Valid`, `@NotBlank`, `@Size`) para barrar requisições inválidas no Controller antes que cheguem à camada de negócio.
4. **Integração de APIs Externa Isolada:** O consumo da SWAPI possui seu próprio serviço (`SwapInfoApi`) e seu próprio DTO (`SwapInfoDto`), garantindo desacoplamento entre dados internos e externos.

---

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (In-Memory)
- **Documentação:** Springdoc OpenAPI (Swagger UI)
- **Validações:** Jakarta Validation API
- **Produtividade:** Lombok
- **Comunicação Externa:** Spring RestTemplate
- **Frontend:** HTML5, CSS3, JavaScript (Fetch API)

---

## 📋 Endpoints da API

A documentação interativa completa pode ser acessada via Swagger UI com a aplicação em execução.

| Método | Endpoint | Resumo / Descrição | Status Sucesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/mundos` | **Cadastrar mundo:** Cadastra um novo mundo no banco de dados local. | `201 Created` |
| `GET` | `/api/mundos/local` | **Consultar mundos locais:** Retorna todos os mundos cadastrados localmente. | `200 OK` |
| `GET` | `/api/mundos/externo/{id}` | **Consultar mundo na SWAPI:** Consulta um mundo na API externa SWAPI utilizando seu ID. | `200 OK` |
| `PUT` | `/api/mundos/{id}` | **Atualizar um mundo:** Atualiza os dados de um mundo existente. | `200 OK` |
| `DELETE` | `/api/mundos/{id}` | **Excluir mundo:** Exclui um mundo cadastrado localmente utilizando seu ID. | `204 No Content` |

---

## 💻 Como Executar o Projeto

### Pré-requisitos
- **Java JDK 17** ou superior instalado.
- **Maven** instalado (ou utilize o wrapper `./mvnw` incluso no projeto).
- Git configurado na máquina.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/DouglasJr12/starwars-api.git
   cd starwars-api
   ```

2. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse as interfaces pelo navegador:**
    - **Frontend Interativo:** [http://localhost:8080](http://localhost:8080)
    - **Swagger UI (Documentação):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
    - **Console H2 (Banco de dados):**