package com.zdran.springboot.service.impl;

import com.zdran.springboot.dao.AccountInfo;
import com.zdran.springboot.dao.AccountInfoExample;
import com.zdran.springboot.mapper.AccountInfoMapper;
import com.zdran.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by ranzd on 2018/7/2
 *
 * @author cm.zdran@gmail.com
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountInfoMapper accountInfoMapper;

    @Override
    public AccountInfo queryByName(String name) {
        AccountInfoExample example = new AccountInfoExample();
        example.createCriteria().andNameEqualTo(name);
        List<AccountInfo> accountInfoList = accountInfoMapper.selectByExample(example);
        if (accountInfoList != null && accountInfoList.size() != 0) {
            return accountInfoList.get(0);
        }
        return null;
    }
}
