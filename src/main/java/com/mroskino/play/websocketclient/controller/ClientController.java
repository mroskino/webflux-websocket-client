package com.mroskino.play.websocketclient.controller;

import com.mroskino.play.websocketclient.client.TestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final TestClient client;

    @PostMapping("/test")
    public void test() {
        client.handle();
    }
}
