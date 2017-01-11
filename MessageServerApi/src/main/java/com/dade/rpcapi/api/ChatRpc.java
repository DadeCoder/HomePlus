package com.dade.rpcapi.api;


import com.dade.rpcapi.dto.chat.MessageContentDto;
import com.dade.rpcapi.dto.user.MessageUserDto;
import com.dade.rpcapi.dto.user.SimpleMessageDto;

import java.util.List;

/**
 * 聊天rpc
 * Created by Dade on 2017/1/10.
 */
public interface ChatRpc
{

    /**
     * 系统发送消息给列表
     * @param content   消息内容
     */
    void systemSendMessageToList (List<MessageUserDto> messageUserDtoList, MessageContentDto content);

    /**
     * 系统发送消息给指定用户
     * @param receiver  接收者
     * @param content   消息内容
     */
    void systemSendMessage (MessageUserDto receiver, MessageContentDto content);

    /**
     * 用户发送消息到指定用户
     * 注: 应用于业务提醒, 如 A推荐了候选人到B
     * @param sender    发送者
     * @param receiver  接收者
     * @param content   消息内容
     */
    void sendMessage (MessageUserDto sender, MessageUserDto receiver, MessageContentDto content);

    SimpleMessageDto getSimpleMessageDto(String userId);

}
