package com.dade.module.chat.mapping;



import com.dade.MessageServerApplication;
import com.dade.common.LogUtil;
import com.dade.framework.MappingResponse;
import com.dade.framework.client.MessageClient;
import com.dade.module.MessageBasicMapping;
import com.dade.module.MessageErrorCode;
import com.dade.module.chat.ChatDtoFactory;
import com.dade.module.chat.ChatService;
import com.dade.module.chat.dto.ChatUserDto;
import com.dade.module.chat.model.UserChatObject;
import com.dade.network.DefaultDtoListDto;
import com.dade.network.DefaultEmptyDto;

import java.util.Collections;
import java.util.List;

/**
 * 聊天系统-获取聊天的用户列表
 * Created by dade on 2016/1/10
 */
public class ChatGetUserListMapping extends MessageBasicMapping<DefaultEmptyDto>
{
    @Override
    public Class<DefaultEmptyDto> getDtoClass()
    {
        return DefaultEmptyDto.class;
    }

    @Override
    public MappingResponse execute()
    {
        MessageClient client = getClient();

        ChatService chatService = MessageServerApplication.getContext().getBean(ChatService.class);
        UserChatObject userChatObject = chatService.findUserChatObject(client.getUserId());
        if (userChatObject == null)
            return error(MessageErrorCode.LOGIN_NO_USER);

        List<ChatUserDto> chatUserList = ChatDtoFactory.createChatUserDtoList(userChatObject);

        Collections.sort(chatUserList);

        LogUtil.debug("聊天系统-获取聊天的用户列表. ownerId:" + client.getUserId() + " " + chatUserList);
        return ok(new DefaultDtoListDto<>(chatUserList));
    }
}
