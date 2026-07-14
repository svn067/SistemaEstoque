Nomes:
ARTHUR GONÇALVES DE OLIVEIRA, 
BEATRIZ AYANNA SANTOS DE OLIVEIRA, 
ENZO FERREIRA JOSÉ MATIAS, 
MATHIAS EDUARDO VIANA DE PAULA, 
SAMARA CORDEIRO DE OLIVEIRA


# SistemaEstoque

Sistema web de gerenciamento de estoque desenvolvido em Java com Spring Boot.

O projeto tem como objetivo facilitar o controle de produtos, fornecedores e movimentações de estoque, permitindo cadastrar, consultar e gerenciar informações de forma organizada.

---

## 🚀 Tecnologias utilizadas

- Java
- Spring Boot
- Maven
- JDBC
- SQLite
- HTML5
- CSS3
- JavaScript

---

## 📌 Funcionalidades

### Produtos
- Cadastro de produtos
- Listagem de produtos
- Edição de produtos
- Exclusão de produtos
- Controle de quantidade em estoque

### Fornecedores
- Cadastro de fornecedores
- Validação de dados
- Listagem de fornecedores
- Gerenciamento de fornecedores

### Movimentações
- Registro de entrada de produtos
- Registro de saída de produtos
- Histórico de movimentações

---

## 📂 Estrutura do projeto
SistemaEstoque
│
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.estoque.sistemaestoque
│ │ │ ├── controller
│ │ │ ├── dao
│ │ │ ├── model
│ │ │ └── conexao
│ │ │
│ │ └── resources
│ │ └── static
│ │ ├── html
│ │ ├── css
│ │ └── js
│
└── pom.xml


---

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java JDK instalado
- Maven instalado (ou utilizar o Maven Wrapper)

---

### Executando

Clone o repositório:git clone https://github.com/svn067/SistemaEstoque.git


Entre na pasta:


cd SistemaEstoque


Execute:

Windows:


mvnw spring-boot:run


ou:


mvn spring-boot:run


---

## 🌐 Acesso

Após iniciar o projeto:


http://localhost:8080/html/index.html


---

## 🗄️ Banco de dados

O sistema utiliza SQLite para armazenamento dos dados.

A conexão é realizada utilizando JDBC.

---

## 👨‍💻 Desenvolvimento

Projeto desenvolvido como atividade acadêmica utilizando arquitetura organizada em:

- Controllers
- DAO (Data Access Object)
- Models
- Conexão com banco de dados

---

## 📄 Licença

Projeto desenvolvido para fins educacionais.
