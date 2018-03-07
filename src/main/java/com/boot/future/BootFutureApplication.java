package com.boot.future;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;

@MapperScan("com.boot.future.mapper.*")
@SpringBootApplication
@EnableCaching
@EnableWebSecurity // 启用web安全
public class BootFutureApplication {

    final static Logger logger = LoggerFactory.getLogger(BootFutureApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BootFutureApplication.class, args);
        logger.info("==begin==");

    }

    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }
}
