package com.zdran.springboot.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试项目类
 * Create by zdRan on 2018/6/28
 *
 * @author cm.zdran@gmail.com
 */
@RestController
public class HelloController {
    /**
     * 测试项目
     *
     * @return 字符串
     */
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "hello world,I am Spring Boot";
    }
}