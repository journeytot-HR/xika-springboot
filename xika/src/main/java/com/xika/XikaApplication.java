package com.xika;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.xika.mapper")
@ComponentScan(basePackages = {"com.xika","org.n3r.idworker"})
@EnableScheduling
@EnableAsync
public class XikaApplication {

	public static void main(String[] args) {
		SpringApplication.run(XikaApplication.class, args);
	}

}
