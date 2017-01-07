package com.dade.user.hunter;

import com.dade.baisc.BasicModelObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Dade on 2016/12/25.
 */
@Document(collection = "hunter_user")
public class HunterUser extends BasicModelObject{

    @Id
    private String id;

    // 注册
    private String phoneNumber;                         // 手机号
    private String verificationCode;                    // 验证码
    private String nickName;                            // 昵称
    private String password;                            // 密码

    // 个人中心
    private String imageHeaderUrl;



    public static final String FIELD_PHONE_NUMBER = "phoneNumber";

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageHeaderUrl() {
        return imageHeaderUrl;
    }

    @Override
    public String toString() {
        return "HunterUser{" +
                "id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", imageHeaderUrl='" + imageHeaderUrl + '\'' +
                '}';
    }
}
