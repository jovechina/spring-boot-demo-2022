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

@SessionAttributes({"user", "estimation", "pageType"})
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger("HomeController.class");

    private final EstimationController estimationController;
    private final UserService userService;
    private final ContentService contentService;

    @Autowired
    public HomeController(EstimationController estimationController,
                          UserService userService, ContentService contentService) {
        this.estimationController = estimationController;
        this.userService = userService;
        this.contentService = contentService;

    }

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
            //如果已经登录就直接进入下一个画面
            // 重置session变量
            model.addAttribute("pageType", EstimationController.PAGE_MODEL_NEW);
            model.addAttribute("estimation", null);
            return estimationController.estimationStepOneInit(model);
        }
    }


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
        // 服务端验证，确认用户是否存在。
        user = userService.validateLogin(user);
        logger.debug("token" + user.getToken());
        if (user.getToken() == null || user.getToken().equals("")) {
            logger.debug("login failed");
            result.reject("error.loginError", "用户名密码不正确，请重新输入。");
            model.addAttribute("user", user);
            return "login";
        } else {
            logger.debug("登录成功, userId:" + user.getUserId());
            model.addAttribute("user", user);
        }
        return estimationController.estimationStepOneInit(model);
    }

    @GetMapping("/test-db/{id}")
    public String content(@PathVariable int id, Model model) {
        // 测试数据库连接，数据访问
        ContentResult contentResult = contentService.selectContentById(id);
        model.addAttribute("contentResult", contentResult);
        model.addAttribute("title", "数据库连接测试");
        return "test-db";
    }

}
