//package com.dade.security;
//
//import com.dade.user.hunter.HunterUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// * Created by Dade on 2017/1/7.
// */
//@Configuration
//@EnableWebSecurity
//@ComponentScan("com.dade")
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Autowired
//    HunterUserRepository repository;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
////        auth.inMemoryAuthentication()
////                .withUser("user").password("password").roles("USER");
//        auth.userDetailsService(new HunterUserSecurityServices(repository));
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
////        http.authorizeRequests().anyRequest().authenticated().and().formLogin();
//        http
////                .formLogin()
////                .and()
////                .httpBasic()
////                .and()
//                .authorizeRequests()
//                .antMatchers("/api/private/**").authenticated()
//                .anyRequest().permitAll();
//        http.csrf().disable();
//    }
//
//
//
//
//}
