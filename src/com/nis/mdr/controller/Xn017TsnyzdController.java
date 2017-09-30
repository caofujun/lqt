package com.nis.mdr.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.entity.Xn017Tsnyzd;
import com.nis.mdr.service.Xn017TsnyzdService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
public class Xn017TsnyzdController extends BaseController {
	private static final Logger c = Logger.getLogger(Xn017TsnyzdController.class);
	@Autowired
	private Xn017TsnyzdService cm;
	@Autowired
	private SysDictService tW;

	@RequestMapping({"/xn017Tsnyzd/f_json/getValue"})
	@ResponseBody
	public void K(HttpServletRequest request, HttpServletResponse response) {
		List list = this.cm.getKvEntity();
		this.b(response, list);
	}

	@RequestMapping({"/xn017Tsnyzd/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "mdr/xn017TsnyzdList";
	}

	@RequestMapping({"/xn017Tsnyzd/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "患者特殊耐药详情")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn017Tsnyzd xn017Tsnyzd) {
		MyPage page = this.cm.a(xn017Tsnyzd);
		this.a(response, page);
	}

	@RequestMapping({"/xn017Tsnyzd/f_view/toedit"})
	@SqlLog(p = "重点菌配置--重点菌规则列表")
	public String a(HttpServletRequest request, ModelMap modelMap, Xn017Tsnyzd xn017Tsnyzd, String dictName,
			String num) {
		if (xn017Tsnyzd != null) {
			try {
				if (ab.isNotEmpty(xn017Tsnyzd.getTestresult())) {
					xn017Tsnyzd.setTestresult(URLDecoder.decode(xn017Tsnyzd.getTestresult(), "utf-8"));
				}

				String xn017Tsnyzd2 = new String(xn017Tsnyzd.getDrugId().getBytes("ISO-8859-1"), "UTF-8");
				xn017Tsnyzd.setDrugId(xn017Tsnyzd2);
			} catch (UnsupportedEncodingException arg6) {
				arg6.printStackTrace();
			}

			Xn017Tsnyzd xn017Tsnyzd21 = this.cm.getByPathogenIdDrugIdSpecDescribe(xn017Tsnyzd);
			modelMap.put("xn017Tsnyzd", xn017Tsnyzd21);
			modelMap.put("specDescribe", xn017Tsnyzd.getSpecDescribe());
			dictName = this.tW.k("spec_describes", xn017Tsnyzd.getSpecDescribe(), (String) null);
			modelMap.put("dictName", dictName);
		}

		return num.equals("1")
				? "mdr/xn017TsnyzdEdit"
				: (num.equals("2") ? "mdr/xn017TsnyzdEdit2" : (num.equals("3") ? "mdr/xn017TsnyzdEdit3" : ""));
	}

	@RequestMapping({"/xn017Tsnyzd/f_json/save"})
	@ResponseBody
	@SqlLog(p = "重点菌配置--保存重点菌规则")
	public void b(HttpServletRequest request, HttpServletResponse response, Xn017Tsnyzd xn017Tsnyzd) {
		Result result = null;
		Xn017Tsnyzd xn017Tsnyzd2 = null;

		try {
			result = new Result();
			if (xn017Tsnyzd.getFlag() == null) {
				xn017Tsnyzd.setFlag(Long.valueOf(1L));
				if (StringUtils.isBlank(xn017Tsnyzd.getPathogenId())) {
					xn017Tsnyzd.setPathogenId("无");
					xn017Tsnyzd.setPathogenName("无");
				}

				if (StringUtils.isBlank(xn017Tsnyzd.getDrugId())) {
					xn017Tsnyzd.setDrugId("无");
					xn017Tsnyzd.setDrugName("无");
				}

				if (xn017Tsnyzd.getTestresult() == null) {
					xn017Tsnyzd.setTestresult("无");
				}

				xn017Tsnyzd2 = this.cm.getByPathogenIdDrugIdSpecDescribe(xn017Tsnyzd);
				if (xn017Tsnyzd2 == null) {
					this.cm.save2(xn017Tsnyzd);
					result.setResult("success");
				} else {
					result = new Result("error", "已存在相同规则！");
				}
			} else {
				xn017Tsnyzd2 = this.cm.getByPathogenIdDrugIdSpecDescribe(xn017Tsnyzd);
				if (xn017Tsnyzd2 == null) {
					this.cm.update(xn017Tsnyzd);
					result.setResult("success");
				} else {
					result = new Result("error", "已存在相同规则！");
				}
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn017Tsnyzd/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "重点菌配置--删除重点菌规则")
	public void c(HttpServletRequest request, HttpServletResponse response, Xn017Tsnyzd xn017Tsnyzd) {
		Result result = null;

		try {
			result = new Result();
			this.cm.delete(xn017Tsnyzd);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}