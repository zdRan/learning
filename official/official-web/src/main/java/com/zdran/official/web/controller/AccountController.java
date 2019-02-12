package com.zdran.official.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zdran.duboot.official.api.OfficialHelloApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by ranzd on 2019/1/29
 *
 * @author ranzd@chinaunicom.cn
 */
@RestController
public class AccountController {

    @Reference(version = "1.0")
    private OfficialHelloApi officialHelloApi;

    @GetMapping("/account/{name}")
    public String sayHello(@PathVariable(name = "name") String name) {
        return officialHelloApi.sayHello(name);
    }

}
