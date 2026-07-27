package com.marcos.notificacao_eda.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NOTIFICACAO = "notificacao.v1,queue";
    public static final String EXCHANGE_NOTIFICACAO = "notificacao.v1.exchange";
    public static final String ROUTING_KEY = "notificacao.v1.routingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NOTIFICACAO, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NOTIFICACAO);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

