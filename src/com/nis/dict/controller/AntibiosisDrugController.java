package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.AntibiosisDrug;
import com.nis.dict.service.AntibiosisDrugService;
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
public class AntibiosisDrugController extends BaseController {
	private static final Logger c = Logger.getLogger(AntibiosisDrugController.class);
	@Autowired
	private AntibiosisDrugService qv;

	@RequestMapping({"/antibiosisDrug/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/antibiosisDrugLists";
	}

	@RequestMapping({"/antibiosisDrug/f_view/antibiosisDrugList"})
	public String k(HttpServletRequest request, ModelMap modelMap) {
		return "dict/antibiosisDrugList";
	}

	@RequestMapping({"/antibiosisDrug/f_view/yjDrugList"})
	public String l(HttpServletRequest request, ModelMap modelMap) {
		return "dict/yjDrugList";
	}

	@RequestMapping({"/antibiosisDrug/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size) {
		AntibiosisDrug antibiosisDrug = new AntibiosisDrug();
		antibiosisDrug.setSearchString(ab.aR(q));
		antibiosisDrug.setDrugTypeid(drugTypeid);
		antibiosisDrug.setPage(Integer.valueOf(page));
		antibiosisDrug.setSize(Integer.valueOf(size));
		MyPage drugPage = this.qv.a(antibiosisDrug);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/antibiosisDrug/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "标准抗菌药物管理--抗菌药物列表")
	public void a(HttpServletRequest request, HttpServletResponse response, AntibiosisDrug antibiosisDrug) {
		if (antibiosisDrug.getSearchString() != null && !"".equals(antibiosisDrug.getSearchString())) {
			antibiosisDrug.setSearchString(ab.aR(antibiosisDrug.getSearchString()));
		}

		MyPage page = this.qv.a(antibiosisDrug);
		this.b(response, page);
	}

	@RequestMapping({"/antibiosisDrug/f_json/findKjywyl"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, AntibiosisDrug antibiosisDrug) {
		List list = this.qv.findKjywyl(antibiosisDrug);
		this.b(response, list);
	}

	@RequestMapping({"/antibiosisDrug/f_view/toedit"})
	@SqlLog(p = "标准抗菌药物管理--抗菌药物详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			AntibiosisDrug antibiosisDrug = this.qv.get(id);
			modelMap.put("antibiosisDrug", antibiosisDrug);
		}

		return "dict/antibiosisDrugEdit";
	}

	@RequestMapping({"/antibiosisDrug/f_json/save"})
	@ResponseBody
	@SqlLog(p = "标准抗菌药物管理--保存抗菌药物")
	public void c(HttpServletRequest request, HttpServletResponse response, AntibiosisDrug antibiosisDrug) {
		Result result = null;

		try {
			result = new Result();
			if (antibiosisDrug.getDrugId() != null) {
				antibiosisDrug.setDrugId(antibiosisDrug.getDrugId().trim());
			}

			if (this.qv.get(antibiosisDrug.getDrugId()) == null) {
				this.qv.save(antibiosisDrug);
			} else {
				this.qv.update(antibiosisDrug);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/antibiosisDrug/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "标准抗菌药物管理--删除抗菌药物")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.qv.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}