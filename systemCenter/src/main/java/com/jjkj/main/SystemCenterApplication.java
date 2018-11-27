package com.jjkj.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = { "com.jjkj.config" })
@ServletComponentScan   //扫描Servlet  
@EnableTransactionManagement//开启事务管理
@MapperScan("com.jjkj.api.mapper")
@ComponentScan("com.jjkj.api.controller")
@ComponentScan("com.jjkj.api.service")
//@ComponentScan("com.jjkj.schedule")
public class SystemCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemCenterApplication.class, args);
	}
}
