package com.boot.future.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtils {
    public static String getCookie(HttpServletRequest request,String name) {
        String value = "";
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                value = null;
            } else {
                for (int i = 0; i < cookies.length; i++) {
                    // 获得具体的Cookie
                    Cookie cookie = cookies[i];
                    // 获得Cookie的名称
                    if (cookie.getName().equals(name)) {
                        value = cookie.getValue();
                    } else {
                        value = null;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return value;
    }

    public static void setCookie(HttpServletResponse response, String val,String name) {
        Cookie cookie = new Cookie(name, val);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);
    }

}
