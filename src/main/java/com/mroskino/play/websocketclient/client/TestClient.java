package com.mroskino.play.websocketclient.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestClient {

    private final WebSocketClient client;

    @Value("${url.websocket}")
    private String address;
    private URI url;

    @PostConstruct
    @SneakyThrows
    private void initialize() {
        url = new URI(address);
        handle();
    }

    public void handle() {
        client.execute()

        )

    }
}
