package com.me.demo.provider.serviceImpl;

import com.me.demo.api.service.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

@Service(version = "${dubbo.application.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "生产者----1: Hello " + name;
    }

    @Override
    public void context() {

        String age = RpcContext.getContext().getAttachment("age");
        System.out.println("接收隐式参数:" + age);

        boolean isProviderSide = RpcContext.getContext().isProviderSide();
        System.out.println("本端是否为提供端:" + isProviderSide);

        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("调用方IP地址:" + clientIP);
        System.out.println(RpcContext.getContext().getUrl());
        String application = RpcContext.getContext().getUrl().getParameter("application");
        System.out.println("当前服务配置信息:" + application);

    }

    @Override
    public String mock() {
        return "service mock";
    }

    @Override
    public CompletableFuture<String> consumerAsync(String name) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CompletableFuture<String> c = new CompletableFuture<>();
        c.complete("service consumerAsync");
        return c;

    }

    @Override
    public CompletableFuture<String> providerAsync(String name) {

        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            // 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 写回响应
            asyncContext.write("Hello " + name + ", response from provider.");
        }).start();
        CompletableFuture<String> c = new CompletableFuture<>();
        c.complete("service providerAsync");
        return c;

    }

}