package com.dade.module;


import com.dade.framework.AbstractMapping;
import com.dade.framework.MappingResponse;
import com.dade.network.IDto;

/**
 * 消息服映射父类
 * Created by dade on 2016/1/10
 */
public abstract class MessageBasicMapping<T extends IDto> extends AbstractMapping<T>
{

    /**
     * 请求失败返回
     * @param errorCode
     * @return
     */
    protected final MappingResponse error(MessageErrorCode errorCode)
    {
        return new MappingResponse(errorCode.code(), errorCode.info(),
                getMappingType(), getDto(), null);
    }

    /**
     * 请求成功返回
     * @return
     */
    protected final MappingResponse ok ()
    {
        return ok(null);
    }

    /**
     * 请求成功返回
     * @param response  附带返回Dto对象
     * @return          映射响应对象
     */
    protected final MappingResponse ok (IDto response)
    {
        return new MappingResponse(0, "successful", getMappingType(), getDto(), response);
    }

    /**
     * 结果返回
     * @param wrapper   包装器, 包括错误码与Dto
     * @return
     */
    protected final MappingResponse resultWrapper(DtoResultWrapper wrapper)
    {
        if (wrapper.getErrorCode() == MessageErrorCode.SUCCESS)
            return ok (wrapper.getResult());

        if (wrapper.getErrorCode() == null)
            return error(MessageErrorCode.OTHER_ERROR);

        return error(wrapper.getErrorCode());
    }

    /**
     * 结果返回
     * @param errorCode
     * @return
     */
    protected final MappingResponse result(MessageErrorCode errorCode)
    {
        if (errorCode == MessageErrorCode.SUCCESS)
            return ok ();

        if (errorCode == null)
            return error(MessageErrorCode.OTHER_ERROR);

        return error(errorCode);
    }
}
