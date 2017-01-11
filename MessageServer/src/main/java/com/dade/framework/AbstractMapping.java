package com.dade.framework;


import com.dade.framework.client.MessageClient;
import com.dade.network.IDto;

/**
 * 所有映射抽象父类
 * Created by dade on 2016/1/10
 */
public abstract class AbstractMapping<T extends IDto>
{
    private MessageClient client;   // 连接
    private String mappingType;     // 映射类型
    private T dto;                  // dto数据

    /**
     * 初始化映射对象方法
     * @param client
     * @param dto
     */
    public final void init (MessageClient client, String mappingType, T dto)
    {
        this.client = client;
        this.mappingType = mappingType;
        this.dto = dto;

        initImpl();
    }

    /**
     * 映射初始化后的处理, 供有需要的子类实现
     */
    protected void initImpl ()
    {

    }

    /**
     * 必须实现的dto class
     * @return
     */
    public abstract Class<T> getDtoClass ();

    /**
     * 映射执行方法
     * @return  返回对象
     */
    public abstract MappingResponse execute ();


    public final MessageClient getClient() {
        return client;
    }

    public final T getDto() {
        return dto;
    }

    public final String getMappingType ()
    {
        return mappingType;
    }
}
