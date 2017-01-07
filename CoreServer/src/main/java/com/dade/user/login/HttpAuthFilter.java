//package com.dade.user.login;
//
//import com.dade.commons.utils.LogUtil;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by Dade on 2017/1/6.
// */
//@Component
//public class HttpAuthFilter implements Filter{
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        LogUtil.info("filter");
//        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
//        String auth = httpRequest.getHeader("userid");
//
//
//
//        if (auth != null){
//           LogUtil.info(auth);
//        }
//        else{
//            LogUtil.info("return");
//            return;
//        }
//
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
