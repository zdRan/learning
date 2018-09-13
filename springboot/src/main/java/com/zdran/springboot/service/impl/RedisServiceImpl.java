package com.zdran.springboot.service.impl;

import com.zdran.springboot.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Create by ranzd on 2018/9/11
 *
 * @author cm.zdran@gmail.com
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);


    @Override
    public void setStr(String key, String value) {
        logger.info("插入 Redis 数据。入参：key：{}，value：{}", key, value);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
    }

    @Override
    public String getStr(String key) {
        logger.info("获取 Redis 数据。入参：key：{}", key);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String value = operations.get(key);
        logger.info("获取 Redis 数据。出参：value：{}", value);
        return value;
    }

    @Override
    public void delStr(String key) {
        logger.info("删除 Redis 数据。入参：key：{}", key);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(key);
    }
}
