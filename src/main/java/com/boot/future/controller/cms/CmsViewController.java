package com.boot.future.controller.cms;

import com.boot.future.entity.LoginUser;
import com.boot.future.service.ILoginUserService;
import com.boot.future.swagger.result.Result;
import com.boot.future.util.CookiesUtils;
import com.boot.future.util.Utils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms")
public class CmsViewController extends BaseController {


	@GetMapping(value = "/index")
	public String index() {
		return "cms/index";
	}
	@GetMapping(value = "/cmslogin")
	public String cmslogin(Model model) {
		return "cms/login";
	}
	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
	@GetMapping(value = "/bsdemo")
	public String bsdemo(Model model) {
		model.addAttribute("name", "Dear");
		return "bd/bsdemo";
	}

}
