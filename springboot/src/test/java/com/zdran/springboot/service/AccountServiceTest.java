package com.zdran.springboot.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create by ranzhendong on 2019-09-12
 *
 * @author ranzhendong@maoyan.com
 */

public class AccountServiceTest {
    @Test
    public void testJson(){
        JSONObject menu = JSONObject.parseObject(String.valueOf("{\\\"summary\\\":{\\\"meituanprice\\\":28.60,\\\"maxprice\\\":36.00,\\\"ismax\\\":false},\\\"comment\\\":\\\"\\\",\\\"title\\\":\\\"\\\",\\\"sections\\\":[{\\\"title\\\":\\\"\\\",\\\"items\\\":[{\\\"amount\\\":\\\"1桶\\\",\\\"price\\\":0.00,\\\"subtotal\\\":0.00,\\\"name\\\":\\\"爆米花\\\",\\\"capacity\\\":\\\"46oz\\\"},{\\\"amount\\\":\\\"1杯\\\",\\\"price\\\":0.00,\\\"subtotal\\\":0.00,\\\"name\\\":\\\"可乐\\\",\\\"capacity\\\":\\\"12oz\\\"},{\\\"amount\\\":\\\"1瓶\\\",\\\"price\\\":0.00,\\\"subtotal\\\":0.00,\\\"name\\\":\\\"c胞活力水\\\",\\\"capacity\\\":\\\"330ml\\\"}]}],\\\"desc\\\":\\\"\\\"}".replace("\\","")));
        System.out.println(menu.toJSONString());
    }

}