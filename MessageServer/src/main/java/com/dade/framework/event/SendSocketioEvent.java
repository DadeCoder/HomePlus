package com.dade.framework.event;


import com.dade.network.IDto;

/**
 * 发包socket io 事件
 * Created by dade on 2016/1/10
 */
public class SendSocketioEvent extends SocketioEvent
{
    private IDto dto;           // 数据包

    public IDto getDto() {
        return dto;
    }

    public void setDto(IDto dto) {
        this.dto = dto;
    }

    @Override
    public String toString() {
        return "SendSocketioEvent{" +
                "mapping='" + getMapping() + '\'' +
                ", jsonData='" + dto.toString() + '\'' +
                '}';
    }
}
