package com.zdran.springboot.controller;

import com.zdran.springboot.dao.AccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 参数传递测试
 * Create by ranzd on 2018/7/25
 *
 * @author cm.zdran@gmail.com
 */
@RestController
@RequestMapping("/parameter")
public class ParameterController {
    private Logger logger = LoggerFactory.getLogger(ParameterController.class);

    /**
     * 接收 /learning/parameter/getString/abc 的参数
     *
     * @param str abc
     * @return
     */
    @GetMapping("/getString/{str}")
    public String getString(@PathVariable(value = "str") String str) {
        logger.info("GET 传参，传递基本类型。str：{}", str);
        return "收到参数：" + str;
    }

    /**
     * 接收 /learning/parameter/getName?name=abc 的参数
     *
     * @param name abc
     * @return
     */
    @GetMapping("/getName")
    public String getName(@RequestParam(value = "name") String name) {
        logger.info("GET 传参，传递基本类型。str：{}", name);
        return "收到参数：" + name;
    }

    /**
     * 接收 /learning/parameter/postString/abc 的参数
     *
     * @param str abc
     * @return
     */
    @PostMapping("/postString/{str}")
    public String postString(@PathVariable(value = "str") String str) {
        logger.info("POST 传参，传递基本类型。str：{}", str);
        return "收到参数：" + str;
    }

    /**
     * 接收 /learning/parameter/postName?name=abc 的参数
     *
     * @param name abc
     * @return
     */
    @PostMapping("/postName")
    public String postName(@RequestParam(value = "name") String name) {
        logger.info("POST 传参，传递基本类型。str：{}", name);
        return "收到参数：" + name;
    }

    /**
     * 接收 /learning/parameter/getAccount 的参数
     *
     * @param accountInfo abc
     * @return
     */
    @PostMapping("/postAccount")
    public AccountInfo postAccount(@RequestBody AccountInfo accountInfo) {
        logger.info("GET 传参，传递基本类型。str：{}", accountInfo);
        return accountInfo;
    }

    @PostMapping("/postNames")
    public List<String> postNames(@RequestBody String[] names) {
        logger.info("GET 传参，传递基本类型。str：{}", Arrays.asList(names).toString());
        return Arrays.asList(names);
    }

    @PostMapping("/postAccountList")
    public List<AccountInfo> postAccountList(@RequestBody List<AccountInfo> accounts) {
        logger.info("GET 传参，传递基本类型。str：{}", accounts.toString());
        return accounts;
    }
}
