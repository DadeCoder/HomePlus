//package com.dade.test;
//
//import com.dade.api.UserRpc;
//import com.dade.common.LogUtil;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * Created by Dade on 2017/1/10.
// */
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//
//    @Resource
//    UserRpc userRpc;
//
//    @RequestMapping("/run")
//    String run(){
//        return "Hello MessageServer!";
//    }
//
//    @RequestMapping("dubbo")
//    String dubbo(){
//        LogUtil.info(userRpc.getUserName());
//        return userRpc.getUserName();
//    }
//
//}
