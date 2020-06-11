package com.me.demo.provider.serviceImpl;

import com.me.demo.api.service.ValidationService;
import com.me.demo.api.vo.ValidationParameter;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${dubbo.application.version}")
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void save(ValidationParameter parameter) {
        System.out.println(parameter.toString());
    }

    @Override
    public void update(ValidationParameter parameter) {
        System.out.println(parameter.toString());
    }

}
