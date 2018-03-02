package com.boot.future.controller;

import com.boot.future.entity.LoginUser;
import com.boot.future.entity.LoginUserData;
import com.boot.future.service.ILoginUserDataService;
import com.boot.future.service.ILoginUserService;

import com.boot.future.util.CacheUtils;
import com.boot.future.util.Utils;
import com.boot.future.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
@RequestMapping("/loginuser")
public class LoginUserController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(LoginUserController.class);
    @Autowired
    ILoginUserService service;
    @Autowired
    ILoginUserDataService iLoginUserDataService;

    /**
     * 响应验证码页面
     *
     * @return
     */
    @RequestMapping(value = "/validateCode/{time}", method = RequestMethod.GET)
    @ResponseBody//org.eclipse.jetty.server.HttpChannel 
    public String ValidateCode(@PathVariable("time") String time) throws Exception {
        try {
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            HttpSession session = request.getSession();
            ValidateUtils Validate = new ValidateUtils(120, 40, 4, 10);
            session.setAttribute("validateCode", Validate.getCode());
            Validate.write(response.getOutputStream());
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> login(String code, String value, String password) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            password = request.getParameter("password");
            value = request.getParameter("value");
            code = request.getParameter("code");
            HttpSession session = request.getSession();
            String sessionCode = (String) session.getAttribute("validateCode");
            if (!StringUtils.equalsIgnoreCase(code, sessionCode)) { // 忽略验证码大小写
                throw new RuntimeException("验证码对应不上code=" + code + "  sessionCode=" + sessionCode);
            }
            LoginUser user = null;
            if (Utils.checkPhone(value)) {
                user = service.getLoginUserByPhoneAndPassword(value, password);
            } else {
                user = service.getLoginUserByNameAndPassword(value, password);
            }
            if (user != null) {
                flag = true;
                map.put("user", user);
            }

        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> createUser(String name, String password, String phone) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        String msg = "";
        Date date = new Date();
        name = request.getParameter("name");
        password = request.getParameter("password");
        phone = request.getParameter("phone");
        try {
            LoginUser checkuser = service.getLoginUserByandPhone(phone);
            LoginUser checkuser2 = service.getLoginUserByandName(name);
            if (checkuser != null)
                throw new Exception("该号码已注册");
            if (checkuser2 != null)
                throw new Exception("该用户名称已注册");
            LoginUser user = new LoginUser();
            LoginUserData record = new LoginUserData();
            String ip = Utils.getIpAddr(request);
            user.setName(name);
            user.setPassword(password);
            user.setPhone(phone);
            user.setIp(ip);
            user.setCreatedate(date);
            user.setUpdatedate(date);
            record.setCreatedate(date);
            record.setLoginFrequency(1);
            record.setLoginIp(ip);
            service.insert(user);
            LoginUser user3 = service.getLoginUserByandPhone(phone);
            if (user != null) {
                int loginid = user3.getId();
                record.setLoginUserId(loginid);
                record.setUpdatedate(date);
                iLoginUserDataService.insert(record);
                flag = true;
            }
        } catch (Exception e) {
            msg = e.getMessage();
            map.put("msg", msg);
        }
        map.put("flag", flag);
        return map;
    }

    @RequestMapping(value = "/savecookie", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> saveCookie(String value) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            value = request.getParameter("value");
            CacheUtils.saveCookie(response, "User_Identification", value);
        } catch (Exception e) {
            map.put("flag", flag);
            map.put("msg", e.getMessage());
        }
        return map;

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> tedt(String value) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            value = request.getParameter("value");
            LoginUser user = service.getLoginUserByandName(value);
            map.put("name", user.getName());
            map.put("password", user.getPassword());
        } catch (Exception e) {
            map.put("flag", flag);
            map.put("msg", e.getMessage());
        }
        return map;

    }
}
