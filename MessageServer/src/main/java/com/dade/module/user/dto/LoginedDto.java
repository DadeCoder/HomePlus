package com.dade.module.user.dto;


import com.dade.network.IDto;

/**
 * 登录成功返回
 * Created by dade on 2016/1/10
 */
public class LoginedDto implements IDto
{
    private String user;      // 用户名称

    public LoginedDto(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
