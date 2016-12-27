package com.dade.test;

import com.dade.commons.utils.LogUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2016/12/24.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestUserMongoDao dao;

    @RequestMapping("/spring_boot")
    String springBoot(){
        LogUtil.info("test for spring boot!");
        return "sping boot!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    TestUser user(@RequestBody TestUser user){
        LogUtil.info("newUser: " + user.toString());
        TestUser dbUser = dao.insert(user);
        LogUtil.info("dbUser: " + dbUser);
        return dbUser;
    }

    @RequestMapping(value = "/head", method = RequestMethod.POST)
    void head(HttpServletRequest request){

        LogUtil.info(request.toString());

    }

}
