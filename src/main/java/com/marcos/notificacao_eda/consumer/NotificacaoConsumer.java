package com.marcos.notificacao_eda.consumer;

import com.marcos.notificacao_eda.config.RabbitMQConfig;
import com.marcos.notificacao_eda.dto.NotificacaoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificacaoConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NOTIFICACAO)
    public void processarNotificacao(NotificacaoRequest request) {
        log.info("[EVENTO RECEBIDO] Processando notificação do tipo: {}", request.getTipo());
        log.info("Enviando e-mail para: {} | Conteúdo: '{}'", request.getEmail(), request.getMensagem());

    // Simula um tempo de processamento (Ex: envio de e-mail real)
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    log.info("Notificação enviada com sucesso para {}!", request.getEmail());
    }
}
