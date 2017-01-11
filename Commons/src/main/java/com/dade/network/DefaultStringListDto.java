package com.dade.network;

import java.util.List;

/**
 * 默认string list dto
 * Created by dade on 2016/1/10
 */
public class DefaultStringListDto implements IDto
{
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
