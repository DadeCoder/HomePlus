package com.dade.framework.client;

import com.corundumstudio.socketio.SocketIOClient;
import com.dade.common.LogUtil;
import com.dade.framework.event.SendSocketioEvent;

import java.util.UUID;

/**
 * 消息连接
 * Created by dade on 2016/1/10
 */
public final class MessageClient
{
    private SocketIOClient _client;     // 原始socket io连接
    private String address;             // 地址
    private MessageClientStatus status; // 连接状态

    private String userId;              // 用户ID
    private Object object;              // 数据对象

    public MessageClient(SocketIOClient _client)
    {
        this._client = _client;
        this.address = _client.getRemoteAddress().toString();
        this.status = MessageClientStatus.WAIT_AUTH;
    }

    /**
     * 发包
     * @param eventType
     * @param event
     */
    public void sendPacket(String eventType, SendSocketioEvent event)
    {
        if (isClosed())
        {
            LogUtil.warn("sendPacket error, client is closed " + toString() + " " + event);
            return;
        }

        _client.sendEvent(eventType, event);
    }

    /**
     * 连接关闭
     */
    public void close ()
    {
        status = MessageClientStatus.CLOSE;
        _client.disconnect();
    }

    /**
     * 检测连接是否关闭
     */
    public boolean isClosed ()
    {
        return status == MessageClientStatus.CLOSE;
    }

    public UUID getUUID ()
    {
        return _client.getSessionId();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MessageClientStatus getStatus() {
        return status;
    }

    public void setStatus(MessageClientStatus status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "MessageClient{" +
                "address='" + address + '\'' +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                '}';
    }
}
