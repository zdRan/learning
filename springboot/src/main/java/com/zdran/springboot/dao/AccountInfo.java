package com.zdran.springboot.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "AccountInfo",
        description = "用户信息实体类"
)
public class AccountInfo {

    @ApiModelProperty(
            value = "账户id",
            example = "123455",
            dataType = "Integer"
    )
    private Integer accountId;

    @ApiModelProperty(
            value = "用户名",
            example = "zdran",
            dataType = "String"
    )
    private String name;

    @ApiModelProperty(
            value = "密码",
            example = "zdran",
            dataType = "String"
    )
    private String pwd;

    @ApiModelProperty(
            value = "余额",
            example = "zdran",
            dataType = "Ingeger"
    )
    private Integer balance;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"accountId\":")
                .append(accountId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"pwd\":\"")
                .append(pwd).append('\"');
        sb.append(",\"balance\":")
                .append(balance);
        sb.append('}');
        return sb.toString();
    }
}