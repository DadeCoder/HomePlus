package com.dade.network;

import java.util.List;

/**
 * 默认的dto list
 * Created by dade on 2016/1/10
 */
public class DefaultDtoListDto<T extends IDto> implements IDto
{
    private List<T> list;

    public DefaultDtoListDto(List<T> list)
    {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
