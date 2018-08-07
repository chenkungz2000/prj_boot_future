package com.boot.future.config;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    final static Logger logger = LoggerFactory.getLogger(WebAppConfig.class);

    /**
     * 设置字符编码威utf-8
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    /**
     * 设置页面格式
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("webapp/");
        viewResolver.setSuffix(".html");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 映射访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/webapp/");
    }

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");

    }


    /**
     * 拦截
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CmsInterceptor())    //指定拦截器类
//                .excludePathPatterns("/cms/bootstrap/**", "/images/**","/cms/login")
//                .addPathPatterns("/cms/**");        //指定该类拦截的url
//
//    }


    /**
     * 配置过滤器
     *
     * @return
     */
//    @Bean
//    public FilterRegistrationBean filterRegist() {
//        FilterRegistrationBean frBean = new FilterRegistrationBean();
//        frBean.setFilter(new UrlFilter());
//        frBean.addUrlPatterns("/cms/*");
//        return frBean;
//    }

}