package com.nis.organization.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.p;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ae;
import com.nis.organization.entity.Unit;
import com.nis.organization.service.UnitService;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UnitController extends BaseController {
	private static final Logger c = Logger.getLogger(UnitController.class);
	@Autowired
	private UnitService se;

	@RequestMapping({"/unit/json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String name) {
		String q = request.getParameter("q");
		if (ab.isNotEmpty(q)) {
			name = ab.aR(q);
		}

		List list = this.se.findLike(name);
		ArrayList kvList = new ArrayList();
		Iterator arg7 = list.iterator();

		while (arg7.hasNext()) {
			Unit unit = (Unit) arg7.next();
			kvList.add(new KvEntity(unit.getUnitId().toString(), unit.getHospName()));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/unit/json/get"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String id) {
		if (!ab.isEmpty(id)) {
			if (id.indexOf(",") != -1) {
				List unit = this.se.cl(id);
				ArrayList kvList = new ArrayList();
				Iterator arg6 = unit.iterator();

				while (arg6.hasNext()) {
					Unit unit1 = (Unit) arg6.next();
					kvList.add(new KvEntity(unit1.getUnitId().toString(), unit1.getHospName()));
				}

				this.a(response, kvList);
			} else {
				Unit unit2 = this.se.get(id);
				if (unit2 != null) {
					this.a(response, new KvEntity(unit2.getUnitId().toString(), unit2.getHospName()));
				}
			}

		}
	}

	@RequestMapping({"/unit/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "organization/unitList";
	}

	@RequestMapping({"/unit/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "医院信息--医院列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Unit unit) {
		if (unit.getSearchString() != null && !"".equals(unit.getSearchString())) {
			unit.setSearchString(ab.aQ(unit.getSearchString()));
		}

		MyPage page = this.se.a(unit);
		this.b(response, page);
	}

	@RequestMapping({"/unit/f_view/toedit"})
	@SqlLog(p = "医院信息--医院详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Unit unit = this.se.get(id);
			modelMap.put("unit", unit);
		}

		return "organization/unitEdit";
	}

	@RequestMapping({"/unit/f_json/save"})
	@ResponseBody
	@SqlLog(p = "医院信息--保存医院信息")
	public void b(HttpServletRequest request, HttpServletResponse response, Unit unit) {
		Result result = null;

		try {
			result = new Result();
			if (this.se.get(unit.getUnitId()) == null) {
				this.se.save(unit);
			} else {
				this.se.update(unit);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/unit/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "医院信息--删除医院信息")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.se.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/unit/f_json/start"})
	@ResponseBody
	@SqlLog(p = "医院信息--启用医院可用状态")
	public void c(HttpServletRequest request, HttpServletResponse response, Unit unit) {
		Result result = null;

		try {
			result = new Result();
			this.se.updateUnitState(unit.getUnitId(), String.valueOf(p.gy.getValue()));
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/unit/f_json/stop"})
	@ResponseBody
	@SqlLog(p = "医院信息--禁用医院可用状态")
	public void d(HttpServletRequest request, HttpServletResponse response, Unit unit) {
		Result result = null;

		try {
			result = new Result();
			this.se.updateUnitState(unit.getUnitId(), String.valueOf(p.gz.getValue()));
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/unit/f_json/upload"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long unitId, MultipartFile photo) {
		Result result = null;

		try {
			result = new Result();
			String e = "";
			if (photo != null) {
				e = ae.a(photo, request, Integer.valueOf(5), "hospitalPhoto");
				if ("maxSize".equals(e)) {
					new Result("maxSize", "图片大小超过5M!");
				} else if ("typeNoMatch".equals(e)) {
					new Result("typeNoMatch", "上传的不是图片文件!");
				} else {
					result.setResult("success");
					result.setMsg(e);
				}
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/unit/json/queryList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String q, Long flag, int page, int size) {
		Unit unit = new Unit();
		unit.setSearchString(ab.aR(q));
		unit.setFlag(flag);
		unit.setPage(Integer.valueOf(page));
		unit.setSize(Integer.valueOf(size));
		MyPage unitPage = this.se.a(unit);
		this.b(response, unitPage.getRows());
	}
}