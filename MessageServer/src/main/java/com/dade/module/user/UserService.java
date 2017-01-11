package com.dade.module.user;


import com.dade.api.UserRpc;
import com.dade.common.LogUtil;
import com.dade.dto.UserDto;
import com.dade.framework.client.MessageClient;
import com.dade.framework.client.MessageClientPool;
import com.dade.framework.client.MessageClientStatus;
import com.dade.module.DtoResultWrapper;
import com.dade.module.MessageErrorCode;
import com.dade.module.chat.ChatService;
import com.dade.module.user.dto.LoginedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.Key;
import java.util.Collection;

/**
 * 用户服务类
 * Created by dade on 2016/1/10
 */
@Component
public class UserService
{

    @Resource
    UserRpc userRpc;

    @Autowired
    ChatService chatService;

    /**
     * 查找Root消息用户
     * @return      消息用户
     */
    public MessageUser findRootMessageUser()
    {
//        UserDto userDto = userRpc.getUserName();
        UserDto userDto = new UserDto();
        MessageUser messageUser = MessageUserDtoFactory.createMessageUser(userDto);
        return messageUser;
    }

    /**
     * 查找消息用户
     * 注: 与 get 不同的是如果不在线则会rpc 查找
     * @param userId    用户ID
     * @return          消息用户
     */
    public MessageUser findMessageUser(String userId)
    {
        MessageUserObject userObject = MessageUserPool.get(userId);
        if (userObject != null)
            return userObject.getMessageUser();

//        UserDto userDto = userRpc.getUser(userId);
        UserDto userDto = userRpc.getDto();
        return MessageUserDtoFactory.createMessageUser(userDto);
    }

    /**
     * 获得在线的用户对象
     * @param userId    用户ID
     * @return          消息用户实体
     */
    public MessageUserObject getUserObject (String userId)
    {
        return MessageUserPool.get(userId);
    }

    /**
     * 检测用户是否在线
     * @param userId    用户ID
     * @return          true,false
     */
    public boolean isOnline (String userId)
    {
        return MessageUserPool.contains(userId);
    }

    /**
     * 用户登陆
     * @param client    连接
     * @param userId    用户ID
     * @return          登录返回包装器
     */
    public DtoResultWrapper<LoginedDto> login (MessageClient client, String userId)
    {
        // 检测是否已在线, 在线则初始化连接并返回
        MessageUserObject userObject = MessageUserPool.get(userId);
        if (userObject != null)
        {
            client.setUserId(userId);
            client.setObject(userObject);
            client.setStatus(MessageClientStatus.LOGINED);

            // 连接注册
            MessageClientPool.getInstance().addUserIdIndex(client);
            LogUtil.info("登录, 用户已在线, 加载新连接数据, "+ userObject + client);

            return new DtoResultWrapper<>(
                    new LoginedDto(userObject.getMessageUser().getNick()));
        }

        // 检测是否存在用户
//        UserDto userDto = userRpc.getUser(userId);
        UserDto userDto = userRpc.getDto();
        // TODO login userRPC
        if (userDto == null)
            return new DtoResultWrapper<>(MessageErrorCode.LOGIN_NO_USER);

        // 生成用户对象
        MessageUser messageUser = MessageUserDtoFactory.createMessageUser(userDto);
        userObject = new MessageUserObject(messageUser);

        client.setUserId(userId);
        client.setObject(userObject);
        client.setStatus(MessageClientStatus.LOGINED);

        onLogin(userObject);

        // 连接注册, 用户
        MessageClientPool.getInstance().addUserIdIndex(client);
        MessageUserPool.register(userObject);
        LogUtil.info("用户登录上线, "+ userObject);

        return new DtoResultWrapper<>(
                new LoginedDto(userObject.getMessageUser().getNick()));
    }

    /**
     * 用户下线
     * @param userId    用户ID
     */
    public void logout (String userId)
    {
        LogUtil.info("强制用户下线, userId: "+ userId);

        Collection<MessageClient> clientCollection = MessageClientPool.getInstance().getByUserId(userId);
        if (clientCollection == null || clientCollection.isEmpty())
            return;

        clientCollection.forEach(MessageClient::close);
    }

    /**
     * 登录处理
     * @param userObject    用户对象
     */
    private void onLogin (MessageUserObject userObject)
    {
        // 每个系统的上线调用
        chatService.onLogin(userObject);
    }

    /**
     * 下线处理
     * @param userObject    用户对象
     */
    public void onLogout (MessageUserObject userObject)
    {
        // 每个系统的下线调用
        chatService.onLogout(userObject);

        MessageUserPool.unregister(userObject);

        LogUtil.info("用户下线完毕, "+ userObject);
    }

    public String getUserPhone(String userId){
//        UserDto userDto = userRpc.getUser(userId);
        UserDto userDto = new UserDto();
        String mobile = userDto.getMobile();
        return mobile;
    }

//    public byte[] getUserKey(){
//        byte[] key = userRpc.getJWTKey();
//        return key;
//    }

}
