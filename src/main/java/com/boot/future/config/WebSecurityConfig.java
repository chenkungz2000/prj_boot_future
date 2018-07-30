package com.boot.future.config;

import com.boot.future.service.ILoginUserService;
import com.boot.future.util.CookiesUtils;
import com.boot.future.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    HttpServletRequest request;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cms/**").access("hasRole('USER')")
                .and().formLogin().loginPage("/login")
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        Map<String, Object> map= (Map<String, Object>)new RedisUtils().get("loginusercache");
        String data=(String)map.get("value");
        System.out.println("data+++++++++++");
        System.out.println(data);
        System.out.println("data===========");
        auth.inMemoryAuthentication().withUser("root").password("root").roles("USER");
    }

}

