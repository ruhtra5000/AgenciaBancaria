<H1 align="center">🏦 AgenciaBancaria</H1>
Projeto simples que visa representar uma agência bancária. Desenvolvido como parte da disciplina de Banco de Dados na Universidade Federal do Agreste de Pernambuco (UFAPE).

## 🎯 Objetivo
O objetivo do projeto é realizar quatro consultas especificadas pela docente da disciplina, utilizando um banco de dados relacional e apresentando os resultados em uma interface gráfica.

## 🔍 Consultas Requisitadas
1. Ache todos os clientes do banco que possuem uma conta, mas não um empréstimo

2. Ache os nomes de todos os clientes que moram na mesma rua e na mesma cidade de Smith

3. Crie uma visão que permita ao cliente ver o histórico de transações da sua conta

4. Crie um documento em formato pdf que apresenta  gráficos de barras com o número de transações de depósito por mês nos últimos 5 anos. Um gráfico para os 5 meses  janeiro, outro para os 5 meses fevereiro e assim por diante.

## 🛠 Tecnologias Utilizadas
- **Banco de Dados:** SQL
- **Back-end:** Spring Boot (Java)
- **Interface Gráfica:** Páginas HTML estáticas com comunicação via requisições ao servidor
- **Geração de Gráficos e PDF:** `JFreeChart` e `iTextPDF`

## 📂 Estrutura do Projeto
O projeto segue a estrutura MVC, contendo:
- **Models:** Definição das entidades do banco de dados.
- **Repositories:** Interfaces para interação com o banco de dados.
- **Services:** Regras de negócio e lógica das consultas.
- **Controllers:** Rotas e endpoints da API.
- **HTML Pages:** Interface para exibição dos resultados das consultas.

# Executando o Projeto 🚀

Para executar este projeto corretamente, siga os passos abaixo:

## 1️⃣ Baixe o Projeto
Faça o download do código-fonte ou clone o repositório para sua máquina local.

## 2️⃣ Configure o Banco de Dados  
Crie um banco de dados na sua máquina local com as propriedades descritas no arquivo de configuração da aplicação:  
📄 **Local do arquivo:** `src/main/resources/application.properties`  

Certifique-se de que o banco de dados está configurado corretamente antes de prosseguir.

## 3️⃣ Execute os Scripts SQL  
Para garantir que todas as tabelas, views e dados necessários estejam disponíveis, execute os comandos SQL abaixo no seu banco de dados:

```sql
-- CRIAÇÃO DE TABELAS & VIEWS --

CREATE TABLE IF NOT EXISTS Agencia (
	nomeAgencia varchar(30),
	cidadeAgencia varchar(30) NOT NULL,
	ativos varchar(50),
	PRIMARY KEY (nomeAgencia)
);

CREATE TABLE IF NOT EXISTS Emprestimo (
	numero serial,
	valor numeric NOT NULL,
	nomeAgencia varchar(30),
      dataEmprestimo DATE NOT NULL,
	PRIMARY KEY (numero),
	FOREIGN KEY (nomeAgencia) REFERENCES Agencia(nomeAgencia)
);

CREATE TABLE IF NOT EXISTS Conta (
	numero serial,
	saldo numeric,
	nomeAgencia varchar(30),
	PRIMARY KEY (numero),
	FOREIGN KEY (nomeAgencia) REFERENCES Agencia(nomeAgencia),
	CHECK (saldo >= 0)
);

CREATE TABLE IF NOT EXISTS Cliente (
	nomeCliente varchar(30),
	enderecoCliente varchar(40),
	cidadeCliente varchar(30),
      numeroConta integer,
	PRIMARY KEY (nomeCliente),
      FOREIGN KEY (numeroConta) REFERENCES Conta(numero)
);

CREATE TABLE IF NOT EXISTS Tomador (
	nomeCliente varchar(30),
	numeroEmprestimo integer,
	PRIMARY KEY (nomeCliente, numeroEmprestimo),
	FOREIGN KEY (nomeCliente) REFERENCES Cliente(nomeCliente),
	FOREIGN KEY (numeroEmprestimo) REFERENCES Emprestimo (numero)
);

CREATE TABLE IF NOT EXISTS Depositante (
	nomeCliente varchar(30),
	numeroConta integer,
	PRIMARY KEY (nomeCliente, numeroConta),
	FOREIGN KEY (nomeCliente) REFERENCES Cliente(nomeCliente),
	FOREIGN KEY (numeroConta) REFERENCES Conta (numero)
);


CREATE TABLE IF NOT EXISTS Deposito (
    id serial PRIMARY KEY,
    nomeCliente varchar(30),
    numeroConta integer,
    valor numeric NOT NULL,
    dataDeposito DATE NOT NULL,
    FOREIGN KEY (nomeCliente) REFERENCES Cliente(nomeCliente),
    FOREIGN KEY (numeroConta) REFERENCES Conta(numero)
);


CREATE VIEW HistoricoTransacoes AS 
    SELECT cl.nomeCliente, c.numero AS numeroConta, d.dataDeposito AS   dataTransacao, d.valor, 'Deposito' AS tipoTransacao
    FROM Cliente cl, Deposito d, Conta c
    WHERE cl.nomeCliente = d.nomeCliente AND cl.numeroConta = c.numero

    UNION ALL

    SELECT cl.nomeCliente, c.numero AS numeroConta, e.dataEmprestimo AS dataTransacao, e.valor, 'Emprestimo' AS tipoTransacao
    FROM Cliente cl, Emprestimo e, Conta c
    WHERE e.nomeAgencia = c.nomeAgencia AND cl.numeroConta = c.numero;





-- INSERTS NAS TABELAS --

-- AGENCIA --
INSERT INTO Agencia (nomeAgencia, cidadeAgencia, ativos) VALUES
('Agencia Central', 'São Paulo', '5000000'),
('Agencia Norte', 'Rio de Janeiro', '3200000'),
('Agencia Sul', 'Curitiba', '2800000'),
('Agencia Leste', 'Belo Horizonte', '1500000'),
('Agencia Oeste', 'Porto Alegre', '1700000'),
('Agencia Nordeste', 'Recife', '2100000'),
('Agencia Sudeste', 'São Paulo', '3400000'),
('Agencia Centro-Oeste', 'Goiânia', '1250000'),
('Agencia Norte II', 'Belém', '980000'),
('Agencia Sul II', 'Florianópolis', '1340000'),
('Agencia Sudeste II', 'São Bento do Una', '98000000'),
('Agencia Centro-Oeste II', 'Garanhuns', '134000000');


-- EMPRESTIMO --
INSERT INTO Emprestimo (valor, nomeAgencia, dataEmprestimo) VALUES
(5000, 'Agencia Central', '2020-01-01'),
(10000, 'Agencia Norte', '2020-01-02'),
(7500, 'Agencia Sul', '2020-01-03'),
(3000, 'Agencia Leste', '2020-01-01'),
(4500, 'Agencia Oeste', '2020-01-02'),
(2000, 'Agencia Nordeste', '2020-01-02'),
(8000, 'Agencia Sudeste', '2020-01-03'),
(9000, 'Agencia Centro-Oeste', '2020-01-01'),
(12000, 'Agencia Norte II', '2020-01-02'),
(5500, 'Agencia Sul II', '2020-01-03');


-- CONTA --
INSERT INTO Conta (saldo, nomeAgencia) VALUES
(1500, 'Agencia Central'),
(2200, 'Agencia Norte'),
(800, 'Agencia Sul'),
(4000, 'Agencia Leste'),
(300, 'Agencia Oeste'),
(5000, 'Agencia Nordeste'),
(700, 'Agencia Sudeste'),
(2700, 'Agencia Centro-Oeste'),
(3500, 'Agencia Norte II'),
(600, 'Agencia Sul II'),
(700, 'Agencia Sudeste II'),
(2700, 'Agencia Centro-Oeste II');


-- CLIENTE --
INSERT INTO Cliente (nomeCliente, enderecoCliente, cidadeCliente, numeroConta) VALUES
('Juliana Martins', 'Rua Z, 70', 'Goiânia', 10),
('Rubra', 'Rua Z, 70', 'Goiânia', 11),
('Gordox', 'Rua Z, 70', 'Goiânia', 12),
('Carlos Silva', 'Rua A, 100', 'São Paulo', 1),
('Maria Oliveira', 'Rua B, 200', 'Rio de Janeiro', 2),
('João Souza', 'Rua C, 300', 'Curitiba', 3),
('Ana Lima', 'Rua D, 400', 'Belo Horizonte', 4),
('Paulo Mendes', 'Rua E, 500', 'Porto Alegre' , 5),
('Lucas Santos', 'Rua X, 50', 'Recife', 6),
('Fernanda Costa', 'Rua Y, 60', 'São Paulo', 7),
('Smith', 'Rua Z, 70', 'Goiânia', 8),
('Roberto Rocha', 'Rua Z, 80', 'Goiânia', 9);


-- TOMADOR --
INSERT INTO Tomador (nomeCliente, numeroEmprestimo) VALUES
('Carlos Silva', 1),
('Maria Oliveira', 2),
('Ana Lima', 4),
('Lucas Santos', 6),
('Fernanda Costa', 7),
('Smith', 8),
('Roberto Rocha', 9);


-- DEPOSITANTE --
INSERT INTO Depositante (nomeCliente, numeroConta) VALUES
('Carlos Silva', 1),
('Maria Oliveira', 2),
('João Souza', 3),
('Ana Lima', 4),
('Paulo Mendes', 5),
('Lucas Santos', 6),
('Fernanda Costa', 7),
('Roberto Rocha', 9),
('Juliana Martins', 10);


-- DEPOSITOS --
INSERT INTO Deposito (nomeCliente, numeroConta, valor, dataDeposito) VALUES
('Carlos Silva', 1, 500, '2020-01-01'),
('Carlos Silva', 1, 500, '2021-01-01'),
('Maria Oliveira', 2, 750, '2021-01-02'),
('João Souza', 3, 1200, '2021-01-03'),
('Maria Oliveira', 2, 750, '2022-01-02'),
('João Souza', 3, 1200, '2022-01-03'),
('Maria Oliveira', 2, 750, '2023-01-02'),
('Carlos Silva', 1, 500, '2024-01-01'),
('João Souza', 3, 1200, '2024-01-03'),
('Ana Lima', 4, 450, '2020-02-01'),
('Paulo Mendes', 5, 900, '2020-02-02'),
('Lucas Santos', 6, 300, '2020-02-03'),
('Ana Lima', 4, 450, '2021-02-01'),
('Paulo Mendes', 5, 900, '2021-02-02'),
('Ana Lima', 4, 450, '2022-02-01'),
('Lucas Santos', 6, 300, '2022-02-03'),
('Paulo Mendes', 5, 900, '2023-02-02'),
('Lucas Santos', 6, 300, '2023-02-03'),
('Ana Lima', 4, 450, '2024-02-01'),
('Paulo Mendes', 5, 900, '2024-02-02'),
('Lucas Santos', 6, 300, '2024-02-03'),
('Fernanda Costa', 7, 600, '2020-03-01'),
('Roberto Rocha', 9, 700, '2020-03-02'),
('Juliana Martins', 10, 1100, '2020-03-03'),
('Juliana Martins', 10, 1100, '2021-03-03'),
('Fernanda Costa', 7, 600, '2022-03-01'),
('Roberto Rocha', 9, 700, '2022-03-02'),
('Juliana Martins', 10, 1100, '2022-03-03'),
('Juliana Martins', 10, 1100, '2023-03-03'),
('Fernanda Costa', 7, 600, '2024-03-01'),
('Juliana Martins', 10, 1100, '2024-03-03'),
('Carlos Silva', 1, 1300, '2020-04-01'),
('Maria Oliveira', 2, 400, '2020-04-02'),
('João Souza', 3, 800, '2020-04-03'),
('João Souza', 3, 800, '2021-04-03'),
('Carlos Silva', 1, 1300, '2022-04-01'),
('Maria Oliveira', 2, 400, '2022-04-02'),
('João Souza', 3, 800, '2022-04-03'),
('Carlos Silva', 1, 1300, '2023-04-01'),
('João Souza', 3, 800, '2023-04-03'),
('João Souza', 3, 800, '2024-04-03'),
('Ana Lima', 4, 900, '2020-05-01'),
('Paulo Mendes', 5, 1200, '2020-05-02'),
('Lucas Santos', 6, 100, '2020-05-03'),
('Paulo Mendes', 5, 1200, '2021-05-02'),
('Lucas Santos', 6, 100, '2021-05-03'),
('Ana Lima', 4, 900, '2022-05-01'),
('Lucas Santos', 6, 100, '2022-05-03'),
('Ana Lima', 4, 900, '2023-05-01'),
('Paulo Mendes', 5, 1200, '2023-05-02'),
('Ana Lima', 4, 900, '2024-05-01'),
('Paulo Mendes', 5, 1200, '2024-05-02'),
('Roberto Rocha', 8, 1500, '2021-06-02'),
('Juliana Martins', 9, 2000, '2021-06-03'),
('Fernanda Costa', 7, 700, '2022-06-01'),
('Juliana Martins', 9, 2000, '2022-06-03'),
('Roberto Rocha', 8, 1500, '2023-06-02'),
('Roberto Rocha', 8, 1500, '2024-06-02'),
('Juliana Martins', 9, 2000, '2024-06-03'),
('Carlos Silva', 1, 500, '2020-07-01'),
('Maria Oliveira', 2, 600, '2020-07-02'),
('João Souza', 3, 700, '2020-07-03'),
('Carlos Silva', 1, 500, '2021-07-01'),
('Maria Oliveira', 2, 600, '2021-07-02'),
('João Souza', 3, 700, '2021-07-03'),
('Carlos Silva', 1, 500, '2022-07-01'),
('Carlos Silva', 1, 500, '2024-07-01'),
('Maria Oliveira', 2, 600, '2024-07-02'),
('João Souza', 3, 700, '2024-07-03'),
('Paulo Mendes', 5, 900, '2020-08-02'),
('Ana Lima', 4, 800, '2021-08-01'),
('Paulo Mendes', 5, 900, '2021-08-02'),
('Ana Lima', 4, 800, '2022-08-01'),
('Paulo Mendes', 5, 900, '2022-08-02'),
('Lucas Santos', 6, 1000, '2022-08-03'),
('Lucas Santos', 6, 1000, '2023-08-03'),
('Ana Lima', 4, 800, '2024-08-01'),
('Paulo Mendes', 5, 900, '2024-08-02'),
('Lucas Santos', 6, 1000, '2024-08-03'),
('Fernanda Costa', 7, 1100, '2020-09-01'),
('Roberto Rocha', 9, 1200, '2020-09-02'),
('Juliana Martins', 10, 1300, '2020-09-03'),
('Fernanda Costa', 7, 1100, '2021-09-01'),
('Fernanda Costa', 7, 1100, '2022-09-01'),
('Roberto Rocha', 9, 1200, '2022-09-02'),
('Juliana Martins', 10, 1300, '2022-09-03'),
('Fernanda Costa', 7, 1100, '2023-09-01'),
('Roberto Rocha', 9, 1200, '2023-09-02'),
('Juliana Martins', 10, 1300, '2023-09-03'),
('Fernanda Costa', 7, 1100, '2024-09-01'),
('Roberto Rocha', 9, 1200, '2024-09-02'),
('Juliana Martins', 10, 1300, '2024-09-03'),
('João Souza', 3, 1600, '2020-10-03'),
('Maria Oliveira', 2, 1500, '2021-10-02'),
('João Souza', 3, 1600, '2021-10-03'),
('Carlos Silva', 1, 1400, '2022-10-01'),
('Maria Oliveira', 2, 1500, '2022-10-02'),
('João Souza', 3, 1600, '2022-10-03'),
('Carlos Silva', 1, 1400, '2023-10-01'),
('Maria Oliveira', 2, 1500, '2023-10-02'),
('João Souza', 3, 1600, '2024-10-03'),
('Ana Lima', 4, 1700, '2020-11-01'),
('Paulo Mendes', 5, 1800, '2020-11-02'),
('Lucas Santos', 6, 1900, '2020-11-03'),
('Ana Lima', 4, 1700, '2021-11-01'),
('Paulo Mendes', 5, 1800, '2021-11-02'),
('Lucas Santos', 6, 1900, '2021-11-03'),
('Ana Lima', 4, 1700, '2022-11-01'),
('Paulo Mendes', 5, 1800, '2022-11-02'),
('Lucas Santos', 6, 1900, '2022-11-03'),
('Ana Lima', 4, 1700, '2023-11-01'),
('Paulo Mendes', 5, 1800, '2023-11-02'),
('Lucas Santos', 6, 1900, '2023-11-03'),
('Ana Lima', 4, 1700, '2024-11-01'),
('Paulo Mendes', 5, 1800, '2024-11-02'),
('Lucas Santos', 6, 1900, '2024-11-03'),
('Roberto Rocha', 9, 2100, '2020-12-02'),
('Fernanda Costa', 7, 2000, '2021-12-01'),
('Juliana Martins', 10, 2200, '2021-12-03'),
('Roberto Rocha', 9, 2100, '2022-12-02'),
('Fernanda Costa', 7, 2000, '2023-12-01'),
('Juliana Martins', 10, 2200, '2023-12-03'),
('Roberto Rocha', 9, 2100, '2024-12-02');
```

⚠️ **Observação:** Certifique-se de que sua conexão com o banco de dados está ativa antes de rodar os scripts.

## 4️⃣ Inicie a Aplicação  
Agora que o banco de dados está configurado, execute a aplicação utilizando o Maven/Gradle ou um comando específico do seu framework:

Após isso, a aplicação estará rodando e pronta para uso. 🎉