package com.mroskino.playground.websocketclient.controller;

import com.mroskino.playground.websocketclient.api.SendMessageRequest;
import com.mroskino.playground.websocketclient.client.TestWebSocketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final TestWebSocketClient client;

    @PostMapping("/send")
    public Mono<Void> sendMessage(@RequestBody SendMessageRequest request) {
        return client.sendMessage(request.getMessage());
    }
}
