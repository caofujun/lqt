package com.nis.hygiene.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.hygiene.entity.Hw001Jcxm;
import com.nis.hygiene.service.Hw001JcxmService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hw001JcxmController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw001JcxmController.class);
	@Autowired
	private Hw001JcxmService rp;

	@RequestMapping({"/hw001Jcxm/f_json/queryTree"})
	@SqlLog(p = "环境卫生监测--监测项目列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw001Jcxm hw001Jcxm) {
		AcAccount account = (AcAccount) this.b(request);
		hw001Jcxm.setUserId(account.getUsername());
		hw001Jcxm.setDeptId(account.getDepNo());
		List list = this.rp.queryTree(hw001Jcxm);
		this.b(response, list);
	}

	@RequestMapping({"/hw001Jcxm/f_json/queryList"})
	public void b(HttpServletRequest request, HttpServletResponse response, Hw001Jcxm hw001Jcxm) {
		List list = this.rp.queryList(hw001Jcxm);
		this.b(response, list);
	}

	@RequestMapping({"/hw001Jcxm/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/hw001JcxmList";
	}

	@RequestMapping({"/hw001Jcxm/f_json/findList"})
	@SqlLog(p = "环境卫生监测--监测项目列表")
	public void c(HttpServletRequest request, HttpServletResponse response, Hw001Jcxm hw001Jcxm) {
		List list = this.rp.findList(hw001Jcxm);
		this.b(response, list);
	}

	@RequestMapping({"/hw001Jcxm/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String classId) {
		if (StringUtils.isNotBlank(classId)) {
			Hw001Jcxm hw001Jcxm = this.rp.get(classId);
			modelMap.put("hw001Jcxm", hw001Jcxm);
			modelMap.put("action", "edit");
		}

		return "hygiene/hw001JcxmEdit";
	}

	@RequestMapping({"/hw001Jcxm/f_view/findSubclass"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--监测项目下级列表")
	public void y(HttpServletRequest request, HttpServletResponse response, String classId) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(classId)) {
				List e = this.rp.findSubclass(classId);
				if (e != null) {
					if (e.size() > 1) {
						result.setResult("success");
					} else {
						result.setResult("noSubclass");
					}
				}
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw001Jcxm/f_view/del"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除监测项目")
	public void c(HttpServletRequest request, HttpServletResponse response, String classId) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(classId)) {
				this.rp.delete(classId);
				result.setResult("success");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw001Jcxm/f_view/delClass"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除监测项目")
	public void z(HttpServletRequest request, HttpServletResponse response, String classId) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(classId)) {
				this.rp.delByClassIdPclassId(classId);
				result.setResult("success");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw001Jcxm/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存监测项目")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw001Jcxm hw001Jcxm, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw001Jcxm.getClassId())) {
				hw001Jcxm.setClassId(hw001Jcxm.getClassId().trim());
				if ("edit".equals(action)) {
					this.rp.c(hw001Jcxm);
					result.setResult("success");
				} else if (this.rp.get(hw001Jcxm.getClassId().trim()) != null) {
					result = new Result("error", "项目编号已存在");
				} else {
					this.rp.save(hw001Jcxm);
					result.setResult("success");
				}
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw001Jclb/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--更新监测项目状态")
	public void d(HttpServletRequest request, HttpServletResponse response, Hw001Jcxm hw001Jcxm) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw001Jcxm.getClassId())) {
				if (hw001Jcxm.getFlag() != null && hw001Jcxm.getFlag().intValue() == 1) {
					hw001Jcxm.setFlag(Integer.valueOf(0));
				} else {
					hw001Jcxm.setFlag(Integer.valueOf(1));
				}

				this.rp.b(hw001Jcxm);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw001Jcxm/f_json/get"})
	public void b(HttpServletRequest request, HttpServletResponse response, String classId) {
		Hw001Jcxm hw001Jcxm = null;
		if (StringUtils.isNotBlank(classId)) {
			hw001Jcxm = this.rp.get(classId);
		}

		this.b(response, hw001Jcxm);
	}
}