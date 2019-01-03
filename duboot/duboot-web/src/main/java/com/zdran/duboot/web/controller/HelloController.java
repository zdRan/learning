package com.zdran.duboot.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zdran.duboot.api.service.HelloDubbo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by ranzd on 2018/12/16
 *
 * @author cm.zdran@gmail.com
 */

@RestController
public class HelloController {

    @Reference(version = "1.0")
    private HelloDubbo helloDubbo;

    @GetMapping("/duboot/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloDubbo.sayHello(name);
    }
}
