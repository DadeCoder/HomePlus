package com.dade.framework;


import com.dade.network.IDto;

/**
 * 映射请求的响应返回对象
 * Created by dade on 2016/1/10
 */
public final class MappingResponse
{
    private int errorCode;      // 错误码
    private String message;     // 信息
    private String mapping;     // 映射类型
    private IDto request;       // 请求对象
    private IDto response;      // 返回对象

    public MappingResponse() {}

    public MappingResponse(int errorCode, String message, String mapping, IDto request, IDto response) {
        this.errorCode = errorCode;
        this.message = message;
        this.mapping = mapping;
        this.request = request;
        this.response = response;
    }

    /**
     * 检测执行是否成功
     * @return
     */
    public boolean checkSuccessful ()
    {
        return errorCode == 0;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public IDto getRequest() {
        return request;
    }

    public void setRequest(IDto request) {
        this.request = request;
    }

    public IDto getResponse() {
        return response;
    }

    public void setResponse(IDto response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "MappingResponse{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", mapping='" + mapping + '\'' +
                ", dto=" + request +
                ", response=" + response +
                '}';
    }
}
