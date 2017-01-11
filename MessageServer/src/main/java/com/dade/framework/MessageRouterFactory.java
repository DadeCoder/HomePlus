package com.dade.framework;



import com.dade.framework.client.MessageClient;
import com.dade.framework.event.FileSocketioEvent;
import com.dade.framework.event.JsonSocketioEvent;
import com.dade.framework.exception.MappingClassNotFindException;
import com.dade.framework.exception.MappingObjectCreateException;
import com.dade.framework.exception.SocketioEventConvertDtoException;
import com.dade.network.IDto;
import com.dade.utils.JsonUtil;
import com.dade.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息路由工厂
 * Created by dade on 2016/1/10
 */
public class MessageRouterFactory
{

    private Map<String, Class<? extends AbstractMapping>> receiveMappingMap = new ConcurrentHashMap<>();
    private Map<Class<? extends IDto>, List<String>> sendMappingMap = new ConcurrentHashMap<>();

    private static MessageRouterFactory instance = new MessageRouterFactory();

    private MessageRouterFactory() {}

    public static MessageRouterFactory getInstance ()
    {
        return instance;
    }

    /**
     * 创建json映射
     * @param client
     * @param event
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends IDto> AbstractMapping<T> createJsonMapping (MessageClient client, JsonSocketioEvent event)
            throws Exception
    {
        String mappingType = event.getMapping();
        AbstractMapping<T> mapping = createMappingInstance(mappingType);

        T dto = JsonUtil.parseJsonToObject(event.getJsonData(), mapping.getDtoClass());
        if (dto == null)
            throw new SocketioEventConvertDtoException("JsonMapping to jsonDto error: " +
                    client.toString() + " ===== " + event.toString());

        mapping.init(client, mappingType, dto);
        return mapping;
    }

    /**
     * 创建文件映射
     * @param client
     * @param event
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends IDto> AbstractMapping<T> createFileMapping (MessageClient client, FileSocketioEvent event)
            throws Exception
    {
        String mappingType = event.getMapping();
        AbstractMapping<T> mapping = createMappingInstance(mappingType);

        T dto = mapping.getDtoClass().newInstance();
        //dto.setBinaryData(event.getBinaryData());

        if (dto == null)
            throw new SocketioEventConvertDtoException("FileMapping to fileDto error: " +
                    client.toString() + " ===== " + event.toString());

        mapping.init(client, mappingType, dto);

        return mapping;
    }

    /**
     * 注册接收映射
     * @param mappingType
     * @param mappingClass
     * @return
     */
    public synchronized boolean registerMapping (String mappingType, Class<? extends AbstractMapping> mappingClass)
    {
        return receiveMappingMap.putIfAbsent(mappingType, mappingClass) == null;
    }

    /**
     * 注册发送包映射
     * @param sendMapping
     * @param dtoClass
     * @return
     */
    public synchronized boolean registerSendable (String sendMapping, Class<? extends IDto> dtoClass)
    {
        if (StringUtil.isEmpty(sendMapping) || dtoClass == null)
            return false;

        List<String> sendMappingList = sendMappingMap.get(dtoClass);
        if (sendMappingList == null)
            sendMappingMap.put(dtoClass, (sendMappingList = new ArrayList<>()));

        if (sendMappingList.contains(sendMapping))
            return false;

        sendMappingList.add(sendMapping);
        return true;
    }

    /**
     * 获发送映射集合
     * @param dtoClass
     * @return
     */
    public List<String> getSendMappingList(Class<? extends IDto> dtoClass)
    {
        return sendMappingMap.get(dtoClass);
    }

    /**
     * 创建映射实例
     * @param mappingType
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T extends IDto> AbstractMapping<T> createMappingInstance (String mappingType)
            throws Exception
    {
        // 检测映射是否存在
        Class<? extends AbstractMapping> clz = receiveMappingMap.get(mappingType);
        if (clz == null)
            throw new MappingClassNotFindException("createMappingInstance no type mapping, mappingType["+ mappingType +"]");

        try
        {
            AbstractMapping<T> mapping = (AbstractMapping<T>)clz.newInstance();
            return mapping;
        }
        catch(Exception e)
        {
            throw new MappingObjectCreateException("createMappingInstance type mapping class new instance error, " +
                    "mappingType["+ mappingType +"] class["+ clz.getSimpleName() +"]", e);
        }
    }
}
