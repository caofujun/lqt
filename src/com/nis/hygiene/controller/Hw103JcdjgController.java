package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.hygiene.service.Hw103JcdjgService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hw103JcdjgController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw103JcdjgController.class);
	@Autowired
	private Hw103JcdjgService rG;

	@RequestMapping({"/hw103Jcdjg/f_json/findListByHw102Id"})
	@SqlLog(p = "环境卫生监测--监测样本列表")
	public void E(HttpServletRequest request, HttpServletResponse response, String hw102Id) {
		List list = this.rG.findListByHw102Id(hw102Id, (String) null);
		this.b(response, list);
	}
}