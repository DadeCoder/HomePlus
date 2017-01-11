package com.dade.module.user;


import com.dade.dto.UserDto;
import com.dade.rpcapi.dto.user.MessageUserDto;
import org.springframework.beans.BeanUtils;

/**
 * 消息用户Dto工厂
 * Created by dade on 2016/1/10
 */
public class MessageUserDtoFactory
{

    /**
     * 创建消息用户实体
     * @param userDto
     * @return
     */
    public static MessageUser createMessageUser (UserDto userDto)
    {
        MessageUser messageUser = new MessageUser();

        BeanUtils.copyProperties(userDto, messageUser);
        messageUser.setUserType(1);

        return messageUser;
    }

    /**
     * 创建消息用户实体
     * @param messageUserDto
     * @return
     */
    public static MessageUser createMessageUser (MessageUserDto messageUserDto)
    {
        MessageUser messageUser = new MessageUser();
        BeanUtils.copyProperties(messageUserDto, messageUser);
        return messageUser;
    }

    /**
     * 创建消息用户Dto
     * @param messageUser
     * @return
     */
    public static MessageUserDto createMessageUserDto (MessageUser messageUser)
    {
        MessageUserDto messageUserDto = new MessageUserDto();
        BeanUtils.copyProperties(messageUser, messageUserDto);
        return messageUserDto;
    }

}
