package com.nis.hygiene.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw201Jcdmb;
import com.nis.hygiene.service.Hw101JcdjService;
import com.nis.hygiene.service.Hw201JcdmbService;
import java.util.Date;
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
public class Hw201JcdmbController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw201JcdmbController.class);
	@Autowired
	private Hw201JcdmbService rH;
	@Autowired
	private Hw101JcdjService rD;

	@RequestMapping({"/hw201Jcdmb/f_view/toAddByTemplet"})
	public String a(HttpServletRequest request, ModelMap modelMap, Integer type) {
		Date date = new Date();
		Hw101Jcdj hw101Jcdj = new Hw101Jcdj();
		hw101Jcdj.setDjId(f.c(date, "yyyyMMddHHmmss"));
		LoginUser user = this.d(request);
		hw101Jcdj.setDeptId(user.getDepNo());
		hw101Jcdj.setReportBy(user.getUsername());
		hw101Jcdj.setReportAt(date);
		modelMap.put("hw101Jcdj", hw101Jcdj);
		modelMap.put("type", type);
		return "hygiene/addByTemplet";
	}

	@RequestMapping({"/hw201Jcdmb/f_json/addByTemplet"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Hw101Jcdj hw101Jcdj, Hw201Jcdmb hw201Jcdmb,
			String templetDeptId) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			hw201Jcdmb.setDeptId(e.getDepNo());
			hw201Jcdmb.setUserId(e.getUsername());
			hw201Jcdmb.setDjDeptId(templetDeptId);
			this.rH.a(hw101Jcdj, hw201Jcdmb, e);
			result.setResult("success");
			result.setData(hw101Jcdj.getDjId());
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw201Jcdmb/f_json/findTempletList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Hw201Jcdmb hw201Jcdmb) {
		AcAccount account = (AcAccount) this.b(request);
		hw201Jcdmb.setDeptId(account.getDepNo());
		hw201Jcdmb.setUserId(account.getUsername());
		List list = this.rH.findTempletList(hw201Jcdmb);
		this.a(response, list);
	}

	@RequestMapping({"/hw201Jcdmb/f_json/findSampleList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Hw201Jcdmb hw201Jcdmb) {
		AcAccount account = (AcAccount) this.b(request);
		hw201Jcdmb.setDeptId(account.getDepNo());
		hw201Jcdmb.setUserId(account.getUsername());
		List list = this.rH.findSampleList(hw201Jcdmb);
		this.a(response, list);
	}

	@RequestMapping({"/hw201Jcdmb/f_json/delTemplet"})
	@ResponseBody
	public void F(HttpServletRequest request, HttpServletResponse response, String templetIdStr) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(templetIdStr)) {
				String[] e = templetIdStr.split(",");
				if (e.length > 0) {
					this.rH.i(e);
					result.setResult("success");
				}
			}

			if (!"success".equals(result.getResult())) {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw201Jcdmb/f_view/toStoSaveToTemplet"})
	public String a(HttpServletRequest request, ModelMap modelMap, Hw201Jcdmb hw201Jcdmb) {
		Hw101Jcdj hw101Jcdj = this.rD.get(hw201Jcdmb.getDjId());
		modelMap.put("hw101Jcdj", hw101Jcdj);
		return "hygiene/saveToTemplet";
	}

	@RequestMapping({"/hw201Jcdmb/f_json/findByTempletName"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Hw201Jcdmb hw201Jcdmb) {
		Result result = null;

		try {
			result = new Result();
			int e = this.rH.findByTempletName(hw201Jcdmb);
			result.setResult("success");
			result.setData(String.valueOf(e));
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw201Jcdmb/f_json/saveToTemplet"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, Hw201Jcdmb hw201Jcdmb) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw201Jcdmb.getDjId()) && StringUtils.isNotBlank(hw201Jcdmb.getDeptId())
					&& StringUtils.isNotBlank(hw201Jcdmb.getTempletName())) {
				this.rH.b(hw201Jcdmb);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}