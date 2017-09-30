package com.nis.cdc.controller;

import com.nis.cdc.entity.CtgSys012Pathology;
import com.nis.cdc.service.CtgSys012PathologyService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CtgSys012PathologyController extends BaseController {
	@Autowired
	private CtgSys012PathologyService dC;

	@RequestMapping({"/icd03/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/icd03List";
	}

	@RequestMapping({"/icd03/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "ICD0-3字典--ICD字典列表")
	public void n(HttpServletRequest request, HttpServletResponse response, String pathologyno) {
		Result result = new Result();

		try {
			this.dC.delete(pathologyno);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			result.setResult("error");
			result.setMsg(arg5.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/icd03/f_view/toedit"})
	@SqlLog(p = "ICD0-3字典--ICD字典详情")
	public String j(HttpServletRequest request, ModelMap modelMap, String pathologyno) {
		CtgSys012Pathology ctgSys012Pathology = this.dC.get(pathologyno);
		modelMap.put("ICD0", ctgSys012Pathology);
		return "dict/icd03Edit";
	}

	@RequestMapping({"/icd03/f_json/save"})
	@ResponseBody
	@SqlLog(p = "ICD0-3字典--保存ICD字典")
	public void a(HttpServletRequest request, HttpServletResponse response, CtgSys012Pathology ctgSys012Pathology) {
		Result result = new Result();

		try {
			this.dC.save(ctgSys012Pathology);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			result.setResult("error");
			result.setMsg(arg5.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/icd03/f_json/isNoExist"})
	@ResponseBody
	@SqlLog(p = "ICD0-3字典--ICD字典查重")
	public void o(HttpServletRequest request, HttpServletResponse response, String pathologyno) {
		Result result = new Result();

		try {
			boolean e = this.dC.w(pathologyno);
			if (e) {
				result.setResult("error");
				result.setMsg("该编号已存在！");
			} else {
				result.setResult("success");
			}
		} catch (Exception arg5) {
			arg5.printStackTrace();
			result.setResult("error");
			result.setMsg(arg5.getMessage());
		}

		this.a(response, result);
	}
}