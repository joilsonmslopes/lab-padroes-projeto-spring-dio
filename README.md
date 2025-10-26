# API de Gerenciamento de Clientes

Esta é uma API RESTful simples para realizar operações de CRUD (Criar, Ler, Atualizar, Deletar) em uma entidade de Cliente.

Este foi um desafio prático do curso **Design Patterns com Java: Dos Clássicos (GoF) ao Spring Framework** na **DIO**

O projeto demonstra o uso dos padrões **Singleton**, **Strategy** e **Facade** em um cenário real com Spring Boot:

- Singleton: Beans Spring (@Service, @Component) garantem uma única instância das dependências.

- Strategy: Interface ClientService define o contrato e a implementação ClientServiceImpl fornece a estratégia concreta de negócio.

- Facade: _ViaCepService_ encapsula a integração com a API externa ViaCEP, expondo uma interface simples ao controlador.

O projeto utiliza **Java** e **Spring Boot** para expor os endpoints.

## Endpoints da API

O prefixo base para todos os endpoints é `/api/client`.

| Método | Rota | Descrição | Corpo da Requisição (Payload) | Resposta (Sucesso) |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/` | Lista todos os clientes cadastrados. | N/A | `200 OK` - `List<ClientResponseDTO>` |
| `GET` | `/{id}` | Busca um cliente específico pelo seu ID. | N/A | `200 OK` - `ClientResponseDTO` |
| `POST` | `/` | Cria um novo cliente. | `ClientRequestDTO` | `200 OK` - `ClientResponseDTO` |
| `PUT` | `/{id}` | Atualiza um cliente existente com base no ID. | `ClientRequestDTO` | `200 OK` - `ClientResponseDTO` |
| `DELETE` | `/{id}` | Remove um cliente do sistema pelo ID. | N/A | `204 No Content` |

---

## Modelos de Dados (DTOs)

A API utiliza DTOs (Data Transfer Objects) para enviar e receber dados, garantindo que apenas informações pertinentes sejam expostas.

### ClientRequestDTO

Este DTO é esperado no corpo (body) das requisições `POST` (criar) e `PUT` (atualizar).

* A anotação `@Valid` indica que os campos deste DTO passam por validação (ex: campos obrigatórios, formato de e-mail, etc.).
* *Nota: A estrutura exata (campos) está definida na classe `ClientRequestDTO`.*

### ClientResponseDTO

Este DTO é retornado no corpo das respostas `GET`, `POST` e `PUT` bem-sucedidas, contendo os dados do cliente.

* *Nota: A estrutura exata (campos) está definida na classe `ClientResponseDTO`.*

---

## Tratamento de Erros

A API utiliza códigos de status HTTP padrão para indicar o resultado das operações:

* **200 OK**: A requisição foi bem-sucedida (padrão para `GET`, `POST`, `PUT`).
* **204 No Content**: A requisição `DELETE` foi bem-sucedida e não há conteúdo para retornar.
* **400 Bad Request**: Retornado por `PUT` em caso de falha na atualização (ex: erro no processamento ou dados inválidos não capturados pela validação inicial).
* **404 Not Found**: Retornado por `GET /{id}` ou `DELETE /{id}` quando o cliente com o ID especificado não é encontrado no banco de dados.

---

## Dependencias do projeto
- Spring Web
- Spring Data JPA
- Lombok
- [OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- H2 Database (como banco de dados em memória)

## API externa
- [ViaCep](https://viacep.com.br/) (utilizada para cadastrar o endereço do cliente pelo cep)