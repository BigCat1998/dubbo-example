package com.me.demo.api.service.callback;

public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}