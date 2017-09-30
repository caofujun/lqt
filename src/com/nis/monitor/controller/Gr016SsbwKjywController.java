package com.nis.monitor.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import com.nis.monitor.service.Gr016SsbwKjywService;
import com.nis.patient.entity.St005Ssxxb;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Gr016SsbwKjywController extends BaseController {
	private static final Logger c = Logger.getLogger(Gr016SsbwKjywController.class);
	@Autowired
	private Gr016SsbwKjywService uy;

	@RequestMapping({"/gr016SsbwKjyw/f_json/update"})
	@ResponseBody
	@SqlLog(p = "患者手术详情--更新手术信息")
	public void a(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb,
			Gr016SsbwKjyw gr016SsbwKjyw) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(st005Ssxxb.getId())) {
				AcAccount e = (AcAccount) this.b(request);
				st005Ssxxb.setLastLog(e.getUserId());
				st005Ssxxb.setLastModUserid(e.getRealname());
				st005Ssxxb.setLastModDate(f.getCurDate());
				this.uy.a(st005Ssxxb, gr016SsbwKjyw);
				result.setResult("success");
			} else {
				result.setResult("error");
				result.setMsg("关键参数丢失！");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gr016SsbwKjyw/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "患者手术详情--删除手术用药")
	public void c(HttpServletRequest request, HttpServletResponse response, String relid) {
		Result result = null;

		try {
			result = new Result();
			this.uy.delete(relid);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}