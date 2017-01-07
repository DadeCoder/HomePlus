package com.dade.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/1/7.
 */
@RestController
@RequestMapping("/api/public/test")
public class TestPublicController {

    @RequestMapping("/security")
    String publicSecurity(){
        return "Hello Public API!";
    }

}
