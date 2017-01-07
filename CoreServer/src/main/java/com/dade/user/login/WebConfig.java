//package com.dade.user.login;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by Dade on 2017/1/6.
// */
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(HttpAuthFilter myFilter){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(myFilter);
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addUrlPatterns("/api/private/*");
////        filterRegistrationBean.addUrlPatterns("/api/public/*");
//        return filterRegistrationBean;
//    }
//
//}
