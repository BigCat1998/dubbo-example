package com.me.demo.consumer.controller;

import com.me.demo.api.service.DemoService;
import com.me.demo.consumer.ConsumerApplication;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class DemoControllerTest {

    @Reference(version = "1.0.0")
    private DemoService demoService;

    @Test
    public void testGetName() {
        String result = demoService.sayHello("tom");
        System.out.println(result);

    }

}
