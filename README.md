# EasyRH - Backend

Bem-vindo ao repositório do backend do EasyRH! Este projeto é a parte do servidor da aplicação EasyRH, desenvolvida em Spring Boot, que fornece serviços e APIs para gerenciar recursos humanos, incluindo autenticação, gerenciamento de usuários, manipulação de dados de RH e muito mais.

Arquitetura em camadas e voltada ao DDD

## Requisitos

Antes de começar, verifique se você possui as seguintes ferramentas instaladas em sua máquina:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 11 ou superior)
- [Apache Maven](https://maven.apache.org/) (v3.x ou superior)
- [MySQL](https://www.mysql.com/) (ou outro banco de dados relacional compatível)

## Instalação e Execução

1. Clone este repositório em sua máquina local:

git clone https://github.com/seu-usuario/easyrh-backend.git

2. Acesse o diretório do projeto:

3. Configure o banco de dados MySQL:
   - Crie um banco de dados chamado `easyrh`.
   - Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu banco de dados.

4. Execute o projeto usando o Maven:
mvn spring-boot:run

O servidor será iniciado e estará pronto para receber solicitações.

## Estrutura de Pacotes

src/
├── main/
│ ├── java/
│ │ └── br.com.easyrh/
│ │ ├── config/
│ │ ├── controller/
│ │ ├── dto/
│ │ ├── model/
│ │ ├── repository/
│ │ ├── security/
│ │ ├── service/
│ │ └── EasyRhApplication.java
│ └── resources/
│ ├── application.properties
│ ├── db/
│ │ └── migration/
│ └── static/
└── test/
└── java/
└── br.com.easyrh/
└── controller/


- **`config/`**: Contém configurações do Spring, como configurações de segurança e configurações de beans.
- **`controller/`**: Contém classes controladoras que definem os endpoints da API REST.
- **`dto/`**: Contém classes DTO (Data Transfer Object) para representar dados transferidos entre a camada de controle e a camada de serviço.
- **`model/`**: Contém classes de entidade JPA que representam os modelos de dados do aplicativo.
- **`repository/`**: Contém interfaces de repositório JPA para interagir com o banco de dados.
- **`security/`**: Contém configurações de segurança, como filtros de autenticação e autorização.
- **`service/`**: Contém classes de serviço que implementam a lógica de negócios da aplicação.
- **`EasyRhApplication.java`**: Arquivo de inicialização do Spring Boot que inicia o aplicativo.

## Como Contribuir

Sinta-se à vontade para contribuir com este projeto! Basta seguir as etapas descritas no arquivo [CONTRIBUTING.md](CONTRIBUTING.md).

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

