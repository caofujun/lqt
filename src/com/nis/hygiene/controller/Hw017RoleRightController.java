package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.hygiene.entity.Hw016Role;
import com.nis.hygiene.entity.Hw017RoleRight;
import com.nis.hygiene.service.Hw017RoleRightService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hw017RoleRightController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw017RoleRightController.class);
	@Autowired
	private Hw017RoleRightService rB;

	@RequestMapping({"/hw017RoleRight/f_json/findList"})
	public void a(HttpServletRequest request, HttpServletResponse response, Hw017RoleRight hw017RoleRight) {
		List list = this.rB.findList(hw017RoleRight);
		this.b(response, list);
	}

	@RequestMapping({"/hw017RoleRight/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存角色权限")
	public void b(HttpServletRequest request, HttpServletResponse response, Hw016Role hw016Role) {
		Result result = null;

		try {
			if (hw016Role.getHw017List() != null && hw016Role.getHw017List().size() > 0) {
				this.rB.g(hw016Role.getHw017List(), hw016Role.getRoleId());
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
}