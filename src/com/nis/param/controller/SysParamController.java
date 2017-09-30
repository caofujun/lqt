package com.nis.param.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.param.entity.SysParam;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Iterator;
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
public class SysParamController extends BaseController {
	private static final Logger c = Logger.getLogger(SysParamController.class);
	@Autowired
	private SysParamService j;

	@RequestMapping({"/sysParam/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "param/sysParamList";
	}

	@RequestMapping({"/sysParam/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "参数管理--参数列表")
	public void a(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		if (sysParam.getSearchString() != null && !"".equals(sysParam.getSearchString())) {
			sysParam.setSearchString(ab.aR(sysParam.getSearchString()));
		}

		List list = this.j.pageQuery(sysParam);
		this.b(response, list);
	}

	@RequestMapping({"/sysParam/json/queryList"})
	@ResponseBody
	public void r(HttpServletRequest request, HttpServletResponse response, String q) {
		List kvList = Param.getList();
		ArrayList list = new ArrayList();
		Iterator arg6 = kvList.iterator();

		while (true) {
			KvEntity p;
			label23 : do {
				while (arg6.hasNext()) {
					p = (KvEntity) arg6.next();
					if (ab.isNotEmpty(q)) {
						continue label23;
					}

					list.add(new KvEntity(p.getKey(), p.getValue()));
				}

				this.b(response, list);
				return;
			} while (p.getValue().indexOf(q) <= -1 && p.getKey().indexOf(q) <= -1);

			list.add(new KvEntity(p.getKey(), p.getValue()));
		}
	}

	@RequestMapping({"/sysParam/f_view/toedit"})
	@SqlLog(p = "参数管理--参数详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			SysParam sysParam = this.j.get(id);
			modelMap.put("sysParam", sysParam);
		}

		return "param/sysParamEdit";
	}

	@RequestMapping({"/sysParam/f_json/save"})
	@ResponseBody
	@SqlLog(p = "参数管理--保存参数")
	public void b(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		Result result = null;

		try {
			result = new Result();
			if (sysParam.getScopeLevel().equals(n.gh.getValue().toString())) {
				sysParam.setDepNo((String) null);
				sysParam.setUnitId((String) null);
			} else if (sysParam.getScopeLevel().equals(n.gi.getValue().toString())) {
				sysParam.setUnitName((String) null);
			} else if (sysParam.getScopeLevel().equals(n.gk.getValue().toString())) {
				sysParam.setDepNo((String) null);
				sysParam.setUnitName((String) null);
			}

			if (StringUtils.isBlank(sysParam.getId())) {
				List e = this.j.findSysParamList(sysParam);
				if (e != null && e.size() > 0) {
					result.setResult("error");
					result.setMsg("参数已经存在!");
					this.a(response, result);
					return;
				}

				this.j.save(sysParam);
			} else {
				this.j.update(sysParam);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysParam/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "参数管理--删除参数")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.j.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}