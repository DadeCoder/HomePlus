package com.dade.module.chat.mapping;


import com.dade.MessageServerApplication;
import com.dade.common.LogUtil;
import com.dade.framework.MappingResponse;
import com.dade.framework.client.MessageClient;
import com.dade.module.MessageBasicMapping;
import com.dade.module.MessageErrorCode;
import com.dade.module.chat.ChatService;
import com.dade.module.chat.dto.ChatUserSendMessageDto;

/**
 * 发送消息
 * Created by dade on 2016/1/10
 */
public class ChatSendMessageMapping extends MessageBasicMapping<ChatUserSendMessageDto>
{

    @Override
    public Class<ChatUserSendMessageDto> getDtoClass() {
        return ChatUserSendMessageDto.class;
    }

    @Override
    public MappingResponse execute()
    {
        MessageClient client        = getClient();
        ChatUserSendMessageDto dto  = getDto();
        ChatService chatService     = MessageServerApplication.getContext().getBean(ChatService.class);

        MessageErrorCode errorCode = chatService.sendMessageToUser(client.getUserId(), dto.getReceiverId(), dto.getMessage());

        LogUtil.debug("聊天系统-发送消息. "+ errorCode +" ownerId: " + client.getUserId() + ", "+ dto);
        return result(errorCode);
    }
}