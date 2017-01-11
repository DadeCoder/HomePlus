package com.dade.api;

import com.dade.dto.UserDto;

/**
 * Created by Dade on 2017/1/10.
 */
public interface UserRpc {
    public String getUserName();

    public UserDto getDto();

    public UserDto getToDto();


}
