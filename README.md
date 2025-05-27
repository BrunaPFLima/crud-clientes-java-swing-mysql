Sistema de Cadastro de Clientes – Java Swing + MySQL
Este é um projeto simples de sistema de cadastro de clientes, desenvolvido como parte de um teste prático. A aplicação implementa as operações CRUD (Create, Read, Update, Delete) utilizando a linguagem Java, com interface gráfica construída com Swing e persistência de dados em banco de dados MySQL.

📋 Funcionalidades
✅ Cadastro de clientes

✅ Consulta de clientes por nome

✅ Edição de dados do cliente

✅ Exclusão de clientes

✅ Validação de campos e máscara de CPF e telefone

✅ Confirmação para exclusão e edição (ponto extra)

✅ Interface amigável usando Java Swing

🧰 Tecnologias Utilizadas
Java 8+

Java Swing (GUI)

JDBC

MySQL

VSCODE (IDE utilizada no desenvolvimento)

💾 Estrutura da Tabela (MySQL)
A tabela utilizada no banco de dados possui a seguinte estrutura:

sql
Copiar
Editar
CREATE DATABASE cadastro_clientes;

USE cadastro_clientes;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100)
);
🛠️ Como Executar o Projeto
Instale o MySQL e crie o banco de dados e tabela como mostrado acima.

Abra o projeto no NetBeans.

Adicione o driver JDBC (mysql-connector-java) ao classpath do projeto.

Configure a conexão com o banco na classe ConexaoBD.java:

java
Copiar
Editar
String url = "jdbc:mysql://localhost:3306/cadastro_clientes";
String user = "root";
String password = ""; // ou a senha do seu MySQL
Execute a classe com a interface gráfica.

Utilize o formulário para cadastrar, buscar, editar e excluir clientes.

📁 Estrutura de Pacotes
conexao – Responsável pela classe de conexão com o banco

dao – Contém as operações CRUD

model – Contém a classe Cliente (modelo)

view – Interface gráfica (Swing)

🎯 Objetivo do Projeto
Este projeto foi desenvolvido como um exercício prático para validar conhecimentos em Java, Swing e banco de dados relacional. É um ótimo ponto de partida para iniciantes em desenvolvimento desktop com Java.

🤝 Contribuição
Este projeto é pessoal, mas sinta-se à vontade para abrir issues ou enviar pull requests para melhorias!
