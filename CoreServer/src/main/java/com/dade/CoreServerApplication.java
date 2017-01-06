package com.dade;

import com.dade.test.Audience;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Audience.class)
public class CoreServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreServerApplication.class, args);
	}
}
