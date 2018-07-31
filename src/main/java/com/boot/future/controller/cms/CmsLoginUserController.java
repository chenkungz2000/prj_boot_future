package com.boot.future.controller.cms;

import com.boot.future.controller.cms.BaseController;
import com.boot.future.entity.LoginUser;
import com.boot.future.service.ILoginUserDataService;
import com.boot.future.service.ILoginUserService;

import com.boot.future.util.CookiesUtils;
import com.boot.future.util.RedisUtils;
import com.boot.future.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ck
 * @since 2017-12-01
 */
@Controller
@RequestMapping("/cmsloginuser")
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST})
public class CmsLoginUserController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(CmsLoginUserController.class);
    @Autowired
    ILoginUserService service;
    @Autowired
    ILoginUserDataService iLoginUserDataService;

    RedisUtils redisUtils=new RedisUtils();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> login(String code, String value, String password) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            LoginUser user = null;
            if (Utils.checkPhone(value)) {
                user = service.getLoginUserByPhoneAndPassword(value, password);
            } else {
                user = service.getLoginUserByNameAndPassword(value, password);
            }
            if (user != null) {
                flag = true;
                map.put("value", value);
                map.put("password",password);
                CookiesUtils.setCookie(response,user.getName(),"loginCookies");
               request.getSession(true).setAttribute("name", user.getName());
            }

        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;

    }

    /**
     * 检测登陆
     */
    @GetMapping(value = "/ch_login")
    @ResponseBody
    public Map<String, Object> ch_Login() {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        try {
            String sessionname = (String) request.getSession(true).getAttribute("name");
            if (sessionname != null && sessionname.length() >= 0) {
                LoginUser user = service.getLoginUserByandName(sessionname);
                if (user.getName() != null)
                    // request.getSession(true).removeAttribute("name");
                    flag = true;
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;
    }


}
