package com.dade.framework.event;

/**
 * socket io json事件
 * Created by dade on 2016/1/10
 */
public class JsonSocketioEvent extends SocketioEvent
{
    private String jsonData;    // json字符串

    @Override
    public String toString() {
        return "JsonSocketioEvent{" +
                "mapping='" + getMapping() + '\'' +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
