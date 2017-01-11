package com.dade.rpcImpl;

import com.dade.api.UserRpc;
import com.dade.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Dade on 2017/1/10.
 */
@Service("userServices")
public class UserServices implements UserRpc {

    public String getUserName(){
        String name = "Dade";
        return name;
    }

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setUserId("123456");
        userDto.setCityId("123456");
        userDto.setCityName("cityName");
        userDto.setCountryId("123456");
        userDto.setCountryName("countryName");
        userDto.setCreateDate(new Date());
        userDto.setEmail("email");
        userDto.setGender(0);
        userDto.setMobile("123456");
        userDto.setModifyDate(new Date());
        userDto.setName("dade");
        userDto.setProvinceId("123456");
        userDto.setProvinceName("privinceName");
        userDto.setLastLoginDate(new Date());
        userDto.setNick("dade");
        userDto.setType(0);
        userDto.setStatus(1);
        return userDto;
    }

    public UserDto getToDto(){
        UserDto userDto = new UserDto();
        userDto.setUserId("789123");
        userDto.setCityId("123456");
        userDto.setCityName("cityName");
        userDto.setCountryId("123456");
        userDto.setCountryName("countryName");
        userDto.setCreateDate(new Date());
        userDto.setEmail("email");
        userDto.setGender(0);
        userDto.setMobile("123456");
        userDto.setModifyDate(new Date());
        userDto.setName("Dade");
        userDto.setProvinceId("123456");
        userDto.setProvinceName("privinceName");
        userDto.setLastLoginDate(new Date());
        userDto.setNick("Dade");
        userDto.setType(0);
        userDto.setStatus(1);
        return userDto;
    }

}
