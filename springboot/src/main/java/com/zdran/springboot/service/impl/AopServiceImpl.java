package com.zdran.springboot.service.impl;

import com.zdran.springboot.dao.AccountInfo;
import com.zdran.springboot.service.AopService;
import org.springframework.stereotype.Service;

/**
 * Create by ranzd on 2019/4/16
 *
 * @author cm.zdran@gmail.com
 */
@Service
public class AopServiceImpl implements AopService {
    @Override
    public String aopHello(AccountInfo accountInfo) {

        return "hello," + accountInfo.getName();
    }
}
