package com.nis.icu.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.icu.entity.Gm002Djpdd;
import com.nis.icu.service.Gm001DjpdmService;
import com.nis.icu.service.Gm002DjpddService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Gm002DjpddController extends BaseController {
	private static final Logger c = Logger.getLogger(Gm002DjpddController.class);
	@Autowired
	private Gm002DjpddService sc;
	@Autowired
	private Gm001DjpdmService sb;
	@Autowired
	private DepService e;

	@RequestMapping({"/gm002Djpdd/f_view/index"})
	public String b(HttpServletRequest request, ModelMap modelMap, String deptId, String strDate, String enddt,
			String dtYear, String dtMonth, String weeknumber) {
		modelMap.put("deptId", deptId);
		modelMap.put("strDate", strDate);
		modelMap.put("enddt", enddt);
		modelMap.put("dtYear", dtYear);
		modelMap.put("dtMonth", dtMonth);
		modelMap.put("weeknumber", weeknumber);
		return "icu/gm002DjpddList";
	}

	@RequestMapping({"/gm002Djpdd/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String deptId, String strDate, Integer year,
			Integer month, Integer week) throws Exception {
		Dep dep = this.e.get(deptId);
		List page = this.sc.getByDateAndDeptId(deptId, strDate, year, month, week);
		if (dep.getIfbedicu() != null && dep.getIfbedicu().longValue() == 1L) {
			page = this.sc.getBedByDateAndDeptId(deptId, strDate, year, month, week);
		}

		this.a(response, page);
	}

	@RequestMapping({"/gm002Djpdd/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gm002Djpdd gm002Djpdd, String grade,
			String startdt, String enddt) {
		Result result = null;

		try {
			result = new Result();
			if (grade.equals("A")) {
				gm002Djpdd.setGradea(Integer.valueOf(1));
			} else if (grade.equals("B")) {
				gm002Djpdd.setGradeb(Integer.valueOf(1));
			} else if (grade.equals("C")) {
				gm002Djpdd.setGradec(Integer.valueOf(1));
			} else if (grade.equals("D")) {
				gm002Djpdd.setGraded(Integer.valueOf(1));
			} else if (grade.equals("E")) {
				gm002Djpdd.setGradee(Integer.valueOf(1));
			}

			this.sc.save(gm002Djpdd);
			this.sb.a(gm002Djpdd.getDtYear(), gm002Djpdd.getDtMonth(), gm002Djpdd.getWeeknumber(),
					gm002Djpdd.getDeptId(), startdt, enddt, this.d(request).getUsername(),
					this.d(request).getRealname());
			result.setResult("success");
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm002Djpdd/f_json/saveList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gm002Djpdd gm002Djpdd, String startdt,
			String enddt) {
		Result result = null;

		try {
			result = new Result();
			result.setResult("success");
			List e = gm002Djpdd.getDjpddList();
			Iterator arg8 = e.iterator();

			while (arg8.hasNext()) {
				Gm002Djpdd djpdd = (Gm002Djpdd) arg8.next();
				djpdd.setDtYear(gm002Djpdd.getDtYear());
				djpdd.setDtMonth(gm002Djpdd.getDtMonth());
				djpdd.setWeeknumber(gm002Djpdd.getWeeknumber());
				djpdd.setDeptId(gm002Djpdd.getDeptId());
				if (ab.isNotEmpty(djpdd.getGrade())) {
					if (djpdd.getGrade().equals("A")) {
						djpdd.setGradea(Integer.valueOf(1));
					} else if (djpdd.getGrade().equals("B")) {
						djpdd.setGradeb(Integer.valueOf(1));
					} else if (djpdd.getGrade().equals("C")) {
						djpdd.setGradec(Integer.valueOf(1));
					} else if (djpdd.getGrade().equals("D")) {
						djpdd.setGraded(Integer.valueOf(1));
					} else if (djpdd.getGrade().equals("E")) {
						djpdd.setGradee(Integer.valueOf(1));
					}
				}
			}

			this.sc.saveList(e);
			this.sb.a(gm002Djpdd.getDtYear(), gm002Djpdd.getDtMonth(), gm002Djpdd.getWeeknumber(),
					gm002Djpdd.getDeptId(), startdt, enddt, this.d(request).getUsername(),
					this.d(request).getRealname());
		} catch (Exception arg9) {
			c.error("获取信息异常!", arg9);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}