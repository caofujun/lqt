package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.patient.entity.St010Jcbyt;
import com.nis.patient.service.St010JcbytService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class St010JcbytController extends BaseController {
	@Autowired
	private St010JcbytService bF;

	@RequestMapping({"/st010Jcbyt/f_json/findSt010JcbytList"})
	@ResponseBody
	@SqlLog(p = "患者信息--检验单检出病原体信息")
	public void a(HttpServletRequest request, HttpServletResponse response, St010Jcbyt st010Jcbyt) {
		List st010JcbytList = this.bF.findSt010JcbytList(st010Jcbyt);
		this.a(response, st010JcbytList);
	}
}