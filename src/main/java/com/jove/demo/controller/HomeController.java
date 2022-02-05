package com.jove.demo.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jove.demo.model.ContentResult;
import com.jove.demo.service.ContentService;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger("HomeController.class");
	@GetMapping("/")
	public String home(Model model) {
		logger.debug(" home controller,  home function started.");
		logger.info(" home controller,  home function started.");
		String message = "欢迎使用Spring Boot";
		model.addAttribute("message", message);
		return "home";
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
