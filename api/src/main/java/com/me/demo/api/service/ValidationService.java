package com.me.demo.api.service;

import com.me.demo.api.vo.ValidationParameter;

public interface ValidationService {

    @interface Save {}
    void save(ValidationParameter parameter);

    void update(ValidationParameter parameter);
}