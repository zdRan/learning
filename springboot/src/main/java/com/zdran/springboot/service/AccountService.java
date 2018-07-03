package com.zdran.springboot.service;

import com.zdran.springboot.dao.AccountInfo;

/**
 * Create by ranzd on 2018/7/2
 *
 * @author cm.zdran@gmail.com
 */

public interface AccountService {
    /**
     * 根据姓名查询账号信息
     * @param name 姓名
     * @return
     */
    AccountInfo queryByName(String name);
}
