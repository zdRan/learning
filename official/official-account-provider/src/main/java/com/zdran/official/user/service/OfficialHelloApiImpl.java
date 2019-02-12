package com.zdran.official.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zdran.duboot.official.api.OfficialHelloApi;

/**
 * Create by ranzd on 2019/1/28
 *
 * @author ranzd@chinaunicom.cn
 */
@Service(version = "1.0", timeout = 50000)
public class OfficialHelloApiImpl implements OfficialHelloApi {
    @Override
    public String sayHello(String name) {
        return "helll, " + name;
    }
}
