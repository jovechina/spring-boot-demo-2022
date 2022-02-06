package com.jove.demo.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jove.demo.model.ContentResult;
import com.jove.demo.model.User;
import com.jove.demo.service.ContentService;
import com.jove.demo.service.UserService;

@SessionAttributes({ "user" })
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger("HomeController.class");
	@GetMapping("/")
	public String home(Model model) {
		logger.info("Welcome , home !");
		User user = (User) model.getAttribute("user");
		if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
			user = new User();
			model.addAttribute("user", user);
			return "login";
		} else {
			//如果已经登录就直接进入下一个画面
			return "home";
			//return estimationController.estimationStepOneInit(model);
		}
	}

	@Autowired
	UserService userService;
	@PostMapping("/login")
	public String login(@Validated User user, BindingResult result, Model model) {
		

		// 表单验证，确认输入是否符合要求
		if (result.hasErrors()) {
			logger.info("用户名密码输入错误:");
			user.setToken(null);
			model.addAttribute("user", user);
			return "login";
		}

		// 服务端验证，确认用户是否存在。
		user = userService.validateLogin(user);
		logger.debug("token" + user.getToken());
		if (user.getToken() == null || user.getToken() == "") {
			logger.debug("login failed");
			result.reject("error.loginerror", "用户名密码不正确，请重新输入。");
			model.addAttribute("user", user);
			return "login";
		} else {
			logger.debug("登录成功, userId:" + user.getUserId());
			model.addAttribute("user", user);
		}
		return "home";
		//return estimationController.estimationStepOneInit(model);

	}	
	
	@Autowired
	ContentService contentService;	
	@GetMapping("/test-db/{id}")
	public String content(@PathVariable int id, Model model) {
		ContentResult contentResult = contentService.selectContentById(id);
		model.addAttribute("contentResult", contentResult);
		return "test-db";
	}
	
	
}
