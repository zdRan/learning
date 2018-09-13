package com.zdran.springboot.service.impl;

import com.zdran.springboot.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Create by ranzd on 2018/9/13
 *
 * @author cm.zdran@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisServiceImplTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void setStr() throws Exception {
        redisService.setStr("TEST","1234");
    }

    @Test
    public void getStr() throws Exception {
        System.out.println(redisService.getStr("TEST"));
    }

    @Test
    public void delStr() throws Exception {
        redisService.delStr("TEST");
    }

}