package com.nis.patient.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.patient.service.St012ZkjlService;
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
public class St012ZkjlController extends BaseController {
	private static final Logger c = Logger.getLogger(St012ZkjlController.class);
	@Autowired
	private St012ZkjlService wx;

	@RequestMapping({"/st012Zkjl/f_view/toInAndOutDepList"})
	public String K(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "patient/inAndOutDepList";
	}

	@RequestMapping({"/st012Zkjl/f_json/findInAndOutDepList"})
	@ResponseBody
	public void ac(HttpServletRequest request, HttpServletResponse response, String zyid) {
		List st012ZkjlList = null;
		if (ab.isNotEmpty(zyid)) {
			st012ZkjlList = this.wx.findInAndOutDepList(zyid);
		}

		MyPage page = new MyPage(1, st012ZkjlList.size(), st012ZkjlList.size(), st012ZkjlList);
		this.a(response, page);
	}
}