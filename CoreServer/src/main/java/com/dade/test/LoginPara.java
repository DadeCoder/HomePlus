package com.dade.test;

/**
 * 登录获取token时，所需要的认证信息类
 * Created by Dade on 2017/1/6.
 */
public class LoginPara {

    private String mobilePhone;
    private String password;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginPara{" +
                "mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
