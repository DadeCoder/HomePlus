package com.dade.module.chat.mapping;



import com.dade.MessageServerApplication;
import com.dade.framework.MappingResponse;
import com.dade.framework.client.MessageClient;
import com.dade.module.MessageBasicMapping;
import com.dade.module.MessageErrorCode;
import com.dade.module.chat.ChatService;
import com.dade.module.chat.dto.ChatUserDto;
import com.dade.module.chat.model.ChatUser;
import com.dade.module.chat.model.UserChatObject;
import com.dade.module.user.MessageUser;
import com.dade.module.user.MessageUserDtoFactory;
import com.dade.module.user.UserService;
import com.dade.network.DefaultStringDto;
import com.dade.rpcapi.dto.user.MessageUserDto;

import java.util.Date;
import java.util.List;

/**
 * 点击顾问头像，跳转到聊天界面，将该顾问加到聊天好友
 * Created by dade on 2016/1/10
 */
public class ChatAddUserToListMapping extends MessageBasicMapping<DefaultStringDto> {


    @Override
    public Class<DefaultStringDto> getDtoClass() {
        return DefaultStringDto.class;
    }

    @Override
    public MappingResponse execute() {

        MessageClient client = getClient();
        DefaultStringDto dto = getDto();

        ChatService chatService = MessageServerApplication.getContext().getBean(ChatService.class);
        UserService userService = MessageServerApplication.getContext().getBean(UserService.class);
        UserChatObject userChatObject = chatService.findUserChatObject(client.getUserId());

        if (userChatObject == null)
            return error(MessageErrorCode.LOGIN_NO_USER);

        // 获取现有的列表
        List<ChatUser> chatUserList = userChatObject.getChatUsers();

        // 获取要更新到List的顾问Id
        String userId = dto.getString();

        // 查看之前列表中是否已有该用户
        Boolean isNewUser = true;
        ChatUser newUser = new ChatUser();
        MessageUser messageUser = userService.findMessageUser(userId);
        for (ChatUser chatUser : chatUserList){
            if (chatUser.getUser().getUserId().equals(userId)) {
                isNewUser = false;
                newUser = chatUser;
                break;
            }
        }

        if (isNewUser == true){
            newUser.setUser(messageUser);
            newUser.setUnreadCount(0);
            newUser.setLastMessageContent(null);
            newUser.setLastMessageDate(new Date());
        }
        else{
            newUser.setLastMessageDate(new Date());
        }

        chatUserList.add(newUser);
        userChatObject.setChatUsers(chatUserList);

        // 返回该顾问的相关信息
        ChatUserDto chatUserDto = new ChatUserDto();
        MessageUserDto messageUserDto = MessageUserDtoFactory.createMessageUserDto(messageUser);
        chatUserDto.setUser(messageUserDto);
        chatUserDto.setLastMessageDate(null);
        chatUserDto.setUnreadCount(0);
        chatUserDto.setLastMessageContent(null);

        return ok(chatUserDto);
    }
}
