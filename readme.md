
<div align="center">

--- 

### 🛠️ Desenvolvido por Marcos Araújo

*Engenheiro de Software & Desenvolvedor Full-Stack*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/marcos-araujo-517201212/)
[![WhatsApp](https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white)](https://wa.me/5511940292792)

</div>

# 🔄 Notificação EDA - Serviço de Mensageria Assíncrona

Este projeto é uma demonstração prática de uma **Arquitetura Orientada a Eventos (EDA - Event-Driven Architecture)** utilizando **Java 21**, **Spring Boot 3** e **RabbitMQ**.

O objetivo da aplicação é resolver o problema de requisições bloqueantes em APIs REST. Ao receber um pedido de notificação, o sistema responde imediatamente ao cliente (`202 Accepted`) e delega o processamento pesado (envio de e-mail/notificação) para um consumidor de fila assíncrono.

---

## 🏗️ Arquitetura do Sistema

```text
[ Cliente / REST ] 
       │
       ▼  (HTTP POST / 202 Accepted)
┌──────────────────────────────┐
│  Producer (Spring Boot API)  │
└──────────────┬───────────────┘
               │ (Publica evento)
               ▼
┌──────────────────────────────┐
│   RabbitMQ (Message Broker)  │
│   - Exchange                 │
│   - Queue: notificacao.v1    │
└──────────────┬───────────────┘
               │ (Consome mensagem)
               ▼
┌──────────────────────────────┐
│  Consumer (Spring Boot Worker)│
└──────────────────────────────┘
```

# 🛠️ Tecnologias Utilizadas

* Java 21
* Spring Boot 3.3.4
  * Spring Web
  * Spring AMQP (RabbitMQ)
  * Jackson Databind (Serialização Json)
* RabbitMQ (Message Broker)
* Docker & Focker Compose
* Lombok

# 🚀 Como Executar o Projeto

## Pré-requisitos:
* Java 21+ instalado
* Docker e Docker Compose instalados

## 1. Subir o Message Brocker (RabbitMQ)
Na raiz do projeto, execute o comando para iniciar o container do RabbitMQ;

```docker compose up -d```

## 🌐 Painel de Controle do RabbitMQ:
> Acesse: http://localhost:15672

## 2. Executar a aplicação Spring Boot
Execute a aplicacao via Maven:

> ./mvnw spring-boot:run

## 🧪 Como Testar
Você pode enviar uma requisição HTTP POST para enfileirar uma nova notificação:

```curl -X POST http://localhost:8080/api/v1/notificacoes \
  -H "Content-Type: application/json" \
  -d '{
    "email": "dev@marcos.com",
    "mensagem": "Seu cadastro foi realizado com sucesso!",
    "tipo": "BOAS_VINDAS"
  }'
```

## 📊 Resposta Esperada da API:

```HTTP/1.1 202 Accepted
Notificação enfileirada com sucesso para: dev@marcos.com
```

## 📄 Log do consumidor (Processamento em segundo plano):

```📩 [EVENTO RECEBIDO] Processando notificação do tipo: BOAS_VINDAS
Enviando e-mail para: dev@marcos.com | Conteúdo: 'Seu cadastro foi realizado com sucesso!'
✅ Notificação enviada com sucesso para dev@marcos.com!
```






