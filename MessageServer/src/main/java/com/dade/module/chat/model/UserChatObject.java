package com.dade.module.chat.model;

import com.dade.common.mongodb.MongoObject;
import com.dade.module.user.MessageUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * 用户聊天消息
 * Created by dade on 2016/1/10
 */
@Document(collection="user_chat")
public class UserChatObject implements MongoObject
{
    @Id
    private String id;
    private String userId;              // 用户ID
    private List<ChatUser> chatUsers;   // 聊天用户

    public static final String FIELD_USER_ID = "userId";

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ChatUser> getChatUsers() {
        return chatUsers;
    }

    public void setChatUsers(List<ChatUser> chatUsers) {
        this.chatUsers = chatUsers;
    }

    private String chatUsersToString ()
    {
        if (chatUsers == null || chatUsers.isEmpty())
            return "{}";

        StringBuilder chatUsersString = new StringBuilder("[");
        for (ChatUser chatUser : chatUsers)
        {
            chatUsersString
                    .append("{unreadCount=").append(chatUser.getUnreadCount());

            MessageUser messageUser = chatUser.getUser();
            if (messageUser == null)
            {
                chatUsersString.append(",userId=NULL, userType=NULL, nick=NULL}, ");
                continue;
            }

            chatUsersString
                    .append(", userId=").append(messageUser.getUserId())
                    .append(", userType=").append(messageUser.getUserType())
                    .append(", nick=").append(messageUser.getNick()).append("}, ");
        }

        return chatUsersString.append("]").toString();
    }

    @Override
    public String toString() {
        return "UserChatObject{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", chatUsers=" + chatUsersToString() +
                '}';
    }
}
