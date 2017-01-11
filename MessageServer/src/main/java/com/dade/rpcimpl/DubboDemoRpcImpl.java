package com.dade.rpcimpl;


import com.dade.common.LogUtil;
import com.dade.rpcapi.api.DubboDemoRpc;
import com.dade.rpcapi.dto.DubboDemo1Dto;
import com.dade.rpcapi.dto.DubboDemo2Dto;
import com.dade.rpcapi.enums.DubboDemoEnum;
import com.dade.rpcapi.exception.DubboDemoException;
import org.springframework.stereotype.Service;

/**
 * dubbo demo 远程调用的实现类
 * Created by dade on 2016/1/10
 */
@Service
public class DubboDemoRpcImpl implements DubboDemoRpc
{
    @Override
    public String testSimplePrintln(String text)
    {
        String info = "duubo demo testSimplePrintln: " + text;
        LogUtil.info(info);
        return info;
    }

    @Override
    public DubboDemo2Dto testObjectTrans(DubboDemo1Dto dto)
    {
        DubboDemo2Dto resultDto = new DubboDemo2Dto();
        resultDto.setResult("It's OK");
        resultDto.setDto(dto);
        return resultDto;
    }

    @Override
    public String testEnumAndConstant(DubboDemoEnum enumType)
    {
        if (enumType == null)
            return "enum is null";

        return "enum is " + enumType.name();
    }

    @Override
    public void testException(String info)
    {
        throw new DubboDemoException("this is a exception demo, info: " + info);
    }
}
