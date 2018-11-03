package com.zdran.dubbo.service.impl;

import com.zdran.dubbo.service.MyService;

/**
 * 提供的服务
 * Create by ranzd on 2018/11/2
 *
 * @author cm.zdran@gmail.com
 */
public class MyServiceImpl implements MyService {
    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
