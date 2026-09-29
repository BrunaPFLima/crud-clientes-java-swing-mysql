<p align="center">
  <img width="100%" src="https://capsule-render.vercel.app/api?type=waving&color=0:3C1361,50:6A0DAD,100:C77DFF&height=180&section=header&text=Cadastro%20de%20Clientes&fontSize=46&fontColor=ffffff&fontAlignY=36&desc=CRUD%20desktop%20com%20Java%20Swing%20%2B%20JDBC&descSize=17&descAlignY=58&animation=fadeIn" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-8+-6A0DAD?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-9D4EDD?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/JDBC-9D4EDD?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/H2-modo%20MySQL-C77DFF?style=for-the-badge&logo=databricks&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-C77DFF?style=for-the-badge&logo=apachemaven&logoColor=white" />
</p>

---

## 💜 Sobre

Aplicação desktop de **cadastro de clientes** com as quatro operações de um CRUD, feita com **Java Swing** e acesso a banco via **JDBC puro**, seguindo o padrão **MVC + DAO**.

O banco usado é o **H2 em modo de compatibilidade com MySQL**: ele é criado sozinho na primeira execução, então não precisa instalar nada além do Java.

Projeto desenvolvido como teste prático para validar conhecimentos de Java, interface gráfica e banco de dados relacional.

## ✨ Funcionalidades

- ➕ **Incluir** clientes (CPF, nome, e-mail e telefone)
- 📋 **Listar** todos os clientes numa tabela
- ✏️ **Atualizar** o cliente selecionado na tabela
- 🗑️ **Excluir** com **confirmação** antes de apagar
- 🧹 **Limpar** o formulário
- ✅ **Validação**: CPF e nome são obrigatórios
- 🗄️ Tabela criada automaticamente se ainda não existir

## 🧠 O que pratiquei aqui

- Montagem de telas com **Swing** (`GridBagLayout`, `JTable`, `JOptionPane`)
- **JDBC** com `DataSource`, `PreparedStatement` e *try-with-resources*
- Separação de responsabilidades em **model / dao / view**
- Projeto organizado com **Maven**

## 🚀 Como rodar

**Pré-requisitos:** Java 8 ou superior e Maven.

```bash
# 1. Clone o repositório
git clone https://github.com/BrunaPFLima/crud-clientes-java-swing-mysql.git
cd crud-clientes-java-swing-mysql/cadastro-clientes-novo

# 2. Compile e abra a janela
mvn compile exec:java
```

> Também dá pra abrir a pasta `cadastro-clientes-novo` no IntelliJ, Eclipse ou VS Code e rodar a classe `com.exemplo.Main`.

O banco fica no arquivo `src/main/resources/pessoadb.mv.db`, criado na primeira execução.

## 🗄️ Tabela

```sql
CREATE TABLE IF NOT EXISTS pessoas (
    cpf      VARCHAR(20) PRIMARY KEY,
    nome     VARCHAR(100),
    email    VARCHAR(100),
    telefone VARCHAR(20)
);
```

## 🗂️ Estrutura

```
cadastro-clientes-novo/src/main/java/com/exemplo
├── Main.java          # Ponto de entrada — abre a janela
├── model/Pessoa.java  # Modelo com os dados do cliente
├── dao/PessoaDAO.java # Conexão H2 e operações de CRUD via JDBC
└── view/PessoaForm.java # Tela Swing: formulário, botões e tabela
```

---

<p align="center">
  Feito com 💜 por <a href="https://github.com/BrunaPFLima">Bruna Lima</a> ·
  <a href="https://www.linkedin.com/in/bruna-lima-205360144/">LinkedIn</a>
</p>

<p align="center">
  <img width="100%" src="https://capsule-render.vercel.app/api?type=waving&color=0:C77DFF,50:6A0DAD,100:3C1361&height=90&section=footer" />
</p>
