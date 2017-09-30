package com.nis.bl.controller;

import com.nis.access.entity.AcAccount;
import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.service.Bl004CsDetailinfoService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
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
public class Bl004CsDetailinfoController extends BaseController {
	private static final Logger c = Logger.getLogger(Bl004CsDetailinfoController.class);
	@Autowired
	private Bl004CsDetailinfoService cW;

	@RequestMapping({"/bl004CsDetailinfo/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "bl/bl004CsDetailinfoList";
	}

	@RequestMapping({"/bl004CsDetailinfo/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String csmId, String q, int page,
			int size) {
		Bl004CsDetailinfo bl004CsDetailinfo = new Bl004CsDetailinfo();
		bl004CsDetailinfo.setSearchString(q);
		bl004CsDetailinfo.setCsmId(csmId);
		bl004CsDetailinfo.setPage(Integer.valueOf(page));
		bl004CsDetailinfo.setSize(Integer.valueOf(size));
		MyPage drugPage = this.cW.a(bl004CsDetailinfo);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/bl004CsDetailinfo/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "职业暴露字典--字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Bl004CsDetailinfo bl004CsDetailinfo) {
		if (bl004CsDetailinfo.getSearchString() != null && !"".equals(bl004CsDetailinfo.getSearchString())) {
			bl004CsDetailinfo.setSearchString(ab.aR(bl004CsDetailinfo.getSearchString()));
		}

		MyPage page = this.cW.a(bl004CsDetailinfo);
		this.b(response, page);
	}

	@RequestMapping({"/bl004CsDetailinfo/f_view/toedit"})
	@SqlLog(p = "职业暴露字典--字典详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String csmId, String csdId) {
		if (StringUtils.isNotBlank(csmId) && StringUtils.isNotBlank(csdId)) {
			Bl004CsDetailinfo bl004CsDetailinfo = this.cW.get(csmId, csdId);
			modelMap.put("bl004CsDetailinfo", bl004CsDetailinfo);
		}

		return "bl/bl004CsDetailinfoEdit";
	}

	@RequestMapping({"/bl004CsDetailinfo/f_json/save"})
	@ResponseBody
	@SqlLog(p = "职业暴露字典--保存字典")
	public void b(HttpServletRequest request, HttpServletResponse response, Bl004CsDetailinfo bl004CsDetailinfo) {
		Result result = null;

		try {
			result = new Result();
			if (bl004CsDetailinfo.getFlag() == null) {
				bl004CsDetailinfo.setFlag(Long.valueOf(0L));
			}

			AcAccount e = (AcAccount) this.b(request);
			bl004CsDetailinfo.setCreateMen(e.getUsername());
			if (bl004CsDetailinfo.getCsdId() != null) {
				bl004CsDetailinfo.setCsdId(bl004CsDetailinfo.getCsdId().trim());
			}

			if (this.cW.get(bl004CsDetailinfo.getCsmId(), bl004CsDetailinfo.getCsdId()) == null) {
				this.cW.save(bl004CsDetailinfo);
			} else {
				this.cW.update(bl004CsDetailinfo);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl004CsDetailinfo/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "职业暴露字典--删除字典")
	public void f(HttpServletRequest request, HttpServletResponse response, String csmId, String csdId) {
		Result result = null;

		try {
			result = new Result();
			this.cW.delete(csmId, csdId);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl004CsDetailinfo/f_json/findBycsmId"})
	@ResponseBody
	@SqlLog(p = "职业暴露--职业暴露详情")
	public void c(HttpServletRequest request, HttpServletResponse response, String blId, String itemNames,
			String date) {
		List page = this.cW.b(blId, itemNames, date);
		this.b(response, page);
	}
}