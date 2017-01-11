package com.dade.rpcimpl;


import com.dade.module.chat.ChatService;
import com.dade.rpcapi.api.ChatRpc;
import com.dade.rpcapi.dto.chat.MessageContentDto;
import com.dade.rpcapi.dto.user.MessageUserDto;
import com.dade.rpcapi.dto.user.SimpleMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息RPC实现类
 * Created by dade on 2016/1/10
 */
@Service("chatRpcImpl")
public class ChatRpcImpl implements ChatRpc
{

    @Autowired
    ChatService chatService;

    /**
     * 系统发送消息给列表
     * @param messageUserDtoList
     * @param content   消息内容
     */
    @Override
    public void systemSendMessageToList(List<MessageUserDto> messageUserDtoList, MessageContentDto content)
    {
        chatService.systemSendMessageToList(messageUserDtoList, content);
    }

    /**
     * 系统发送消息给指定用户
     * @param receiver  接收者
     * @param content   消息内容
     */
    @Override
    public void systemSendMessage(MessageUserDto receiver, MessageContentDto content)
    {
        chatService.systemSendMessageToUser(receiver.getUserId(),content);
    }

    /**
     * 用户发送消息到指定用户
     * 注: 应用于业务提醒, 如 A推荐了候选人到B
     * @param sender    发送者
     * @param receiver  接收者
     * @param content   消息内容
     */
    @Override
    public void sendMessage(MessageUserDto sender, MessageUserDto receiver, MessageContentDto content)
    {
        chatService.sendMessageToUser(sender.getUserId(),receiver.getUserId(),content);
    }

    /**
     * 得到用户所有未读消息总数
     * @param userId
     * @return
     */
    @Override
    public SimpleMessageDto getSimpleMessageDto(String userId) {
        SimpleMessageDto simpleMessageDto = chatService.getSimpleMessageDto(userId);
        return simpleMessageDto;
    }
}
