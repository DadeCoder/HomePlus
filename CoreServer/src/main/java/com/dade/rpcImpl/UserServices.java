package com.dade.rpcImpl;

import com.dade.api.UserRpc;
import org.springframework.stereotype.Service;

/**
 * Created by Dade on 2017/1/10.
 */
@Service("userServices")
public class UserServices implements UserRpc {

    public String getUserName(){
        String name = "Dade";
        return name;
    }

}
