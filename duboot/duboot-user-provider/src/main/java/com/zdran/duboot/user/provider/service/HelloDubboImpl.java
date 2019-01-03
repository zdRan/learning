package com.zdran.duboot.user.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zdran.duboot.api.service.HelloDubbo;

/**
 * Create by ranzd on 2018/12/15
 *
 * @author ranzd@chinaunicom.cn
 */
@Service(version = "1.0", timeout = 50000)
public class HelloDubboImpl implements HelloDubbo {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
