package com.dade.rpcapi.dto;

import java.io.Serializable;

/**
 * dubbo demo 网络传输对象
 * Created by Dade on 2017/1/10.
 */
public class DubboDemo2Dto implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String result;

    DubboDemo1Dto dto;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DubboDemo1Dto getDto() {
        return dto;
    }

    public void setDto(DubboDemo1Dto dto) {
        this.dto = dto;
    }

    @Override
    public String toString() {
        return "DubboDemo2Dto{" +
                "result='" + result + '\'' +
                ", dto=" + dto +
                '}';
    }
}
