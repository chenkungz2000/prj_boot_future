package com.boot.future.controller.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BaseController {
    // 无效
    // ServletRequestAttributes attr = (ServletRequestAttributes)
    // RequestContextHolder.currentRequestAttributes();
    // HttpServletRequest request=attr.getRequest();
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

}
