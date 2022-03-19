package com.jove.demo.controller;

import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

import io.swagger.v3.oas.annotations.Operation;

@SessionAttributes({ "user", "estimation", "pageType" })
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger("HomeController.class");

	@Autowired
	private EstimationController estimationController;

	@Operation(summary = "默认启动画面")
	@GetMapping("/")
	public String home(Model model) {
		logger.info("Welcome , home !");
		User user = (User) model.getAttribute("user");
		if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
			user = new User();
			model.addAttribute("user", user);
			model.addAttribute("title", "装修估算-登录");
			return "login";
		} else {
			// 如果已经登录就直接进入下一个画面
			// 重置session变量

			model.addAttribute("pageType", EstimationController.PAGE_MODEL_NEW);
			model.addAttribute("estimation", null);
			return estimationController.estimationStepOneInit(model);
		}
	}

	@Operation(summary = "用户登录")
	@PostMapping("/login")
	public String login(@Validated User user, BindingResult result, Model model) {
		// 表单验证，确认输入是否符合要求
		if (result.hasErrors()) {
			logger.info("用户名密码输入错误:");
			user.setToken(null);
			model.addAttribute("user", user);
			model.addAttribute("title", "装修估算-登录");
			return "login";
		}

		// Shiro 处理登录
		// 1.获取Subject
		Subject subject = SecurityUtils.getSubject();

		// 2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

		try {
			subject.login(token);

			/*
			 * 无任何异常，则登录成功
			 */
			return estimationController.estimationStepOneInit(model);

		} catch (UnknownAccountException  e) {
			logger.debug("用户名不正确:" + user.getUserName());
			result.reject("error.loginerror", "用户名密码不正确，请重新输入。");
			user.setToken("");
			model.addAttribute("user", user);
			return "login";
		} catch (IncorrectCredentialsException e) {
			logger.debug("密码不正确:" + user.getPassword());
			result.reject("error.loginerror", "用户名密码不正确，请重新输入。");
			user.setToken("");
			model.addAttribute("user", user);
			return "login";
		}
	}

	@Autowired
	ContentService contentService;

	@Operation(summary = "数据库连接测试")
	@GetMapping("/test-db/{id}")
	public String content(@PathVariable int id, Model model) {
		// 测试数据库连接，数据访问
		ContentResult contentResult = contentService.selectContentById(id);
		model.addAttribute("contentResult", contentResult);
		model.addAttribute("title", "数据库连接测试");
		return "test-db";
	}

}
