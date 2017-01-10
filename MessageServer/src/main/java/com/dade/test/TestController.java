package com.dade.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/1/10.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("/run")
    String run(){
        return "Hello MessageServer!";
    }

}
