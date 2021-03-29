package com.mroskino.play.websocketclient.controller;

import com.mroskino.play.websocketclient.client.TestClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final TestClient client;

    public ClientController(TestClient client) {
        this.client = client;
    }

    @PostMapping("/test")
    public void test() {
        client.handle();
    }
}
