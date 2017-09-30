package com.nis.report.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.report.entity.SysModuleExplain;
import com.nis.report.service.SysModuleExplainService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ModuleExplainController extends BaseController {
	@Autowired
	private SysModuleExplainService xD;

	@RequestMapping({"/moduleExplain/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String mkName) {
		if (ab.isNotEmpty(mkName)) {
			List moduleExplainList = this.xD.findByName(mkName);
			modelMap.put("moduleExplainList", moduleExplainList);
		}

		return "report/sysModuleExplainIndex";
	}

	@RequestMapping({"/moduleExplain/f_view/toedit"})
	@SqlLog(p = "模块说明管理--模块说明详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			SysModuleExplain moduleExplain = this.xD.get(id);
			modelMap.put("moduleExplain", moduleExplain);
		}

		return "report/sysModuleExplainEdit";
	}

	@RequestMapping({"/moduleExplain/f_view/List"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "report/sysModuleExplainList";
	}

	@RequestMapping({"/moduleExplain/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "模块说明管理--模块说明列表")
	public void a(HttpServletRequest request, HttpServletResponse response, SysModuleExplain moduleExplain) {
		MyPage page = this.xD.a(moduleExplain);
		this.b(response, page);
	}

	@RequestMapping({"/moduleExplain/f_json/save"})
	@ResponseBody
	@SqlLog(p = "模块说明管理--保存模块说明")
	public void b(HttpServletRequest request, HttpServletResponse response, SysModuleExplain moduleExplain) {
		Result result = null;
		LoginUser user = this.d(request);
		moduleExplain.setUpdateTime(new Date());
		moduleExplain.setCreateUser(user.getUserId());
		moduleExplain.setCreateName(user.getUsername());

		try {
			result = new Result();
			if (this.xD.get(moduleExplain.getMid()) == null) {
				this.xD.save(moduleExplain);
			} else {
				this.xD.update(moduleExplain);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			arg6.printStackTrace();
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/moduleExplain/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "模块说明管理--删除模块说明")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.xD.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/moduleExplain/f_json/queryHasHelp"})
	@ResponseBody
	public void aE(HttpServletRequest request, HttpServletResponse response, String mid) {
		Result result = null;

		try {
			result = new Result();
			SysModuleExplain e = this.xD.get(mid);
			if (e != null) {
				result.setResult("success");
			} else {
				result.setResult("error");
			}
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/moduleExplain/f_view/showHelp"})
	public String d(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String mid) {
		if (ab.isNotEmpty(mid)) {
			SysModuleExplain sysModuleExplain = this.xD.get(mid);
			modelMap.put("moduleExplain", sysModuleExplain);
		}

		return "report/sysModuleExplainIndex";
	}
}