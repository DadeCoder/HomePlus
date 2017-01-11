package com.dade.network;

import java.util.List;

/**
 * 默认字符串dto
 * Created by dade on 2016/1/10
 */
public class DefaultStringDto implements IDto
{
    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
