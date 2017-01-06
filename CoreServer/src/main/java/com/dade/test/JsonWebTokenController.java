package com.dade.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/1/6.
 */
@RestController
@RequestMapping("/api/jwt")
public class JsonWebTokenController {

    @Autowired
    Audience audienceEntity;

    @RequestMapping("/oauth/token")
    public Object getAccessToken(@RequestBody LoginPara loginPara) {
        ResultMsg resultMsg;
        try {

            // 验证码校验在后面章节添加


            // 验证用户名密码
            //UserInfo user = userRepositoy.findUserInfoByName(loginPara.getUserName());
            UserInfo user = getFadeUser();
            if (user == null) {
                resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                        ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
                return resultMsg;
            } else {
//                String md5Password = MyUtils.getMD5(loginPara.getPassword() + user.getSalt());
                String md5Password = MyUtils.getMD5(loginPara.getPassword());

                if (md5Password.compareTo(user.getPassword()) != 0) {
                    resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                            ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
                    return resultMsg;
                }
            }

            //拼装accessToken
            String accessToken = JwtHelper.createJWT(loginPara.getMobilePhone(),
                    audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());

            //返回accessToken
            AccessToken accessTokenEntity = new AccessToken();
            accessTokenEntity.setAccess_token(accessToken);
            accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
            accessTokenEntity.setToken_type("bearer");
            resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(),
                    ResultStatusCode.OK.getErrmsg(), accessTokenEntity);
            return resultMsg;

        } catch (Exception ex) {
            resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(),
                    ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);
            return resultMsg;
        }
    }

    private UserInfo getFadeUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("dade");
        userInfo.setPassword("123456");
        return userInfo;
    }

    @RequestMapping("/getaudience")
    public Object getAudience()
    {
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), audienceEntity);
        return resultMsg;
    }

}
