package com.dade.rpcapi.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * dubbo demo 网络传输对象
 * Created by Dade on 2017/1/10.
 */
public class DubboDemo1Dto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;
    private String info;
    private List<String> list;
    private Map<String, String> map;

    public DubboDemo1Dto(int id, String info, List<String> list, Map<String, String> map) {
        this.id = id;
        this.info = info;
        this.list = list;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "DubboDemo1Dto{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
