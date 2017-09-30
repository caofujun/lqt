package com.nis.dict.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
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
public class DataInitController extends BaseController {
	private static final Logger c = Logger.getLogger(DataInitController.class);
	@Autowired
	private SysDictService p;

	@RequestMapping({"/dataInit/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeCode("data_init");
		List list = this.p.getAll(sysDict);
		modelMap.put("sysDictList", list);
		return "dict/dataInit";
	}

	@RequestMapping({"/dataInit/f_view/toEncryptionIndex"})
	public String m(HttpServletRequest request, ModelMap modelMap) {
		return "dict/encryptionIndex";
	}

	@RequestMapping({"/dataInit/f_json/findEncryptionIndex"})
	@ResponseBody
	public void q(HttpServletRequest request, HttpServletResponse response, String str, String type) {
		Result result = new Result();

		try {
			result.setData(this.p.v(str, type));
			result.setResult("success");
		} catch (Exception arg6) {
			if (type.equals("1")) {
				c.error("加密失败!", arg6);
				result = new Result("error", "加密失败!");
			} else {
				c.error("解密失败!", arg6);
				result = new Result("error", "解密失败!");
			}

			arg6.printStackTrace();
		}

		this.b(response, result);
	}
}