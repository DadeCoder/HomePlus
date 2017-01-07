package com.dade.test;

import com.dade.user.hunter.HunterUser;
import com.dade.user.hunter.HunterUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;

/**
 * Created by Dade on 2017/1/7.
 */
@RestController
@RequestMapping("/api/test/add/")
public class TestAddUserController {

    @Autowired
    HunterUserDao dao;

    @RequestMapping("/one")
    HunterUser addOneUser(){
        HunterUser hunterUser = new HunterUser();
        hunterUser.setPassword("123456");
        hunterUser.setId(null);
        hunterUser.setNickName("dade");
        hunterUser.setPhoneNumber("12345678910");
        hunterUser.setVerificationCode("test");
        hunterUser = dao.insert(hunterUser);
        return hunterUser;
    }

    @RequestMapping("/{phoneNumber}")
    HunterUser findOneUser(@PathVariable String phoneNumber){

        return dao.findByPhoneNumber(phoneNumber);

    }

}
