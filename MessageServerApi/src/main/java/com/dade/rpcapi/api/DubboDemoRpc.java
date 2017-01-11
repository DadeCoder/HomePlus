package com.dade.rpcapi.api;


import com.dade.rpcapi.dto.DubboDemo1Dto;
import com.dade.rpcapi.dto.DubboDemo2Dto;
import com.dade.rpcapi.enums.DubboDemoEnum;

/**
 * dubbo demo api接口
 * Created by Dade on 2017/1/10.
 */
public interface DubboDemoRpc
{
    /**
     * 测试简单打印
     * @param text
     */
    String testSimplePrintln (String text);

    /**
     * 测试对象传输与返回
     * @param dto
     * @return
     */
    DubboDemo2Dto testObjectTrans (DubboDemo1Dto dto);

    /**
     * 测试枚举
     * @param enumType
     * @return
     */
    String testEnumAndConstant (DubboDemoEnum enumType);

    /**
     * 测试异常
     * @param info
     */
    void testException (String info);


}
