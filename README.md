# Fórum Hub API

![Status](https://img.shields.io/badge/status-concluído-brightgreen)

## 📖 Sobre o Projeto

API REST para um fórum de discussão, desenvolvida como parte do Challenge Back-End do programa Oracle Next Education (ONE) em parceria com a Alura. A API permite que utilizadores se autentiquem e gerenciem tópicos de discussão (criar, ler, atualizar e apagar).

---

## ✨ Funcionalidades

-   **Autenticação:** Sistema de autenticação e autorização via Tokens JWT.
-   **Gestão de Tópicos:** CRUD completo de Tópicos.
    -   Criar um novo tópico (requer autenticação).
    -   Listar todos os tópicos ativos com paginação.
    -   Atualizar um tópico existente (requer autenticação).
    -   "Apagar" um tópico (exclusão lógica, requer autenticação).
-   **Validações:** Impede o registo de tópicos com título e mensagem duplicados.

---

## 🛠️ Tecnologias Utilizadas

As seguintes ferramentas e tecnologias foram utilizadas na construção do projeto:

-   **Java 17**
-   **Spring Boot 3**
-   **Spring Security** (para autenticação/autorização com JWT)
-   **Spring Data JPA** (para persistência de dados)
-   **Maven** (para gestão de dependências)
-   **Lombok** (para reduzir código boilerplate)
-   **Auth0 Java JWT** (para geração e validação de tokens)
-   **Banco de Dados H2** (utilizado no ambiente de desenvolvimento)

---

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SeuUsuario/challenge-oracle-one-forumhub.git](https://github.com/SeuUsuario/challenge-oracle-one-forumhub.git)
    ```
    *(Substitua pelo link do seu repositório)*

2.  **Abra o projeto** na sua IDE preferida (ex: IntelliJ IDEA ou Eclipse).

3.  **Aguarde o Maven descarregar as dependências** do projeto, o que deve acontecer automaticamente.

4.  **Execute a classe principal** `ForumhubApplication.java`.

5.  A API estará disponível em `http://localhost:8080`. Um utilizador de teste (`admin@forum.hub` / `123456`) é criado automaticamente na primeira execução.

---

## 🔌 Endpoints da API

A API expõe os seguintes endpoints:

| Método | Endpoint         | Protegido? | Descrição                                        |
| :----- | :--------------- | :--------: | :------------------------------------------------- |
| `POST` | `/login`         |     ❌     | Autentica um utilizador e devolve um token JWT.      |
| `GET`  | `/topicos`       |     ❌     | Lista todos os tópicos ativos de forma paginada.   |
| `POST` | `/topicos`       |     ✅     | Cria um novo tópico.                               |
| `PUT`  | `/topicos/{id}`  |     ✅     | Atualiza um tópico existente.                      |
| `DELETE`| `/topicos/{id}` |     ✅     | "Apaga" um tópico (exclusão lógica).             |

**Nota sobre a Paginação:** O endpoint `GET /topicos` suporta parâmetros de paginação, como `?size=5&page=1&sort=titulo,asc`.

**Nota sobre a Autenticação:** Para aceder aos endpoints protegidos (✅), é necessário enviar o token JWT no cabeçalho `Authorization` da requisição, no formato `Bearer {seu_token}`.

---

## ✒️ Autor

**Natalino Leite**

-   LinkedIn: [https://www.linkedin.com/in/seu-linkedin/](https://www.linkedin.com/in/natalino-leite-dev/)
-   GitHub: [https://github.com/SeuUsuario](https://github.com/NatalinoLeite)

*(Substitua pelos seus dados)*
