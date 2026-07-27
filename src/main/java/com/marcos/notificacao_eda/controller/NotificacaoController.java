package com.marcos.notificacao_eda.controller;

import com.marcos.notificacao_eda.config.RabbitMQConfig;
import com.marcos.notificacao_eda.dto.NotificacaoRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificacoes")
public class NotificacaoController {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<String> enviar(@RequestBody NotificacaoRequest request) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NOTIFICACAO,
                RabbitMQConfig.ROUTING_KEY,
                request
        );

        return ResponseEntity.accepted().body("Notificacao enfileirada com sucesso para: " + request.getEmail());
    }
}
