package com.boot.future.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
