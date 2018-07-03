package com.zdran.springboot.controller;

import com.zdran.springboot.dao.AccountInfo;
import com.zdran.springboot.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by ranzd on 2018/7/3
 *
 * @author cm.zdran@gmail.com
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("/get/{name}")
    public AccountInfo getAccountByName(@PathVariable String name) {
        logger.info("根据姓名获取账号信息。入参：name：{}", name);
        AccountInfo accountInfo = accountService.queryByName(name);
        if (accountInfo == null) {
            logger.info("根据姓名获取账号信息。获取失败");
        }
        logger.info("根据姓名获取账号信息。出参：accountInfo：{}", accountInfo.toString());
        return accountInfo;
    }

}
