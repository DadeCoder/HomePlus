package com.dade.module.user.dto;


import com.dade.network.IDto;

/**
 * 登录请求Dto
 * Created by dade on 2016/1/10
 */
public class LoginDto implements IDto
{
    private String userId;      // 用户ID
    private String password;    // socketio连接密码

    private String compactJws;  // v2.0 add for jwt

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompactJws() {
        return compactJws;
    }

    public void setCompactJws(String compactJws) {
        this.compactJws = compactJws;
    }
}
