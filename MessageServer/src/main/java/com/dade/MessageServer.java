package com.dade;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.dade.common.LogUtil;
import com.dade.config.CommonConfig;
import com.dade.framework.AbstractMapping;
import com.dade.framework.MappingResponse;
import com.dade.framework.MessageRouterFactory;
import com.dade.framework.client.MessageClient;
import com.dade.framework.client.MessageClientPool;
import com.dade.framework.event.FileSocketioEvent;
import com.dade.framework.event.JsonSocketioEvent;
import com.dade.framework.exception.MappingResponseNullException;
import com.dade.module.MessageErrorCode;
import com.dade.module.RouterMappingEnum;
import com.dade.module.RouterSendEnum;
import com.dade.module.user.MessageUserObject;
import com.dade.module.user.MessageUserPool;
import com.dade.module.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息服务器
 * Created by dade on 2016/1/10
 */
@Component
public class MessageServer
{

    private SocketIOServer server;

    @Autowired
    UserService userService;

    @Autowired
    private CommonConfig commonConfig;

    /**
     * 启动socket io server
     */
    public void start ()
    {
        RouterMappingEnum.init();
        RouterSendEnum.init();

        Configuration config = new Configuration();
        config.setPort(commonConfig.getSocketPort());
        config.setMaxFramePayloadLength(5 * 1024 * 1024);
        config.setMaxHttpContentLength(5 * 1024 * 1024);

        final SocketIOServer server = new SocketIOServer(config);

        // 注册, 注销链接, json事件, 文件事件
        server.addConnectListener   (this::connectEvent);
        server.addDisconnectListener(this::disconnectEvent);
        server.addEventListener("json", JsonSocketioEvent.class, this::jsonEvent);
        server.addEventListener("file", FileSocketioEvent.class, this::fileEvent);

        server.start();
        this.server = server;

        LogUtil.info("消息服务器启动完毕!!! port: " + commonConfig.getSocketPort());
    }

    /**
     * 新连接事件处理
     * @param socketClient
     */
    private void connectEvent (SocketIOClient socketClient)
    {
        LogUtil.info("新的连接: " + socketClient);
        MessageClientPool.getInstance().registerClient(socketClient);
    }

    /**
     * 连接摧毁事件处理
     * @param socketClient
     */
    private void disconnectEvent (SocketIOClient socketClient)
    {
        LogUtil.info("连接关闭-start, client: " + socketClient);

        MessageClient client = MessageClientPool.getInstance().getBySocketClient(socketClient);
        if (client == null)
            return;

        MessageClientPool.getInstance().unregisterClient(socketClient);
        client.setObject(null);

        if (client.getUserId() == null || client.getUserId().isEmpty())
        {
            LogUtil.warn("连接关闭-end, 没有用户. : " + client + socketClient);
            return;
        }

        // 没有用户ID索引则认为用户已下线
        boolean hasUserIndex = MessageClientPool.getInstance().checkUserId(client.getUserId());
        if (!hasUserIndex)
        {
            MessageUserObject userObject = MessageUserPool.get(client.getUserId());
            if (userObject != null)
                userService.onLogout(userObject);
        }

        LogUtil.info("连接关闭-end, hasUserIndex: " + hasUserIndex + socketClient);
    }

    /**
     * json事件
     * @param socketClient
     * @param event
     * @param ackSender
     */
    private void jsonEvent (SocketIOClient socketClient, JsonSocketioEvent event, AckRequest ackSender)
    {
        LogUtil.debug("新事件-json事件, " + event);

        MessageClient client = MessageClientPool.getInstance().getBySocketClient(socketClient);
        if (client == null || event == null)
        {
            socketClient.disconnect();
            LogUtil.warn("json事件参数异常, 强制关闭连接, "+ event + client);
            return;
        }

        String mappingType = "";
        try
        {
            AbstractMapping mapping = MessageRouterFactory.getInstance().createJsonMapping(client, event);
            mappingType = mapping.getMappingType();
            MappingResponse response = mapping.execute();
            if (response == null)
                throw new MappingResponseNullException("mapping type: " + mappingType
                        + ", class: " + mapping.getClass().getSimpleName());

            if (!response.checkSuccessful())
                LogUtil.debug("json事件执行失败返回, mappingType: " + mappingType + ", " + event  + ", " + response);

            ackSender.sendAckData(response);
        }
        catch (Exception e)
        {
            LogUtil.error("json事件执行异常 error. mappingType: "+ mappingType +", "+ event, e);
            ackSender.sendAckData( new MappingResponse(MessageErrorCode.OTHER_ERROR.code(),
                    e.getMessage(), mappingType, null, null));
        }
    }

    /**
     * 文件事件
     * @param socketClient
     * @param event
     * @param ackSender
     */
    private void fileEvent (SocketIOClient socketClient, FileSocketioEvent event, AckRequest ackSender)
    {
        LogUtil.debug("新事件-file事件, " + event);

        MessageClient client = MessageClientPool.getInstance().getBySocketClient(socketClient);
        if (client == null || event == null)
        {
            socketClient.disconnect();
            LogUtil.warn("file事件参数异常, 强制关闭连接, "+ event + client);
            return;
        }

        String mappingType = "";
        try
        {
            AbstractMapping mapping = MessageRouterFactory.getInstance().createFileMapping(client, event);
            mappingType = mapping.getMappingType();

            MappingResponse response = mapping.execute();
            if (response == null)
                throw new MappingResponseNullException("mapping type: " + mappingType
                        + ", class: " + mapping.getClass().getSimpleName());

            if (!response.checkSuccessful())
                LogUtil.debug("json事件执行失败返回, mappingType: " + mappingType + ", " + event  + ", " + response);

            ackSender.sendAckData(response);
        }
        catch (Exception e)
        {
            LogUtil.error("file事件执行异常 error. mappingType: "+ mappingType +", "+ event, e);
            ackSender.sendAckData( new MappingResponse(MessageErrorCode.OTHER_ERROR.code(),
                    e.getMessage(), mappingType, null, null));
        }
    }
}
