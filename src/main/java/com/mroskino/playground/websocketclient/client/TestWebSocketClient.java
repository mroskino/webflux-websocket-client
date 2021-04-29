package com.mroskino.playground.websocketclient.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestWebSocketClient {

    private final WebSocketClient client;

    private WebSocketSession session;

    @Value("${url.websocket-server}")
    private String address;

    @SneakyThrows
    @PostConstruct
    private void initialize() {
        client.execute(new URI(address), this::handle)
                .subscribe();

        Flux.interval(Duration.ofMillis(1000))
                .thenMany(s -> sendMessage("ping"))
                .subscribe();
    }

    public Mono<Void> sendMessage(String message) {
        if (session == null || !session.isOpen()) {
            return Mono.error(new RuntimeException("No connection to server"));
        }

        return session.send(Mono.just(session.textMessage(message)))
                .doOnSuccess(v ->  log.info("WebSocket session {} sent message: {}", session.getId(), message));
    }

    private Mono<Void> handle(WebSocketSession session) {
        this.session = session;
        log.info("WebSocket session {} opened", session.getId());

        return session.receive()
                .doOnNext(m -> log.info("WebSocket session {} received message: {}", session.getId(),  m.getPayloadAsText()))
                .doOnTerminate(() -> log.info("WebSocket session {} closed", session.getId()))
                .then();
    }

}