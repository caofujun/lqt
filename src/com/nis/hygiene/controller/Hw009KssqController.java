package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.hygiene.entity.Hw009Kssq;
import com.nis.hygiene.service.Hw009KssqService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hw009KssqController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw009KssqController.class);
	@Autowired
	private Hw009KssqService ry;

	@RequestMapping({"/hw009Kssq/f_json/queryList"})
	@SqlLog(p = "环境卫生监测--科室列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw009Kssq hw009Kssq, String q) {
		q = StringUtils.isNotBlank(q) ? q.trim() : null;
		LoginUser user = this.d(request);
		hw009Kssq.setDeptId(user.getDepNo());
		hw009Kssq.setUserId(user.getUsername());
		hw009Kssq.setSearchString(q);
		List list = this.ry.queryList(hw009Kssq);
		this.b(response, list);
	}

	@RequestMapping({"/hw009Kssq/f_json/findAccreditDept"})
	@SqlLog(p = "环境卫生监测--授权科室列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw009Kssq hw009Kssq) {
		List list = this.ry.findAccreditDept(hw009Kssq);
		this.b(response, list);
	}
}