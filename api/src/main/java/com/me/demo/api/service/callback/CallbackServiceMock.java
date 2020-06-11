package com.me.demo.api.service.callback;

public class CallbackServiceMock implements CallbackService{

    @Override
    public void addListener(String key, CallbackListener listener) {
        System.out.println("mock addListener");
    }

}