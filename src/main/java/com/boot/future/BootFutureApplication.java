package com.boot.future;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.boot.future.mapper.*")
@SpringBootApplication
@EnableCaching  
public class BootFutureApplication {
	final static Logger logger = LoggerFactory.getLogger(BootFutureApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootFutureApplication.class, args);
		logger.info("==begin==");
	}
}
