package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.patient.entity.St014Pacs;
import com.nis.patient.service.St014PacsService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class St014PacsController extends BaseController {
	private static final Logger c = Logger.getLogger(St014PacsController.class);
	@Autowired
	private St014PacsService bj;

	@RequestMapping({"/st014Pacs/f_view/toImageResultsList"})
	public String B(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid) {
		return "patient/imageResultsList";
	}

	@RequestMapping({"/st014Pacs/f_json/findImageResultsList"})
	@ResponseBody
	@SqlLog(p = "患者信息--影像列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St014Pacs st014Pacs) {
		List st014PacsList = this.bj.findImageResultsList(st014Pacs);
		MyPage page = new MyPage(1, st014PacsList.size(), st014PacsList.size(), st014PacsList);
		this.a(response, page);
	}

	@RequestMapping({"/st014Pacs/f_json/getResultsContent"})
	@ResponseBody
	@SqlLog(p = "患者信息--影像详情")
	public void at(HttpServletRequest request, HttpServletResponse response, String id) {
		St014Pacs st014Pacs = null;
		if (ab.isNotEmpty(id)) {
			st014Pacs = this.bj.get(id);
		}

		this.a(response, st014Pacs);
	}
}