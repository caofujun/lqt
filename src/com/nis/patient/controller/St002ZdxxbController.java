package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.patient.service.St002ZdxxbService;
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
public class St002ZdxxbController extends BaseController {
	private static final Logger c = Logger.getLogger(St002ZdxxbController.class);
	@Autowired
	private St002ZdxxbService bv;

	@RequestMapping({"/st002Zdxxb/f_view/toDiagnosisList"})
	public String K(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "patient/diagnosisInfoList";
	}

	@RequestMapping({"/st003Cryxxb/f_json/findDiagnosisList"})
	@ResponseBody
	@SqlLog(p = "患者信息--诊断列表")
	public void ac(HttpServletRequest request, HttpServletResponse response, String zyid) {
		List st002ZdxxbList = null;
		if (ab.isNotEmpty(zyid)) {
			st002ZdxxbList = this.bv.findListByZyid(zyid);
		}

		MyPage page = new MyPage(1, st002ZdxxbList.size(), st002ZdxxbList.size(), st002ZdxxbList);
		this.a(response, page);
	}
}