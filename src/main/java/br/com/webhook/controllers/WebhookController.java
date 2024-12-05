package br.com.webhook.controllers;

import br.com.webhook.services.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/webhook")
public class WebhookController {

    private final WebhookService webhookService;

    @Autowired
    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    public Mono<HttpStatus> handleWebhook(@RequestBody String payload) {
        return webhookService.processWebhook(payload)
                .then(Mono.just(HttpStatus.OK));
    }
}
