# Developer Projects

**Developer Projects** é uma plataforma que permite que desenvolvedores cadastrem suas informações, gerenciem seus projetos e vinculem tecnologias específicas a cada um. O projeto segue princípios de **Clean Architecture** e **Clean Code**, garantindo alta manutenibilidade e escalabilidade.

A aplicação conta com **autenticação JWT** para segurança e foi desenvolvida utilizando:
- **Java 21**
- **Spring Boot** (Data JPA, Security, Validation)
- **PostgreSQL** (migrações com Flyway)
- **ModelMapper** para mapeamento de objetos
- **Lombok** para reduzir código boilerplate

---

## 🚀 Como rodar o projeto localmente

### 1️⃣ Clonar o repositório
```sh
git clone git@github.com:HericlesSouza/developers-project.git
cd developer-projects
```

### 2️⃣ Configurar o banco de dados
O projeto utiliza **PostgreSQL**. Certifique-se de que ele esteja instalado e rodando na sua máquina.

Crie um banco de dados:
```sql
CREATE DATABASE developer_projects;
```

### 3️⃣ Configurar variáveis de ambiente
As configurações do projeto devem ser definidas no arquivo application.properties, localizado em src/main/resources/application.properties. E você deverá criar duas variáveis de ambientes no seu sistema:

```env
POSTGRES_USER=seu_usuario
POSTGRES_PASSWORD=sua_senha
```

### 4️⃣ Instalar as dependências
```sh
./mvnw clean install
```

### 5️⃣ Rodar a aplicação
```sh
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 📌 Endpoints da API

### 🔐 Autenticação
| Verbo  | Rota                 | Descrição |
|--------|----------------------|------------|
| POST   | `/auth/register`     | Cadastrar um novo desenvolvedor. |
| POST   | `/auth/login`        | Realizar login e obter token JWT. |

### 👨‍💻 Desenvolvedores
| Verbo  | Rota                      | Descrição |
|--------|---------------------------|------------|
| GET    | `/developers`              | Listar todos os desenvolvedores. |
| GET    | `/developers/{id}`         | Obter detalhes de um desenvolvedor. |
| PATCH  | `/developers/{id}`         | Atualizar dados de um desenvolvedor. |
| DELETE | `/developers/{id}`         | Remover um desenvolvedor. |
| POST   | `/developers/{id}/infos`   | Cadastrar informações adicionais de um desenvolvedor. |
| PATCH  | `/developers/{id}/infos`   | Atualizar informações do desenvolvedor. |
| GET    | `/developers/{id}/projects` | Listar todos os projetos de um desenvolvedor. |

### 📂 Projetos
| Verbo  | Rota                                  | Descrição |
|--------|--------------------------------------|------------|
| POST   | `/projects`                          | Criar um novo projeto. |
| GET    | `/projects`                          | Listar todos os projetos. |
| GET    | `/projects/{id}`                     | Obter detalhes de um projeto. |
| PATCH  | `/projects/{id}`                     | Atualizar informações de um projeto. |
| DELETE | `/projects/{id}`                     | Remover um projeto. |
| POST   | `/projects/{id}/technologies`       | Adicionar uma tecnologia a um projeto. |
| DELETE | `/projects/{id}/technologies/{name}` | Remover uma tecnologia de um projeto. |

---

## 🔄 Importação de Rotas no Insomnia ou Postman

Para facilitar os testes da API, você pode importar todas as rotas diretamente no **Insomnia** ou no **Postman**.

📥 **Baixar export do Insomnia:** [Insomnia_Export.json](./DevelopersInsomnia.json)

### Como Importar no Insomnia:
1. Abra o Insomnia
2. Vá para **Application > Import/Export**
3. Clique em **Import Data** e selecione o arquivo JSON baixado
4. Todas as rotas estarão disponíveis para uso

### Como Importar no Postman:
1. Abra o Postman
2. Vá para **File > Import**
3. Selecione o arquivo JSON baixado
4. As rotas serão adicionadas automaticamente

---

## 🛠 Tecnologias Utilizadas
- **Spring Boot** para construção da API
- **Spring Security** para autenticação JWT
- **PostgreSQL** como banco de dados relacional
- **Flyway** para controle de migrações do banco
- **Lombok** para redução de código repetitivo
- **ModelMapper** para conversão entre DTOs e entidades

---

## 📩 Contato
Caso tenha dúvidas ou sugestões, entre em contato:

- **Email:** [hericlessouza01@gmail.com](mailto:hericlessouza01@gmail.com)
- **LinkedIn:** [Hericles Souza](https://www.linkedin.com/in/hericlessouza/)

🚀 **Developer Projects - Gerencie seus projetos com eficiência!**