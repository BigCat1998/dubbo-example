package com.me.demo.consumer.controller.callback;

import com.me.demo.api.service.callback.CallbackListenerImpl;
import com.me.demo.api.service.callback.CallbackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
public class CallbackController{

    @Reference(version = "${demo.provider.version}")
    private CallbackService callbackService;

    /**
     * 参数回调
     *
     * @return
     */
    @GetMapping("/addListener")
    public String addListener() {
        // 这里直接使用 lambda 会出现序列化，这里实例化一个CallbackListener 问题解决
        callbackService.addListener("http://localhost:9092/demo/addListener", new CallbackListenerImpl());
        return "ok";
    }
}
