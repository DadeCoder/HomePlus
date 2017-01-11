package com.dade.module.user.mapping;


import com.dade.MessageServerApplication;
import com.dade.framework.MappingResponse;
import com.dade.framework.client.MessageClient;
import com.dade.framework.client.MessageClientStatus;
import com.dade.module.DtoResultWrapper;
import com.dade.module.MessageBasicMapping;
import com.dade.module.MessageErrorCode;
import com.dade.module.user.UserService;
import com.dade.module.user.dto.LoginDto;
import com.dade.module.user.dto.LoginedDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;

/**
 * 登录
 * Created by dade on 2016/1/10
 */
public class LoginMapping extends MessageBasicMapping<LoginDto>
{
    UserService userService = MessageServerApplication.getContext().getBean(UserService.class);

    @Override
    public Class<LoginDto> getDtoClass()
    {
        return LoginDto.class;
    }

    @Override
    public MappingResponse execute()
    {
        MessageClient client = getClient();
        LoginDto dto = getDto();
        String userId = dto.getUserId();

        // 只有连接在等待授权状态下能登录
        if (client.getStatus() != MessageClientStatus.WAIT_AUTH)
            return error(MessageErrorCode.REPETITION_LOGIN);

        UserService userService = MessageServerApplication.getContext().getBean(UserService.class);
        DtoResultWrapper<LoginedDto> wrapper = userService.login(client, userId);
        return resultWrapper(wrapper);

    }
}
