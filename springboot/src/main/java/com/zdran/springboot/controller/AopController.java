package com.zdran.springboot.controller;

import com.zdran.springboot.dao.AccountInfo;
import com.zdran.springboot.service.AopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by ranzd on 2019/4/17
 *
 * @author ranzd@chinaunicom.cn
 */
@RestController
public class AopController {
    private static Logger logger = LoggerFactory.getLogger(AopController.class);

    @Autowired
    private AopService aopService;

    @GetMapping("/helloAop/{name}")
    public String helloAop(@PathVariable("name") String name) {
        logger.info("AOP 接口入参：{}", name);
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setName(name);

        String str = aopService.aopHello(accountInfo);
        logger.info("AOP 接口出参：{}", str);
        return str;
    }
}
