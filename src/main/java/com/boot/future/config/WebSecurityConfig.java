//package com.boot.future.config;
//
//import com.boot.future.service.DetailsService;
//import com.boot.future.service.ILoginUserService;
//import com.boot.future.util.CookiesUtils;
//import com.boot.future.util.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//
//@EnableWebSecurity
////@EnableWebMvcSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    HttpServletRequest request;
//
//    /**
//     * 配置如何通过拦截器保护请求
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // 关闭csrf保护功能（跨域访问）
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/cmsloginuser/login", "/cms/login.html", "/cms/bootstrap/**", "/images/**", "/cms/login").permitAll()
//                // 任何尚未匹配的URL只需要验证用户即可访问
//                .anyRequest().authenticated()
//                // 指定登录页面,授予所有用户访问登录页面
//                .antMatchers("/cms/**").access("hasRole('USER')")
//                .and().formLogin().loginPage("/cms/login")
//                //设置默认登录成功跳转页面,错误回到error界面
//                .defaultSuccessUrl("/cms/index").failureUrl("/cms/error").permitAll()
//        ;
//
//    }
//
//    /**
//     * 配置user-detail服务
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      //  auth.userDetailsService(new DetailsService());
//        auth.inMemoryAuthentication().withUser("").password("").roles("");
//    }
//
//    /**
//     * 配置Spring Security的Filter链
//     *
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//
//    }
//
//
//}
//
