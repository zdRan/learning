package com.zdran.springboot.service;

/**
 * Create by ranzd on 2018/9/11
 *
 * @author cm.zdran@gmail.com
 */
public interface RedisService {
    /**
     * 插入数据
     * @param key
     * @param value
     */
    void setStr(String key, String value);

    /**
     * 从redis中获取数据
     * @param key
     * @return
     */
    String getStr(String key);

    /**
     * 删除数据
     * @param key
     */
    void delStr(String key);
}
