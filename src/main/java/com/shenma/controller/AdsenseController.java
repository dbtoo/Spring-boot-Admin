package com.shenma.controller;

import com.shenma.model.Adsense;
import com.shenma.model.NewsCategory;
import com.shenma.model.ResObject;
import com.shenma.service.AdsenseService;
import com.shenma.service.NewsCategoryService;
import com.shenma.util.Constant;
import com.shenma.util.PageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AdsenseController {

	@Autowired
	private AdsenseService adsenseService;

	/**
	 * 文章分类列表
	 * @param adsense
	 * @param pageCurrent
	 * @param pageSize
	 * @param pageCount
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/adsenseManage_{pageCurrent}_{pageSize}_{pageCount}")
	public String adsenseManage(Adsense adsense, @PathVariable Integer pageCurrent, @PathVariable Integer pageSize, @PathVariable Integer pageCount, Model model) {
		//判断
		if(pageSize == 0) pageSize = 10;
		if(pageCurrent == 0) pageCurrent = 1;
		int rows = adsenseService.count(adsense);
		if(pageCount == 0) pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

		//查询
		adsense.setStart((pageCurrent - 1)*pageSize);
		adsense.setEnd(pageSize);
		List<Adsense> list = adsenseService.list(adsense);

		//输出
		model.addAttribute("list", list);
		String pageHTML = PageUtil.getPageContent("adsenseManage_{pageCurrent}_{pageSize}_{pageCount}?type="+adsense.getType(), pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("adsense",adsense);
		return "adsense/adsenseManage";
	}

	/**
	 * 广告位置增加、修改跳转
	 * @param model
	 * @param adsense
	 * @return
	 */
	@GetMapping("/admin/adsenseEdit")
	public String adsenseEditGet(Model model,Adsense adsense) {
		if(adsense.getId()!=0){
			Adsense adsenseT = adsenseService.findById(adsense);
			model.addAttribute("adsense",adsenseT);
		}
		return "adsense/adsenseEdit";
	}

	/**
	 * 文章分类新增、修改提交
	 * @param model
	 * @param adsense
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/admin/adsenseEdit")
	public String adsenseEditPost(Model model,Adsense adsense) {
		if(adsense.getId()!=0){
			adsenseService.update(adsense);
		} else {
			adsenseService.insert(adsense);
		}
		return "redirect:adsenseManage_0_0_0";
	}
	
	@ResponseBody
	@PostMapping("/admin/adsenseEditState")
	public ResObject<Object> adsenseEditState(Adsense adsense) {
		adsenseService.updateState(adsense);
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
		return object;
	}
	
}
