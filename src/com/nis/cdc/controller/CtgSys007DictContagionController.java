package com.nis.cdc.controller;

import com.nis.cdc.entity.CtgSys007Dictcontagion;
import com.nis.cdc.service.CtgSys007DictcontagionService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
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
public class CtgSys007DictContagionController extends BaseController {
	private static final Logger c = Logger.getLogger(CtgSys007DictContagionController.class);
	@Autowired
	private CtgSys007DictcontagionService bL;

	@RequestMapping({"/cdc/f_view/diseaseMaintain"})
	public String i(HttpServletRequest request, ModelMap modelMap) {
		return "diseaseMaintain";
	}

	@RequestMapping({"/cdc/f_json/diseaseMaintainQuery"})
	@ResponseBody
	@SqlLog(p = "传染病字典--字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response,
			CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		if (ctgSys007Dictcontagion.getSearchString() != null && !"".equals(ctgSys007Dictcontagion.getSearchString())) {
			ctgSys007Dictcontagion.setSearchString(ab.aR(ctgSys007Dictcontagion.getSearchString()));
		}

		List pageQuery = this.bL.a(ctgSys007Dictcontagion);
		this.b(response, pageQuery);
	}

	@RequestMapping({"/cdc/f_view/diseaseEdit"})
	@SqlLog(p = "传染病字典--字典详情")
	public String i(HttpServletRequest request, ModelMap modelMap, String diseaseid) {
		if (ab.isNotEmpty(diseaseid)) {
			CtgSys007Dictcontagion diseaseParent = this.bL.get(diseaseid);
			modelMap.put("CSDC", diseaseParent);
		} else {
			Integer diseaseParent1 = this.bL.getAvailableDiseaseId();
			modelMap.put("MaxDid", diseaseParent1);
		}

		List diseaseParent2 = this.bL.getDiseaseParent();
		modelMap.put("DPs", diseaseParent2);
		List classify = this.bL.getClassify();
		modelMap.put("classify", classify);
		return "cdc/diseaseEdit";
	}

	@RequestMapping({"/cdc/f_json/saveDisease"})
	@ResponseBody
	@SqlLog(p = "传染病字典--保存字典")
	public void a(HttpServletRequest request, HttpServletResponse response,
			CtgSys007Dictcontagion ctgSys007Dictcontagion, String isadd) {
		Result result = new Result();

		try {
			if (ab.isNotEmpty(isadd)) {
				this.bL.save(ctgSys007Dictcontagion);
			} else {
				this.bL.update(ctgSys007Dictcontagion);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			arg6.printStackTrace();
			result.setResult("error");
			result.setMsg(arg6.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/deleteDisease"})
	@ResponseBody
	@SqlLog(p = "传染病字典--删除字典")
	public void p(HttpServletRequest request, HttpServletResponse response, String diseaseid, String delByParent) {
		Result result = new Result();

		try {
			if (ab.isNotEmpty(diseaseid)) {
				this.bL.delete(diseaseid);
			}

			if ("1".equals(delByParent)) {
				this.bL.delByParentId(diseaseid);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			arg6.printStackTrace();
			result.setResult("error");
			result.setMsg(arg6.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/changeStatus"})
	@ResponseBody
	@SqlLog(p = "传染病字典--更新字典状态")
	public void a(HttpServletRequest request, HttpServletResponse response, String diseaseid, String type, Long value) {
		Result result = new Result();

		try {
			if (ab.isNotEmpty(diseaseid)) {
				CtgSys007Dictcontagion e = new CtgSys007Dictcontagion();
				e.setDiseaseid(diseaseid);
				if ("1".equals(type)) {
					e.setIsintestinal(value);
					this.bL.updateStatus(e);
				} else if ("2".equals(type)) {
					e.setIsrespiratory(value);
					this.bL.updateStatus(e);
				} else if ("3".equals(type)) {
					e.setIsnatural(value);
					this.bL.updateStatus(e);
				} else if ("4".equals(type)) {
					e.setIshemic(value);
					this.bL.updateStatus(e);
				} else if ("5".equals(type)) {
					e.setIssexspread(value);
					this.bL.updateStatus(e);
				} else if ("6".equals(type)) {
					e.setSexcard(value);
					this.bL.updateStatus(e);
				}

				result.setResult("success");
			}
		} catch (Exception arg7) {
			arg7.printStackTrace();
			result.setResult("error");
			result.setMsg("参数无效");
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/diseaseMaintainQuery4Tree"})
	@ResponseBody
	@SqlLog(p = "传染病字典--字典树")
	public void b(HttpServletRequest request, HttpServletResponse response,
			CtgSys007Dictcontagion ctgSys007Dictcontagion) {
		if (ctgSys007Dictcontagion.getSearchString() != null && !"".equals(ctgSys007Dictcontagion.getSearchString())) {
			ctgSys007Dictcontagion.setSearchString(ab.aR(ctgSys007Dictcontagion.getSearchString()));
		}

		List diseaseTree = this.bL.b(ctgSys007Dictcontagion);
		this.b(response, diseaseTree);
	}
}