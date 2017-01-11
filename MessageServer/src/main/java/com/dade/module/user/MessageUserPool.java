package com.dade.module.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在线用户池
 * Created by dade on 2016/1/10
 */
public final class MessageUserPool
{
    private static final Map<String, MessageUserObject> userObjectMap = new ConcurrentHashMap<>();

    /**
     * 注册用户
     * @param user
     * @return
     */
    public static MessageUserObject register (MessageUserObject user)
    {
        return userObjectMap.put(user.getUserId(), user);
    }

    /**
     * 注销用户
     * @param user
     * @return
     */
    public static MessageUserObject unregister (MessageUserObject user)
    {
        return userObjectMap.remove(user.getUserId());
    }

    /**
     * 获得在线用户
     * @param userId
     * @return
     */
    public static MessageUserObject get (String userId)
    {
        return userObjectMap.get(userId);
    }

    /**
     * 检测账户是否存在
     * @param userId
     * @return
    */
    public static boolean contains (String userId)
    {
        return userObjectMap.containsKey(userId);
    }
}
