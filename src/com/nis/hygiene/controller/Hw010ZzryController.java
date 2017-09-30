package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.hygiene.entity.Hw010Zzry;
import com.nis.hygiene.service.Hw010ZzryService;
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
public class Hw010ZzryController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw010ZzryController.class);
	@Autowired
	private Hw010ZzryService rz;

	@RequestMapping({"/hw010Zzry/f_json/queryList"})
	public void a(HttpServletRequest request, HttpServletResponse response, Hw010Zzry hw010Zzry, String q) {
		hw010Zzry.setSearchString(q);
		hw010Zzry.setDeptId(this.d(request).getDepNo());
		List list = this.rz.queryList(hw010Zzry);
		this.b(response, list);
	}

	@RequestMapping({"/hw010Zzry/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/accreditList";
	}

	@RequestMapping({"/hw010Zzry/f_json/findAccreditList"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--授权人员列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw010Zzry hw010Zzry) {
		try {
			if (hw010Zzry.getSearchString() != null && !"".equals(hw010Zzry.getSearchString())) {
				hw010Zzry.setSearchString(ab.aR(hw010Zzry.getSearchString()));
			}

			MyPage e = this.rz.b(hw010Zzry);
			this.a(response, e);
		} catch (Exception arg4) {
			arg4.printStackTrace();
		}

	}

	@RequestMapping({"/hw010Zzry/f_view/toEdit"})
	public String k(HttpServletRequest request, ModelMap modelMap, String deptId, String employeeId) {
		if (StringUtils.isNotBlank(employeeId) && StringUtils.isNotBlank(deptId)) {
			Hw010Zzry hw010Zzry = this.rz.get(employeeId);
			modelMap.put("hw010Zzry", hw010Zzry);
			modelMap.put("action", "edit");
		}

		return "hygiene/hw010ZzryEdit";
	}

	@RequestMapping({"/hw010Zzry/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Hw010Zzry hw010Zzry, String action) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(hw010Zzry.getEmployeeId())) {
				if ("edit".equals(action)) {
					if (this.rz.get(hw010Zzry.getEmployeeId()) == null) {
						this.rz.update(hw010Zzry);
						result = new Result("success");
					} else {
						result = new Result("error", "专职人员信息已存在");
					}
				} else if (this.rz.get(hw010Zzry.getEmployeeId()) == null) {
					this.rz.save(hw010Zzry);
					result = new Result("success");
				} else {
					result = new Result("error", "专职人员信息已存在");
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

	@RequestMapping({"/hw010Zzry/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除授权人员")
	public void f(HttpServletRequest request, HttpServletResponse response, String deptId, String employeeId) {
		Result result = null;

		try {
			result = new Result();
			this.rz.x(employeeId, deptId);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw010Zzry/f_view/toAccreditSelect"})
	public String l(HttpServletRequest request, ModelMap modelMap, String deptId, String employeeId) {
		return "hygiene/accreditSelect";
	}
}