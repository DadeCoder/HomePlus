package com.dade.framework.client;

import com.corundumstudio.socketio.SocketIOClient;
import com.dade.common.LogUtil;
import com.dade.utils.StringUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 消息连接池
 * Created by dade on 2016/1/10
 */
public final class MessageClientPool
{
    // 连接池
    private Map<UUID, MessageClient> clientPool = new ConcurrentHashMap<>();

    // 用户ID索引
    private Map<String, HashSet<UUID>> userIdIndexs = new ConcurrentHashMap<>();

    // 同步锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    // 单例
    private static MessageClientPool instance = new MessageClientPool();

    private MessageClientPool () {}

    public static MessageClientPool getInstance ()
    {
        return instance;
    }

    /**
     * 获得所有socket连接
     * @return  连接集合
     */
    public Collection<MessageClient> getAllSocketClient ()
    {
        readLock.lock();
        try
        {
            return clientPool.values();
        }
        finally
        {
            readLock.unlock();
        }
    }

    /**
     * 获得所有在线用户连接
     * @return  用户连接集合
     */
    public Collection<MessageClient> getAllUserClient ()
    {
        readLock.lock();
        try
        {
            HashSet<UUID> uuidCollection = new HashSet<>();
            userIdIndexs.forEach((userId, uuidList) -> uuidCollection.addAll(uuidList));

            Collection<MessageClient> clientCollection = new ArrayList<>();

            clientPool.forEach((uuid, client) -> {
                if (uuidCollection.contains(uuid))
                    clientCollection.add(client);
            });

            return clientCollection;
        }
        finally
        {
            readLock.unlock();
        }
    }

    /**
     * 检测是否存在userID索引
     * @param userId    用户ID
     * @return          true,false
     */
    public boolean checkUserId (String userId)
    {
        Collection<MessageClient> list = getByUserId(userId);
        return !(list == null || list.isEmpty());

    }

    /**
     * 通过userID获得连接方法
     * @param userId    原生连接
     * @return          连接
     */
    public Collection<MessageClient> getByUserId(String userId)
    {
        if (userId == null)
            return null;

        readLock.lock();
        try
        {
            HashSet<UUID> uuidList = userIdIndexs.get(userId);
            if (uuidList != null)
            {
                Collection<MessageClient> results = new HashSet<>();
                uuidList.forEach(uuid -> {
                    MessageClient client = clientPool.get(uuid);
                    if (client != null)
                        results.add(client);
                });

                return results.isEmpty() ? null : results;
            }

            return null;
        }
        finally
        {
            readLock.unlock();
        }
    }

    /**
     * 通过原生连接获得连接方法
     * @param socketClient  原生连接
     * @return              连接
     */
    public MessageClient getBySocketClient (SocketIOClient socketClient)
    {
        return getClientBySocketClient(socketClient);
    }

    /**
     * 注册一个新的连接
     * @param socketClient  原生连接
     * @return              消息连接
     */
    public MessageClient registerClient (SocketIOClient socketClient)
    {
        MessageClient client = new MessageClient(socketClient);
        UUID uuid = client.getUUID();

        writeLock.lock();
        try
        {
            if (clientPool.putIfAbsent(uuid, client) != null)
            {
                LogUtil.error("register MessageClient error， exist uuid["+ uuid + "]");
                return null;
            }

            setClientBySocketClient(socketClient, client);
            return client;
        }
        finally
        {
            writeLock.unlock();
        }

    }

    /**
     * 注销连接
     * @param socketClient  原生连接
     */
    public void unregisterClient (SocketIOClient socketClient)
    {
        writeLock.lock();
        try
        {
            MessageClient client = getClientBySocketClient(socketClient);
            if (client == null)
                return;

            // 清空连接引用
            clearClientBySocketClient(socketClient);

            UUID uuid = client.getUUID();
            String userId = client.getUserId();
            if (!StringUtil.isEmpty(userId))
            {
                HashSet<UUID> uuidList = userIdIndexs.get(userId);
                if (uuidList != null)
                {
                    uuidList.remove(uuid);

                    // 如果user对应uuid集合为空, 则删除user索引
                    if (uuidList.isEmpty())
                    {
                        userIdIndexs.remove(userId);
                    }
                }
            }

            clientPool.remove(uuid);
        }
        finally
        {
            writeLock.unlock();
        }
    }

    /**
     * 增加userID索引
     * @param client    连接
     */
    public void addUserIdIndex (MessageClient client)
    {
        writeLock.lock();
        try
        {

            String userId;
            // 连接关闭, 或不存在的userID则直接抛弃
            if (client == null ||
                    client.isClosed() ||
                    StringUtil.isEmpty((userId = client.getUserId())))
                return;

            UUID newUUID = client.getUUID();
            if (!userIdIndexs.containsKey(userId))
                userIdIndexs.put(userId, new HashSet<>());

            // 替换包括索引与池的连接
            userIdIndexs.get(userId).add(newUUID);
            clientPool.put(newUUID, client);
        }
        finally
        {
            writeLock.unlock();
        }
    }

    /**
     * 通过sockio获得连接
     * @param socketClient  原生连接
     * @return              消息连接
     */
    private MessageClient getClientBySocketClient (SocketIOClient socketClient)
    {
        return socketClient.get("client");
    }

    /**
     * 通过sockio设置连接
     * @param socketClient  原生连接
     */
    private void setClientBySocketClient (SocketIOClient socketClient, MessageClient client)
    {
        socketClient.set("client", client);
    }

    /**
     * 清空连接关系
     * @param socketClient  原生连接
     */
    private void clearClientBySocketClient (SocketIOClient socketClient)
    {
        socketClient.del("client");
    }

}
