package com.nis.organization.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.organization.entity.DeptType;
import com.nis.organization.service.DeptTypeService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptTypeController extends BaseController {
	private static final Logger c = Logger.getLogger(DeptTypeController.class);
	@Autowired
	private DeptTypeService vT;

	@RequestMapping({"/deptType/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "organization/deptTypeList";
	}

	@RequestMapping({"/deptType/f_view/query"})
	@ResponseBody
	public void Q(HttpServletRequest request, HttpServletResponse response) {
		List list = this.vT.getAll();
		ArrayList kvList = new ArrayList();
		Iterator arg5 = list.iterator();

		while (arg5.hasNext()) {
			DeptType deptType = (DeptType) arg5.next();
			kvList.add(new KvEntity(deptType.getOfficekindid().toString(), deptType.getOfficekind()));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/deptType/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, DeptType deptType) {
		MyPage page = this.vT.a(deptType);
		this.b(response, page);
	}

	@RequestMapping({"/deptType/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			DeptType deptType = this.vT.get(id);
			modelMap.put("deptType", deptType);
		}

		return "organization/deptTypeEdit";
	}

	@RequestMapping({"/deptType/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, DeptType deptType) {
		Result result = null;

		try {
			result = new Result();
			if (this.vT.get(deptType.getOfficekindid()) == null) {
				this.vT.save(deptType);
			} else {
				this.vT.update(deptType);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/deptType/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.vT.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}