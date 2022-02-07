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
import com.jove.demo.model.RoomCategory;
import com.jove.demo.model.User;
import com.jove.demo.service.CodeMasterService;
import com.jove.demo.service.EstimationService;
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
	public String estimationStepOne(@RequestParam String action, @Validated Estimation reqEstimation, BindingResult result, Model model) {
		logger.debug("estimation 1 controller -> estimation step 2 page init");
		if (action.equals("prev")) {
			return homeController.home(model);
		} else {
			String pageType = (String) model.getAttribute("pageType");
			if (pageType == null || pageType.equals("")) {
				pageType = PAGE_MODEL_NEW;
			}
			// estimation 1 画面的内容保存进session，其他值不变
			model.addAttribute("pageType", pageType);
			Estimation estimation = (Estimation) model.getAttribute("estimation");
			if (estimation == null) {
				logger.error("estimation is null");
			}
			estimation.setRoomId(reqEstimation.getRoomId());
			estimation.setInteriorDecoration(reqEstimation.getInteriorDecoration());
			model.addAttribute("estimation", estimation);
			
			return estimationStepTwoInit(model);
		}
	}

	@Autowired
	private EstimationService estService;

	@PostMapping(value = "/estimation-2")
	public String estimationStepTwo(@RequestParam String action, @RequestParam boolean pageStatus, @Validated Estimation estimation, Model model) {

		String pageType = (String) model.getAttribute("pageType");
		model.addAttribute("estimation", estimation);

		if (!pageStatus) {
			// 输入画面
			logger.debug("estimation 2 controller - input page submited - pageStatus=false.");
			if (action.equals("prev")) {
				return estimationStepOneInit(model);
			} else {
				return estimationStepThreeInit(model);
			}
		} else {
			// 确认画面
			logger.debug("estimation 2 controller - confirm page");
			if (action.equals("prev")) {
				// 上一步按钮
				return estimationStepTwoInit(model);
			} 
			int estimationId = 0;			
			if (pageType.equals(PAGE_MODEL_EDIT)) {
				// 更新按钮
				estimationId = estimation.getEstimationId();
				estService.update(estimation);
			} else if (pageType.equals(PAGE_MODEL_NEW)) {
				// 保存按钮
				estimationId = estService.insert(estimation);
			} else {
				logger.error("系统异常，错误代码0x000001,请与系统管理员联系。");
			}			

			// 保存成功后重置session
			User user = (User) model.getAttribute("user");
			if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
				logger.error("session过期，请重新登录。");
			}
			estimation = new Estimation();
			estimation.setUserId(user.getUserId());
			model.addAttribute("estimation", estimation);
			model.addAttribute("pageType", null);
			return estimationSuccess(estimationId, model);
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

	private String estimationStepTwoInit(Model model) {
		//装修估算，输入画面显示
		logger.debug("estimation step 2 page init");
		// 装修服务
		model.addAttribute("roomCategories", roomCategoryService.selectAll());
		model.addAttribute("pageStatus", PAGE_NEW);
		model.addAttribute("title", "装修估算-装修要求输入");
		return "estimation-step-2";
	}

	private String estimationStepThreeInit(Model model) {
		//装修估算，确认画面显示
		logger.debug("estimation step 3 page init");
		// 装修服务
		model.addAttribute("roomCategories", roomCategoryService.selectAll());
		model.addAttribute("pageStatus", PAGE_CONFIRM);
		model.addAttribute("title", "装修估算-装修要求确认");
		return "estimation-step-2";
	}
	
	private String estimationSuccess(int estimationId, Model model) {
		//保存成功画面显示
		logger.debug("estimation successpage init");
		model.addAttribute("estimationId", estimationId);
		return "estimation-success";
	}

}
