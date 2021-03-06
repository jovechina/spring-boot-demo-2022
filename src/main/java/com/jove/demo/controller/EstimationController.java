package com.jove.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jove.demo.model.Estimation;
import com.jove.demo.model.User;
import com.jove.demo.service.CodeMasterService;
import com.jove.demo.service.EstimationService;
import com.jove.demo.service.RoomCategoryService;
import com.jove.demo.service.RoomTypeService;
import com.jove.demo.model.RoomType;
import com.jove.demo.model.CodeMaster;

@Controller
@SessionAttributes({"user", "estimation", "pageType"})
public class EstimationController {
    static final String PAGE_MODEL_NEW = "NEW";
    private static final String PAGE_MODEL_EDIT = "EDIT";
    private static final String PAGE_MODEL_DETAIL = "DETAIL";
    private static final boolean PAGE_NEW = false;
    private static final boolean PAGE_CONFIRM = true;

    private static final Logger logger = LoggerFactory.getLogger(EstimationController.class);
    private final HomeController homeController;
    private final EstimationService estService;
    private final RoomCategoryService roomCategoryService;
    private final CodeMasterService codeMasterService;
    private final RoomTypeService roomTypeService;

    @Autowired
    public EstimationController(HomeController homeController, CodeMasterService codeMasterService,
                                RoomTypeService roomTypeService, EstimationService estService,
                                RoomCategoryService roomCategoryService) {
        this.homeController = homeController;
        this.estService = estService;
        this.roomCategoryService = roomCategoryService;
        this.codeMasterService = codeMasterService;
        this.roomTypeService = roomTypeService;

    }


    //????????????1 ??????
    @PostMapping("/estimation-1")
    public String estimationStepOne(@RequestParam String action, @Validated Estimation reqEstimation, Model model) {
        logger.debug("estimation 1 controller -> estimation step 2 page init");
        if (action.equals("prev")) {
            return homeController.home(model);
        } else {
            String pageType = (String) model.getAttribute("pageType");
            if (pageType == null || pageType.equals("")) {
                pageType = PAGE_MODEL_NEW;
            } else {
                logger.debug("estimation-1 pageType is not null, value:" + pageType);
            }
            // estimation 1 ????????????????????????session??????????????????
            model.addAttribute("pageType", pageType);
            Estimation estimation = (Estimation) model.getAttribute("estimation");
            if (estimation == null) {
                logger.error("estimation is null");
            } else {
                estimation.setRoomId(reqEstimation.getRoomId());
                estimation.setInteriorDecoration(reqEstimation.getInteriorDecoration());
            }
            model.addAttribute("estimation", estimation);
            return estimationStepTwoInit(model);
        }
    }


    //????????????2???????????????3 ??????
    @PostMapping(value = "/estimation-2")
    public String estimationStepTwo(@RequestParam String action, @RequestParam boolean pageStatus, @Validated Estimation estimation, Model model) {

        String pageType = (String) model.getAttribute("pageType");
        logger.debug("estimation-2 pageType:" + pageType);
        model.addAttribute("estimation", estimation);

        if (!pageStatus) {
            // ????????????
            logger.debug("estimation 2 controller - input page submitted - pageStatus=false.");
            if (action.equals("prev")) {
                return estimationStepOneInit(model);
            } else {
                return estimationStepThreeInit(model);
            }
        } else {
            // ????????????
            logger.debug("estimation 2 controller - confirm page");
            if (action.equals("prev")) {
                // ???????????????
                return estimationStepTwoInit(model);
            }
            int estimationId = 0;
            if (pageType.equals(PAGE_MODEL_EDIT)) {
                // ????????????
                estimationId = estimation.getEstimationId();
                estService.update(estimation);
            } else if (pageType.equals(PAGE_MODEL_NEW)) {
                // ????????????
                estimationId = estService.insert(estimation);
            } else {
                logger.error("???????????????????????????0x000001,??????????????????????????????");
            }

            // ?????????????????????session
            User user = (User) model.getAttribute("user");
            if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
                logger.error("session???????????????????????????");
            }
            estimation = new Estimation();
            estimation.setUserId(user.getUserId());
            model.addAttribute("estimation", estimation);
            return estimationSuccess(estimationId, model);
        }
    }

    //??????????????????????????????
    @GetMapping(value = "/estimation-search")
    public String estimationLists(Model model) {
        logger.debug("estimation list page init");
        model.addAttribute("pageType", null);
        model.addAttribute("estLists", estService.selectToSearchResult());
        return "estimation-list";
    }

    //??????->????????????
    @GetMapping(value = "/estimation/detail/{id}")
    public String estimationDetail(@PathVariable int id, Model model) {
        Estimation estimation = estService.selectOne(id);
        model.addAttribute("pageType", PAGE_MODEL_DETAIL);
        model.addAttribute("estimation", estimation);
        return estimationStepThreeInit(model);
    }

    //??????->????????????
    @GetMapping(value = "/estimation/edit/{id}")
    public String estimationEdit(@PathVariable int id, Model model) {
        Estimation estimation = estService.selectOne(id);
        model.addAttribute("pageType", PAGE_MODEL_EDIT);
        model.addAttribute("estimation", estimation);
        return estimationStepTwoInit(model);
    }

    //??????->??????
    @GetMapping(value = "/estimation/del/{id}")
    public String estimationDel(@PathVariable int id, Model model) {
        estService.del(id);
        return estimationLists(model);
    }


    protected String estimationStepOneInit(Model model) {
        // ??????????????????(estimation-step-1)???????????????
        // ????????????????????????????????????????????????????????????
        Estimation estimation = (Estimation) model.getAttribute("estimation");
        if (estimation == null) {
            logger.debug("estimation is null");
            User user = (User) model.getAttribute("user");
            if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
                logger.error("session???????????????????????????");
            }
            estimation = new Estimation();
            estimation.setUserId(user.getUserId());
        }
        // ??????
        Iterable<RoomType> roomTypes = roomTypeService.getAll();
        // ???????????????Category 1
        List<CodeMaster> codeMasters = codeMasterService.getByCategory(1);

        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("codeMasters", codeMasters);
        model.addAttribute("estimation", estimation);
        model.addAttribute("title", "????????????-????????????");

        String pageType = (String) model.getAttribute("pageType");
        if (pageType == null || pageType.equals("")) {
            logger.debug("estimationStepOneInit pageType is null, value:" + pageType);
        }
        return "estimation-step-1";
    }


    private String estimationStepTwoInit(Model model) {
        //?????????????????????????????????
        logger.debug("estimation step 2 page init");
        // ????????????
        model.addAttribute("roomCategories", roomCategoryService.selectAll());
        model.addAttribute("pageStatus", PAGE_NEW);
        model.addAttribute("title", "????????????-??????????????????");
        return "estimation-step-2";
    }

    private String estimationStepThreeInit(Model model) {
        //?????????????????????????????????
        logger.debug("estimation step 3 page init");
        // ????????????
        model.addAttribute("roomCategories", roomCategoryService.selectAll());
        model.addAttribute("pageStatus", PAGE_CONFIRM);
        model.addAttribute("title", "????????????-??????????????????");
        return "estimation-step-2";
    }

    private String estimationSuccess(int estimationId, Model model) {
        //????????????????????????
        logger.debug("estimation success page init");
        model.addAttribute("estimationId", estimationId);
        return "estimation-success";
    }
}
