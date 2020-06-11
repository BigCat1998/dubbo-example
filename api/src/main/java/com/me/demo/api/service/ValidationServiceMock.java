package com.me.demo.api.service;

import com.me.demo.api.vo.ValidationParameter;

public class ValidationServiceMock implements ValidationService {

    @Override
    public void save(ValidationParameter parameter) {
        System.out.println("mock 参数验证 保存成功");
    }

    @Override
    public void update(ValidationParameter parameter) {
        System.out.println("mock 参数验证 修改成功");
    }
}