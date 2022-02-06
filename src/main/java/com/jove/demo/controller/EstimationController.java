package com.jove.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jove.demo.model.Estimation;
import com.jove.demo.model.User;
import com.jove.demo.service.CodeMasterService;
import com.jove.demo.service.RoomCategoryService;
import com.jove.demo.service.RoomTypeService;
import com.jove.demo.model.RoomType;
import com.jove.demo.model.CodeMaster;

@Controller
@SessionAttributes({ "user", "estimation", "pageType" })
public class EstimationController {
	private static final String PAGE_MODEL_NEW = "NEW";
	private static final String PAGE_MODEL_EDIT = "EDIT";
	private static final String PAGE_MODEL_DETAIL = "DETAIL";
	private static final boolean PAGE_NEW = false;
	private static final boolean PAGE_CONFIRM = true;
	
	private static final Logger logger = LoggerFactory.getLogger(EstimationController.class);

	
	@Autowired
	private HomeController homeController;
	
	@PostMapping("/estimation-1")
	public String estimationStepOne(@RequestParam String action, @Validated Estimation estimation,
			BindingResult result, Model model) {
		logger.debug("estimation 1 controller -> estimation step 2 page init");
		if (action.equals("prev")) {
			return homeController.home(model);
		} else {
			String pageType = (String) model.getAttribute("pageType");
			if (pageType == null || pageType.equals("")) {
				pageType = PAGE_MODEL_NEW;
			}
			model.addAttribute("pageType", pageType);
			return estimationStepTwoInit(estimation, model);
		}
	}
	
	
	
	@Autowired
	CodeMasterService codeMasterService;
	
	@Autowired
	RoomTypeService roomTypeService;	
	
	protected String estimationStepOneInit(Model model) {
		// 准备下个画面(estimation-step-1)的初始数据
		// 画面项目显示数据，默认选择可以在这里设置
		Estimation estimation = (Estimation) model.getAttribute("estimation");
		if (estimation == null) {
			logger.debug("estimation is null");
			User user = (User) model.getAttribute("user");
			if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
				logger.error("session过期，请重新登录。");
			}
			estimation = new Estimation();
			estimation.setUserId(user.getUserId());
		}
		// 户型
		Iterable<RoomType> roomTypes = roomTypeService.getAll();
		// 装修状况的Category 1
		List<CodeMaster> codeMasters = codeMasterService.getByCategory(1);

		model.addAttribute("roomTypes", roomTypes);
		model.addAttribute("codeMasters", codeMasters);
		model.addAttribute("estimation", estimation);
		model.addAttribute("title", "装修估算-房屋状况");
		return "estimation-step-1";
	}
	
	@Autowired
	RoomCategoryService roomCategoryService;
	
	private String estimationStepTwoInit(@Validated Estimation reqEstimation, Model model) {
		logger.debug("estimation step 2 page init");
		Estimation estimation = (Estimation) model.getAttribute("estimation");
		if (estimation == null) {
			logger.error("estimation is null");
		}
		estimation.setRoomId(reqEstimation.getRoomId());
		estimation.setInteriorDecoration(reqEstimation.getInteriorDecoration());

		// 装修服务
		model.addAttribute("roomCategories",  roomCategoryService.selectAll());
		model.addAttribute("estimation", estimation);
		model.addAttribute("pageStatus", PAGE_NEW);
		model.addAttribute("title", "装修估算-装修要求");
		return "estimation-step-2";
	}
}
