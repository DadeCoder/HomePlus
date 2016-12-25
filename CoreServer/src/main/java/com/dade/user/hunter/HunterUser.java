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






    @Override
    public String getId() {
        return null;
    }
}
