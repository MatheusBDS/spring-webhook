package br.com.webhook.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.webhook.services.WebhookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(WebhookController.class)
public class WebhookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private WebhookService webhookService;

    @Test
    void testHandleWebhook() {
        String payload = "{\"event\":\"test\"}";

        when(webhookService.processWebhook(payload)).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/api/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .exchange()
                .expectStatus().isOk();

        verify(webhookService).processWebhook(payload);
    }
}