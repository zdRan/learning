package com.zdran.dubbomall.user.service.impl;

import com.zdran.dubbomall.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Create by ranzd on 2018/11/12
 *
 * @author cm.zdran@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
