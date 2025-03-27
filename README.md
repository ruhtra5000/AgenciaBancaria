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
