package com.dade.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Dade on 2017/1/10.
 */
@Configuration
@ImportResource({"classpath:dubbo-consumer.xml"}) 			//加入spring的bean的xml文件
public class DubboConfig {
}
