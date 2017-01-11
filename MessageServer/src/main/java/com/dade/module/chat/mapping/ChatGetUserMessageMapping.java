package com.dade.module.chat.mapping;



import com.dade.MessageServerApplication;
import com.dade.common.LogUtil;
import com.dade.framework.MappingResponse;
import com.dade.module.MessageBasicMapping;
import com.dade.module.MessageErrorCode;
import com.dade.module.chat.ChatDtoFactory;
import com.dade.module.chat.ChatService;
import com.dade.module.chat.dto.ChatGetUserMessageDto;
import com.dade.module.chat.dto.ChatMessageDto;
import com.dade.module.chat.model.ChatMessage;
import com.dade.network.DefaultDtoListDto;

import java.util.List;

/**
 * 获得指定用户消息列表
 * Created by dade on 2016/1/10
 */
public class ChatGetUserMessageMapping extends MessageBasicMapping<ChatGetUserMessageDto>
{
    @Override
    public Class<ChatGetUserMessageDto> getDtoClass()
    {
        return ChatGetUserMessageDto.class;
    }

    @Override
    public MappingResponse execute()
    {
        String ownerId              = getClient().getUserId();
        ChatGetUserMessageDto dto   = getDto();
        String chatUserId           = dto.getUserId();
        String messageId            = dto.getMessageId();
        int count                   = dto.getCount();

        if (count <= 0)
            return error(MessageErrorCode.OTHER_ERROR);

        ChatService chatService = MessageServerApplication.getContext().getBean(ChatService.class);
        List<ChatMessage> chatMessageList = chatService.showMessageList(ownerId, chatUserId, messageId, count);
        List<ChatMessageDto> chatMessageDtoList = ChatDtoFactory.createChatMessageDtoList(chatMessageList);

        LogUtil.debug("聊天系统-获得指定用户消息列表. ownerId: " + ownerId + "chatUserId: "+ chatUserId + ", messageId: "
                + messageId + ", count: "+ count + ", " + chatMessageDtoList);

        return ok(new DefaultDtoListDto<>(chatMessageDtoList));
    }
}
