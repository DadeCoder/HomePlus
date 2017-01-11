package com.dade.module.chat;


import com.dade.module.chat.dto.ChatMessageDto;
import com.dade.module.chat.dto.ChatUserDto;
import com.dade.module.chat.model.ChatMessage;
import com.dade.module.chat.model.MessageContent;
import com.dade.module.chat.model.UserChatObject;
import com.dade.module.user.MessageUserDtoFactory;
import com.dade.rpcapi.dto.chat.MessageContentDto;
import com.dade.rpcapi.dto.user.MessageUserDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天dto工厂
 * Created by dade on 2016/1/10
 */
public final class ChatDtoFactory
{
    /**
     * 创建聊天用户dto list
     * @param userChat  用户聊天对象
     * @return
     */
    public static List<ChatUserDto> createChatUserDtoList (UserChatObject userChat)
    {
        List<ChatUserDto> chatUserList = new ArrayList<>();

        userChat.getChatUsers().forEach(chatUser->{
            if (chatUser.getUser() == null)
                return;

            ChatUserDto chatUserDto = new ChatUserDto();
            chatUserDto.setUser(MessageUserDtoFactory.createMessageUserDto(chatUser.getUser()));
            chatUserDto.setUnreadCount(chatUser.getUnreadCount());

            if (chatUser.getLastMessageContent() != null)
            {
                chatUserDto.setLastMessageContent(createMessageContentDto(chatUser.getLastMessageContent()));
                chatUserDto.setLastMessageDate(chatUser.getLastMessageDate());
            }

            chatUserList.add(chatUserDto);
        });

        return chatUserList;
    }

    /**
     * 创建消息dto list
     * @param chatMessageList   消息list
     * @return
     */
        public static List<ChatMessageDto> createChatMessageDtoList (List<ChatMessage> chatMessageList)
    {
        List<ChatMessageDto> chatMessageDtoList = new ArrayList<>();
        chatMessageList.forEach(chatMessage->
                chatMessageDtoList.add(createChatMessageDto(chatMessage)));
        return chatMessageDtoList;
}

    /**
     * 创建消息Dto
     * @param chatMessage   消息
     * @return
     */
    public static ChatMessageDto createChatMessageDto (ChatMessage chatMessage)
    {
        ChatMessageDto chatMessageDto = new ChatMessageDto();

        MessageUserDto receiverDto = MessageUserDtoFactory
                .createMessageUserDto(chatMessage.getReceiver());
        MessageUserDto senderDto = MessageUserDtoFactory
                .createMessageUserDto(chatMessage.getSender());
        chatMessageDto.setId(chatMessage.getId());
        chatMessageDto.setReceiver(receiverDto);
        chatMessageDto.setSender(senderDto);
        chatMessageDto.setContent(createMessageContentDto(chatMessage.getContent()));
        chatMessageDto.setSendDate(chatMessage.getSendDate());
        return chatMessageDto;
    }

    /**
     * 创建消息内容实体
     * @param messageContentDto 消息内容Dto
     * @return
     */
    public static MessageContent createMessageContent (MessageContentDto messageContentDto)
    {
        MessageContent messageContent = new MessageContent();
        BeanUtils.copyProperties(messageContentDto, messageContent);
        return messageContent;
    }

    /**
     * 创建消息内容Dto
     * @param messageContent 消息内容
     * @return
     */
    public static MessageContentDto createMessageContentDto (MessageContent messageContent)
    {
        MessageContentDto messageContentDto = new MessageContentDto();
        BeanUtils.copyProperties(messageContent, messageContentDto);
        return messageContentDto;
    }
}
