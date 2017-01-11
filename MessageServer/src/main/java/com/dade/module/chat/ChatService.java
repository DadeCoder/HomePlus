package com.dade.module.chat;


import com.dade.common.LogUtil;
import com.dade.module.MessageErrorCode;
import com.dade.module.SendFacotry;
import com.dade.module.chat.dto.ChatMessageDto;
import com.dade.module.chat.dto.ChatUserDto;
import com.dade.module.chat.model.ChatMessage;
import com.dade.module.chat.model.ChatUser;
import com.dade.module.chat.model.MessageContent;
import com.dade.module.chat.model.UserChatObject;
import com.dade.module.user.MessageUser;
import com.dade.module.user.MessageUserDtoFactory;
import com.dade.module.user.MessageUserObject;
import com.dade.module.user.UserService;
import com.dade.rpcapi.dto.chat.MessageContentDto;
import com.dade.rpcapi.dto.user.MessageUserDto;
import com.dade.rpcapi.dto.user.SimpleMessageDto;
import com.dade.rpcapi.enums.MessageContentType;
import com.dade.rpcapi.exception.MessageServerDubboException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 聊天服务类
 * Created by dade on 2016/1/10
 */
@Component
public class ChatService
{
    @Autowired
    UserService userService;

    @Autowired
    UserChatMongoDao userChatMongoDao;

    @Autowired
    ChatMessageMongoDao chatMessageMongoDao;

    // 系统消息线程池
    private static final ExecutorService SystemMessageThreadPool = Executors.newFixedThreadPool(5);

    /**
     * 上线初始化各种数据
     * @param userObject
     */
    public void onLogin (MessageUserObject userObject)
    {
        UserChatObject userChatObject = userChatMongoDao.findOne(UserChatObject.FIELD_USER_ID, userObject.getUserId());
        if (userChatObject != null)
        {
            userObject.setUserChatObject(userChatObject);
            LogUtil.debug("上线处理-聊天系统获得用户聊天对象, " + userObject + " " + userChatObject);
            return;
        }

        userChatObject = initMessageUserObject(userObject.getUserId());
        userObject.setUserChatObject(userChatObject);

        LogUtil.info("上线处理-聊天系统初始化创建用户聊天对象, " + userObject + " " + userChatObject);
    }

    /**
     * 下线时存储数据等处理
     * @param user
     */
    public void onLogout (MessageUserObject user)
    {
        UserChatObject userChatObject = user.getUserChatObject();
        userChatMongoDao.upsert(UserChatObject.FIELD_USER_ID, user.getUserId(), userChatObject);

        LogUtil.debug("下线处理-聊天系统储存用户聊天对象, " + user + " " + userChatObject);
    }

    /**
     * 初始化创建用户聊天信息
     * @param userId
     * @return
     */
    private UserChatObject initMessageUserObject (String userId)
    {
        UserChatObject userChatObject = new UserChatObject();
        userChatObject.setUserId(userId);
        userChatObject.setChatUsers(new ArrayList<>());

        // 初始化Root消息账户
        MessageUser RootMessageUser = userService.findRootMessageUser();
        if (RootMessageUser != null)
            addChatUser(userChatObject, RootMessageUser);

        userChatMongoDao.insert(userChatObject);

        return userChatObject;
    }

    /**
     * 系统对所有用户发送消息
     * @param messageUserDtoList  接受者列表
     * @param messageContentDto   消息内容
     */
    public void systemSendMessageToList (List<MessageUserDto> messageUserDtoList, MessageContentDto messageContentDto)
    {
        MessageUser sender = userService.findRootMessageUser();
        MessageContent messageContent = ChatDtoFactory.createMessageContent(messageContentDto);

        // 发送者和消息不能为空
        if (sender == null || messageContent == null)
        {
            LogUtil.error("ChatService.systemSendMessageToUser 数据异常: " +
                    "messageContent, " + messageContentDto + " sender, " + sender);
            throw new MessageServerDubboException("ChatService.systemSendMessageToUser 数据异常: " +
                    "messageContent, " + messageContentDto + " sender, " + sender);
        }

        LogUtil.info("发送人数 " + messageUserDtoList.size());
        for (MessageUserDto messageUserDto : messageUserDtoList)
        {
            SystemMessageThreadPool.execute(() ->
                    _sendSystemMessage(messageUserDto, sender, messageContent)
            );
        }

    }

    /**
     * 发送系统消息(仅供群发调用)
     * @param messageUserDto
     * @param sender
     * @param messageContent
     */
    private void _sendSystemMessage (MessageUserDto messageUserDto, MessageUser sender, MessageContent messageContent)
    {
        try {
            MessageUser receiver = MessageUserDtoFactory.createMessageUser(messageUserDto);

            //如果接受者为空，则跳过
            if (receiver == null) {
                LogUtil.error("systemSendMessageToList error, no receiver, receiver " + receiver);
                return;
            }

            // 存在的用户但没有初始化过消息系统数据则进行初始化
            UserChatObject receiverChatObject = findUserChatObject(receiver.getUserId());
            if (receiverChatObject == null)
                receiverChatObject = initMessageUserObject(receiver.getUserId());

            addChatMessageAndSend(receiverChatObject, sender, receiver, messageContent, false);
        }
        catch (Exception e)
        {
            LogUtil.error("systemSendMessageToList thread pool execute error! " + messageUserDto, e);
        }
    }

    /**
     * 系统对指定用户发送消息
     * @param receiverId            接收者ID
     * @param messageContentDto     消息内容
     */
    public void systemSendMessageToUser (String receiverId, MessageContentDto messageContentDto)
    {
        MessageUser sender                  = userService.findRootMessageUser();
        MessageUser receiver                = userService.findMessageUser(receiverId);
        MessageContent messageContent       = ChatDtoFactory.createMessageContent(messageContentDto);

        // 发送者 接受者和消息不能为空
        if (sender == null || receiver == null || messageContent == null)
            throw new MessageServerDubboException("ChatService.systemSendMessageToUser 数据异常, receiverId:"
                    + receiverId + ", " + messageContentDto + " sender, receiver,messageContent "
                    + (sender != null) + (receiver != null) + (messageContent != null));

        // 存在的用户但没有初始化过消息系统数据则进行初始化
        UserChatObject receiverChatObject = findUserChatObject(receiverId);
        if (receiverChatObject == null)
            receiverChatObject = initMessageUserObject(receiverId);

        addChatMessageAndSend(receiverChatObject, sender, receiver, messageContent, false);

    }

    /**
     * 发送消息
     * @param senderId          发送者ID
     * @param receiverId        接收者ID
     * @param messageContentDto 消息内容Dto
     * @return
     */
    public MessageErrorCode sendMessageToUser (String senderId, String receiverId, MessageContentDto messageContentDto)
    {
        MessageUser sender                  = userService.findMessageUser(senderId);
        MessageUser receiver                = userService.findMessageUser(receiverId);
        MessageContent messageContent       = ChatDtoFactory.createMessageContent(messageContentDto);

        if (sender == null || receiver == null)
            return MessageErrorCode.LOGIN_NO_USER;

        // 如果不是文本消息 ---- 即业务消息，不对发送方进行初始化
        if (MessageContentType.getEnum(messageContent.getType()) != MessageContentType.TEXT)
        {
            // 存在的用户但没有初始化过消息系统数据则进行初始化
            UserChatObject senderChatObject = findUserChatObject(senderId);
            if (senderChatObject == null)
                senderChatObject = initMessageUserObject(senderId);
            addChatMessageAndSend(senderChatObject, sender, receiver, messageContent, true);
        }

        // 存在的用户但没有初始化过消息系统数据则进行初始化
        UserChatObject receiverChatObject = findUserChatObject(receiverId);
        if (receiverChatObject == null)
            receiverChatObject = initMessageUserObject(receiverId);
        addChatMessageAndSend(receiverChatObject, sender, receiver, messageContent, false);

        return MessageErrorCode.SUCCESS;
    }

    /**
     * 用户增加一条消息处理
     * @param userChatObject
     * @param sender
     * @param receiver
     * @param messageContent
     * @param isRead
     */
    private void addChatMessageAndSend (UserChatObject userChatObject,
                                        MessageUser sender,
                                        MessageUser receiver,
                                        MessageContent messageContent,
                                        boolean isRead)
    {

        Boolean isInChatList = true;

        String ownerId = userChatObject.getUserId();
        MessageUser otherUser = (ownerId.equals(sender.getUserId())) ? receiver : sender;

        // 创建并保存消息到DB
        ChatMessage chatMessage = createChatMessage(ownerId, otherUser.getUserId(), sender, receiver, messageContent, isRead);
        chatMessageMongoDao.insert(chatMessage);

        // 聊天对象不在自己的聊天用户列表中则添加
        ChatUser chatUser = getChatUser(userChatObject, otherUser.getUserId());
        if (chatUser == null)
        {
            chatUser = addChatUser(userChatObject, otherUser);
            isInChatList = false;
            LogUtil.debug("聊天系统-消息列表增加消息用户. " + userChatObject + " " + chatUser);
        }

        // 更新我对应的聊天用户的最新消息, 时间, 未读条目数
        chatUser.setLastMessageContent(messageContent);
        chatUser.setLastMessageDate(new Date());
        if (!isRead)
            chatUser.setUnreadCount(chatUser.getUnreadCount() + 1);

        // 不在线则立即更新userChatObject到DB
        boolean isOnline = userService.isOnline(ownerId);
        if (!isOnline)
        {
            userChatMongoDao.updateFirst(userChatObject.getId(), userChatObject);
            LogUtil.info("聊天系统-不在线储存userChatObject. ownerId:" + ownerId + " " + userChatObject);
        }

        // 在线在立即发送消息
        if (isOnline){
            ChatMessageDto chatMessageDto = ChatDtoFactory.createChatMessageDto(chatMessage);
            chatMessageDto.setInChatList(isInChatList);
            SendFacotry.sendUser(ownerId, chatMessageDto);
            LogUtil.info("聊天系统-在线发送");

        }
    }


    /**
     * 获得聊天用户实体
     * @param userChatObject
     * @param userId
     * @return
     */
    private ChatUser getChatUser (UserChatObject userChatObject, String userId)
    {
        List<ChatUser> chatUserList =  userChatObject.getChatUsers();
        for (ChatUser chatUser : chatUserList)
        {
            if (chatUser.getUser() != null && userId != null && userId.equals(chatUser.getUser().getUserId()))
                return chatUser;
        }

        return null;
    }

    /**
     * 给聊天用户列表添加指定用户
     * @param userChatObject
     * @param messageUser
     * @return
     */
    private ChatUser addChatUser (UserChatObject userChatObject, MessageUser messageUser)
    {
        ChatUser chatUser = new ChatUser();
        chatUser.setUser(messageUser);
        chatUser.setUnreadCount(0);
        chatUser.setLastMessageDate(new Date());
        chatUser.setLastMessageContent(null);

        userChatObject.getChatUsers().add(chatUser);
        return chatUser;
    }

    /**
     * 创建一条消息
     * @param ownerId
     * @param sender
     * @param receiver
     * @param messageContent
     * @param isRead
     * @return
     */
    private ChatMessage createChatMessage (String ownerId,
                                       String chatUserId,
                                       MessageUser sender,
                                       MessageUser receiver,
                                       MessageContent messageContent,
                                       boolean isRead)
    {
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setOwnerUserId(ownerId);
        chatMessage.setChatUserId(chatUserId);
        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);
        chatMessage.setContent(messageContent);
        chatMessage.setSendDate(new Date());
        chatMessage.setRead(isRead);
        if (isRead)
            chatMessage.setReadDate(new Date());

        return chatMessage;
    }

    /**
     * 查找用户聊天对象
     * @param userId
     * @return
     */
    public UserChatObject findUserChatObject(String userId)
    {
        if (userId == null)
            return null;

        MessageUserObject userObject = userService.getUserObject(userId);
        if (userObject != null)
            return userObject.getUserChatObject();

        return userChatMongoDao.findOne(UserChatObject.FIELD_USER_ID, userId);
    }

    /**
     * 显示消息列表
     * @param ownerId       拥有者ID
     * @param chatUserId    聊天对象ID
     * @param messageId     起始消息ID
     * @param count         显示数量
     * @return
     */
    public List<ChatMessage> showMessageList (String ownerId, String chatUserId, String messageId, int count)
    {
        List<ChatMessage> chatMessageList = chatMessageMongoDao.findListByOwnerId(ownerId, chatUserId, messageId, count);

        // 更新所有消息为已读状态
        chatMessageMongoDao.updateReadStatus(ownerId, chatUserId);

        UserChatObject userChatObject = findUserChatObject(ownerId);
        boolean isOnline = userService.isOnline(ownerId);
        if (isOnline && userChatObject != null)
        {
            ChatUser chatUser = getChatUser(userChatObject, chatUserId);
            chatUser.setUnreadCount(0);
        }
        return chatMessageList;
    }

    /**
     * 得到该用户所有未读消息总数
     * @param userId
     * @return
     */
    public SimpleMessageDto getSimpleMessageDto(String userId){
        UserChatObject userChatObject = findUserChatObject(userId);
        if (userChatObject == null)
            return null;

        SimpleMessageDto simpleMessageDto = new SimpleMessageDto();
        simpleMessageDto.setUnReadMessageTotal(0);

        List<ChatUserDto> chatUserList = ChatDtoFactory.createChatUserDtoList(userChatObject);
        for (ChatUserDto chatUserDto : chatUserList){
            if (chatUserDto.getUser().getUserId().equals(userId)){
                continue;
            }
            int total = simpleMessageDto.getUnReadMessageTotal()+chatUserDto.getUnreadCount();
            simpleMessageDto.setUnReadMessageTotal(total);
        }

        return simpleMessageDto;

    }

}
