package com.dade.module.user;


import com.dade.module.chat.model.UserChatObject;

/**
 * 消息服用户
 * Created by dade on 2016/1/10
 */
public class MessageUserObject
{
    private String userId;              // 用户ID
    private MessageUser messageUser;    // 消息用户

    private UserChatObject userChatObject;

    public MessageUserObject(MessageUser messageUser)
    {
        this.userId = messageUser.getUserId();
        this.messageUser = messageUser;
    }

    public String getUserId() {
        return userId;
    }

    public MessageUser getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(MessageUser messageUser) {
        this.messageUser = messageUser;
    }

    public UserChatObject getUserChatObject() {
        return userChatObject;
    }

    public void setUserChatObject(UserChatObject userChatObject) {
        this.userChatObject = userChatObject;
    }

    @Override
    public String toString() {
        return "MessageUserObject{" +
                "userId='" + userId + '\'' +
                ", messageUser=" + messageUser +
                '}';
    }
}
