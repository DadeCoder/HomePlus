package com.dade.test;

import com.dade.user.hunter.HunterUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/1/7.
 */
@RestController
@RequestMapping("/api/private/test")
public class TestPrimaryController {

    @RequestMapping("/security")
    String primarySecurity(){
        return "Hello Primary API!";
    }

}
