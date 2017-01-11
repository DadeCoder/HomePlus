package com.dade.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by dade on 2016/1/10
 */
@Component
public class CommonConfig {

    /**
     * 接收时间间隔（单位：分钟）
     */
    @Value( "${socket.port}" )
    private Integer socketPort;

    public Integer getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(Integer socketPort) {
        this.socketPort = socketPort;
    }
}
