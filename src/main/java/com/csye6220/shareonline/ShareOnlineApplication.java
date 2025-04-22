package com.csye6220.shareonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class,        // 不让 Spring Boot 创建 DataSource
				HibernateJpaAutoConfiguration.class       // 不让 Spring Boot 启动 JPA
		}
)
public class ShareOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareOnlineApplication.class, args);
	}
}
