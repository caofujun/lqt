package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.SysDictType;
import com.nis.dict.service.SysDictService;
import com.nis.dict.service.SysDictTypeService;
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
public class SysDictTypeController extends BaseController {
	private static final Logger c = Logger.getLogger(SysDictTypeController.class);
	@Autowired
	private SysDictTypeService qA;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/sysDictType/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/sysDictTypeList";
	}

	@RequestMapping({"/sysDictType/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "标准字典--标准字典类型列表")
	public void a(HttpServletRequest request, HttpServletResponse response, SysDictType sysDictType) {
		if (sysDictType.getSearchString() != null && !"".equals(sysDictType.getSearchString())) {
			sysDictType.setSearchString(ab.aR(sysDictType.getSearchString()));
		}

		List page = this.qA.getAll(sysDictType);
		this.b(response, page);
	}

	@RequestMapping({"/sysDictType/f_view/toedit"})
	@SqlLog(p = "标准字典--标准字典类型详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			SysDictType sysDictType = this.qA.get(id);
			modelMap.put("sysDictType", sysDictType);
		}

		return "dict/sysDictTypeEdit";
	}

	@RequestMapping({"/sysDictType/f_json/save"})
	@ResponseBody
	@SqlLog(p = "标准字典--保存标准字典类型")
	public void b(HttpServletRequest request, HttpServletResponse response, SysDictType sysDictType) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isBlank(sysDictType.getId())) {
				this.qA.save(sysDictType);
			} else {
				this.qA.update(sysDictType);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDictType/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "标准字典--删除标准字典类型")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			SysDictType e = this.qA.get(id);
			if (e != null) {
				this.qA.delete(id);
				this.p.deleteByTypeCode(e.getDictTypeCode());
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDictType/f_json/getAll"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, SysDictType sysDictType) {
		this.b(response, this.qA.getAll(sysDictType));
	}
}