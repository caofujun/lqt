package com.nis.monitor.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.monitor.service.Gr018YsgrysService;
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
public class Gr018YsgrysController extends BaseController {
	private static final Logger c = Logger.getLogger(Gr018YsgrysController.class);
	@Autowired
	private Gr018YsgrysService aR;

	@RequestMapping({"/gr018Ysgrys/f_view/toInfectionList"})
	public String K(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "monitor/infectionList";
	}

	@RequestMapping({"/gr018Ysgrys/f_json/findInfectionList"})
	@ResponseBody
	public void ac(HttpServletRequest request, HttpServletResponse response, String zyid) {
		List gr018YsgrysList = null;
		if (ab.isNotEmpty(zyid)) {
			gr018YsgrysList = this.aR.findListByZyid(zyid);
		}

		MyPage page = new MyPage(1, gr018YsgrysList.size(), gr018YsgrysList.size(), gr018YsgrysList);
		this.a(response, page);
	}
}