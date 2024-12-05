package br.com.webhook.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WebhookService {

    private static final Logger log = LogManager.getLogger(WebhookService.class);

    public Mono<Void> processWebhook(String payload) {
        return Mono.fromRunnable(() -> log.info("[WEBHOOK] - Dados Recebidos: {}", payload));
    }
}