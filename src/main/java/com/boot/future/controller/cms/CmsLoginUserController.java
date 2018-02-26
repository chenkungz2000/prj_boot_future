package com.boot.future.controller.cms;

import com.boot.future.controller.cms.BaseController;
import com.boot.future.entity.LoginUser;
import com.boot.future.entity.LoginUserData;
import com.boot.future.service.ILoginUserDataService;
import com.boot.future.service.ILoginUserService;
import com.boot.future.tools.CacheUtils;
import com.boot.future.tools.Tools;
import com.boot.future.tools.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/cmsloginuser")
public class CmsLoginUserController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(CmsLoginUserController.class);
    @Autowired
    ILoginUserService service;
    @Autowired
    ILoginUserDataService iLoginUserDataService;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String code, String value, String password) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean flag = false;
        try {
            password = request.getParameter("password");
            value = request.getParameter("value");
            LoginUser user = null;
            if (Tools.checkPhone(value)) {
                user = service.getLoginUserByPhoneAndPassword(value, password);
            } else {
                user = service.getLoginUserByNameAndPassword(value, password);
            }
            if (user != null) {
                flag = true;
                map.put("name", user.getName());
            }

        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;

    }
}
