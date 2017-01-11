package com.dade.module;



import com.dade.common.LogUtil;
import com.dade.framework.MessageRouterFactory;
import com.dade.framework.client.MessageClient;
import com.dade.framework.client.MessageClientPool;
import com.dade.framework.event.SendSocketioEvent;
import com.dade.framework.exception.SendMappingTypeNoSpecifyException;
import com.dade.framework.exception.SendMappingTypeNotFindException;
import com.dade.module.user.MessageUserObject;
import com.dade.network.IDto;
import com.dade.utils.StringUtil;

import java.util.Collection;
import java.util.List;

/**
 * 发包工厂
 * Created by dade on 2016/1/10
 */
public final class SendFacotry
{

    /**
     * 对所有连接发送消息
     * @param dto
     */
    public static void sendAllSocket (IDto dto)
    {
        Collection<MessageClient> clientCollection = MessageClientPool.getInstance().getAllSocketClient();
        SendSocketioEvent event = createDefultSendSocketioEvent(dto);

        clientCollection.forEach(client -> _send(client, event));
    }

    /**
     * 对所有用户发送消息
     * @param dto
     */
    public static void sendAllUser (IDto dto)
    {
        Collection<MessageClient> clientCollection = MessageClientPool.getInstance().getAllUserClient();
        SendSocketioEvent event = createDefultSendSocketioEvent(dto);

        clientCollection.forEach(client -> _send(client, event));
    }

    /**
     * 发送给指定用户
     * @param user
     * @param dto
     */
    public static void sendUser (MessageUserObject user, IDto dto)
    {
        Collection<MessageClient> clientCollection = MessageClientPool.getInstance().getByUserId(user.getUserId());
        if (clientCollection == null || clientCollection.isEmpty())
        {
            LogUtil.warn("sendUser is client empty. " + user + " " + dto);
            return;
        }

        sends(clientCollection, dto);
    }

    /**
     * 发送给指定用户
     * @param userId
     * @param dto
     */
    public static void sendUser (String userId, IDto dto)
    {
        Collection<MessageClient> clientCollection = MessageClientPool.getInstance().getByUserId(userId);
        if (clientCollection == null || clientCollection.isEmpty())
        {
            LogUtil.warn("sendUser is client empty. userId=" + userId + " " + dto);
            return;
        }

        sends(clientCollection, dto);
    }

    /**
     * 对很多个连接推送消息
     * @param dto
     */
    private static void sends (Collection<MessageClient> clientCollection, IDto dto)
    {
        if (clientCollection == null || clientCollection.isEmpty())
        {
            LogUtil.warn("sends clients is null or empty. " + dto);
            return;
        }

        SendSocketioEvent event = createDefultSendSocketioEvent(dto);
        clientCollection.forEach(client -> _send(client, event));
    }

    /**
     * 推送消息
     * @param client
     * @param dto
     */
    public static void send(MessageClient client, IDto dto)
    {
        SendSocketioEvent event = createDefultSendSocketioEvent(dto);
        _send(client, event);
    }

    /**
     * 创建默认的发包消息
     * @param dto
     */
    private static SendSocketioEvent createDefultSendSocketioEvent(IDto dto)
    {
        if (dto == null)
            throw new SendMappingTypeNotFindException("message defult type sendPacket error. params has null");

        List<String> mappingList = MessageRouterFactory.getInstance().getSendMappingList(dto.getClass());
        if (mappingList == null || mappingList.isEmpty())
            throw new SendMappingTypeNotFindException("message defult type send error. no class vs mappingList, " +
                    "class["+dto.getClass().getSimpleName()+"]");

        if (mappingList.size() > 1)
            throw new SendMappingTypeNoSpecifyException("message defult type send error. mappingList has then one type, " +
                    "class["+dto.getClass().getSimpleName()+"] mappingList size["+ mappingList.size() +"]");

        return createSendSocketioEvent(mappingList.get(0), dto);
    }

    /**
     * 推送消息
     * @param client
     * @param dto
     */
    public static void send(MessageClient client, String sendMapping, IDto dto)
    {
        SendSocketioEvent event = createSendSocketioEvent(sendMapping, dto);
        _send(client, event);
    }

    /**
     * 创建发包消息
     * @param sendMapping
     * @param dto
     * @return
     */
    private static SendSocketioEvent createSendSocketioEvent(String sendMapping, IDto dto)
    {
        if (StringUtil.isEmpty(sendMapping) || dto == null)
            throw new SendMappingTypeNotFindException("message sendPacket error. params has null");

        List<String> mappingList = MessageRouterFactory.getInstance().getSendMappingList(dto.getClass());
        if (mappingList == null || !mappingList.contains(sendMapping))
            throw new SendMappingTypeNotFindException("message send error. no class vs mappingList, " +
                    "class["+dto.getClass().getSimpleName()+"]");

        if (!mappingList.contains(sendMapping))
            throw new SendMappingTypeNotFindException("message send error. mappingList no sendMapping. " +
                    "sendMapping["+ sendMapping +"] class["+dto.getClass().getSimpleName()+"] ");

        SendSocketioEvent event = new SendSocketioEvent();
        event.setMapping(sendMapping);
        event.setDto(dto);
        return event;
    }

    /**
     * 发包
     * @param client
     * @param event
     */
    private static void _send (MessageClient client, SendSocketioEvent event)
    {
        client.sendPacket("sendPacket", event);
    }
}
