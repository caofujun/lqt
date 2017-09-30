package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.hygiene.entity.Hw016Role;
import com.nis.hygiene.service.Hw016RoleService;
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
public class Hw016RoleController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw016RoleController.class);
	@Autowired
	private Hw016RoleService rA;

	@RequestMapping({"/hw016Role/f_view/toRoleManage"})
	public String u(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/roleManage";
	}

	@RequestMapping({"/hw016Role/f_json/findList"})
	@SqlLog(p = "环境卫生监测--角色列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw016Role hw016Role) {
		List list = this.rA.findList(hw016Role);
		this.b(response, list);
	}

	@RequestMapping({"/hw016Role/f_view/toedit"})
	@SqlLog(p = "环境卫生监测--角色详细信息")
	public String q(HttpServletRequest request, ModelMap modelMap, String roleId) {
		if (StringUtils.isNotBlank(roleId)) {
			Hw016Role hw016Role = this.rA.get(roleId);
			modelMap.put("hw016Role", hw016Role);
			modelMap.put("action", "edit");
		}

		return "hygiene/roleEdit";
	}

	@RequestMapping({"/hw016Role/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存角色信息")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw016Role hw016Role, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw016Role.getRoleId())) {
				hw016Role.setRoleId(hw016Role.getRoleId().trim());
				if ("edit".equals(action)) {
					this.rA.updHw016Role(hw016Role);
					result.setResult("success");
				} else if (this.rA.get(hw016Role.getRoleId()) != null) {
					result = new Result("error", "角色编码已存在");
				} else {
					this.rA.save(hw016Role);
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

	@RequestMapping({"/hw016Role/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除角色信息")
	public void c(HttpServletRequest request, HttpServletResponse response, String roleId) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(roleId)) {
				result = new Result();
				this.rA.bu(roleId);
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

	@RequestMapping({"/hw016Role/f_json/findListJoinHw018"})
	@SqlLog(p = "环境卫生监测--角色列表")
	public void A(HttpServletRequest request, HttpServletResponse response, String userId) {
		List list = this.rA.findListJoinHw018(userId);
		this.b(response, list);
	}
}