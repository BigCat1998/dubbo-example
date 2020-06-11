package com.me.demo.consumer.controller;

import com.me.demo.api.service.DemoService;
import com.me.demo.api.service.ValidationService;
import com.me.demo.api.vo.ValidationParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController{

    @Reference(version = "${demo.provider.version}")
    private ValidationService validationService;

    @Reference(version = "${demo.provider.version}")
    private DemoService demoService;

    /**
     * 快速入门
     *
     * @param name
     * @return
     */
    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        String result = null;
        try {
            result = demoService.sayHello(name);
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 参数验证
     *
     * @param parameter
     * @return
     */
    @GetMapping("/save")
    public String save(ValidationParameter parameter) {
        validationService.save(parameter);
        return "ok";
    }

    /**
     * 隐式传参 上下文信息
     *
     * @return
     */
    @GetMapping("/context")
    public String context() {
        // 隐式传参
        RpcContext.getContext().setAttachment("age", "19");
        demoService.context();
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        System.out.println("本端是否为消费端:" + isConsumerSide);

        String serverIP = RpcContext.getContext().getRemoteHost();
        System.out.println("最后一次调用的提供方IP地址:" + serverIP);

        String application = RpcContext.getContext().getUrl().getParameter("application");
        System.out.println("当前服务配置信息:" + application);

        return "ok";
    }

    /**
     * 本地伪装 mock
     *
     * @return
     */
    @GetMapping("/mock")
    public String mock() {
        return demoService.mock();
    }

    /**
     * 消费端异步
     *
     * @return
     */
    @GetMapping("/consumerAsync")
    public String consumerAsync() {
        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = demoService.consumerAsync("async call request");
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        System.out.println("响应返回之前执行");
        return "ok";
    }

    /**
     * 提供端异步
     *
     * @return
     */
    @GetMapping("/providerAsync")
    public String providerAsync() {
        try {
            return demoService.providerAsync("tom").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
