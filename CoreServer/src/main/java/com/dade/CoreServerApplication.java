package com.dade;

import com.dade.test.Audience;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:dubbo-provider.xml"}) 			//加入spring的bean的xml文件
@SpringBootApplication
@EnableConfigurationProperties(Audience.class)
public class CoreServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreServerApplication.class, args);
	}
}
