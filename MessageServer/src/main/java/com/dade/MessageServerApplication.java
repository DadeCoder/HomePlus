package com.dade;

import com.dade.common.LogUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 消息中心启动类
 * Created by dade on 2016/1/10
 */
@SpringBootApplication
@ComponentScan("com.dade")
@EnableScheduling
public class MessageServerApplication implements CommandLineRunner, ApplicationContextAware
{

    private static ApplicationContext context;

    @Autowired
    MessageServer messageServer;

    @Override
    public void run(String... args) throws Exception
    {
        messageServer.start();
    }

    public static void main(String[] args) throws Exception
    {
        context = SpringApplication.run(MessageServerApplication.class, args);
    }

    @PostConstruct
    public void onCreate() {
        LogUtil.info("<context> create context");
    }

    @PreDestroy
    public void onDestroy() {
        LogUtil.info("<context> destroy context");
        context = null;
        System.gc();
        LogUtil.info("<system> system gc");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        LogUtil.info("<context> Inject context");
        context = applicationContext;
    }

    /**
     * spring应用上下文
     * @return
     */
    public static ApplicationContext getContext()
    {
        return context;
    }
}
