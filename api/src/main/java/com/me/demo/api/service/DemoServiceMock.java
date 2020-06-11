package com.me.demo.api.service;

import java.util.concurrent.CompletableFuture;

public class DemoServiceMock implements DemoService {

    @Override
    public String sayHello(String name) {
        return "mock sayHello";
    }

    @Override
    public void context() {
        System.out.println("mock context");
    }

    @Override
    public String mock() {
        return "mock mock";
    }

    @Override
    public CompletableFuture<String> consumerAsync(String name) {
        return null;
    }

    @Override
    public CompletableFuture<String> providerAsync(String name) {
        CompletableFuture<String> mockRe = new CompletableFuture<>();
        mockRe.complete("mock providerAsync");
        return mockRe;
    }

}
