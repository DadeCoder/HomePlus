package com.dade.rpcapi.dto.user;

import java.io.Serializable;

/**
 * 简单聊天信息Dto
 * Created by Dade on 2017/1/10.
 */
public class SimpleMessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    int unReadMessageTotal;

    @Override
    public String toString() {
        return "SimpleMessageDto{" +
                "unReadMessageTotal=" + unReadMessageTotal +
                '}';
    }

    public int getUnReadMessageTotal() {
        return unReadMessageTotal;
    }

    public void setUnReadMessageTotal(int unReadMessageTotal) {
        this.unReadMessageTotal = unReadMessageTotal;
    }
}
