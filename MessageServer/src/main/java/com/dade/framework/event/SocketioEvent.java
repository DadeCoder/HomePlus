package com.dade.framework.event;

/**
 * socket io 事件基类
 * Created by dade on 2016/1/10
 */
public abstract class SocketioEvent
{
    // 业务映射类型
    private String mapping;

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }
}
