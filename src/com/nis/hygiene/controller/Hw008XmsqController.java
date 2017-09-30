package com.nis.hygiene.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.hygiene.entity.Hw008Xmsq;
import com.nis.hygiene.service.Hw008XmsqService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hw008XmsqController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw008XmsqController.class);
	@Autowired
	private Hw008XmsqService rx;

	@RequestMapping({"/hw008Xmsq/f_json/findXmsqList"})
	@SqlLog(p = "环境卫生监测--权限项目列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw008Xmsq hw008Xmsq) {
		List list = this.rx.findXmsqList(hw008Xmsq);
		this.b(response, list);
	}

	@RequestMapping({"/hw008Xmsq/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Hw008Xmsq hw008Xmsq) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(hw008Xmsq.getUserId())) {
				this.rx.b(hw008Xmsq);
				result = new Result("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw008Xmsq/f_json/judgeResultsPermissions"})
	@ResponseBody
	@SqlLog(p = "获取环境卫生监测编辑权限")
	public void x(HttpServletRequest request, HttpServletResponse response, String djDeptId, String classId) {
		AcAccount account = (AcAccount) this.b(request);
		int num = this.rx.judgeResultsPermissions(account.getUsername(), account.getDepNo(), djDeptId, classId);
		this.a(response, Integer.valueOf(num));
	}

	@RequestMapping({"/hw008Xmsq/f_json/judgeReportPermissions"})
	@ResponseBody
	public void y(HttpServletRequest request, HttpServletResponse response, String djDeptId, String classId) {
		AcAccount account = (AcAccount) this.b(request);
		int num = this.rx.judgeReportPermissions(account.getUsername(), account.getDepNo(), djDeptId, classId);
		this.a(response, Integer.valueOf(num));
	}
}