package com.me.demo.api.service;

import java.util.concurrent.CompletableFuture;

public interface DemoService {
    String sayHello(String name);
    void context();
    String mock();
    CompletableFuture<String> consumerAsync(String name);
    CompletableFuture<String> providerAsync(String name);
}