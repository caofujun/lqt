package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.h;
import com.nis.comm.enums.n;
import com.nis.comm.enums.p;
import com.nis.comm.utils.ab;
import com.nis.dict.dao.SysDictDao;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.service.Xn017TsnyzdService;
import java.util.ArrayList;
import java.util.Arrays;
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

@Controller
public class SysDictController extends BaseController {
	private static final Logger c = Logger.getLogger(SysDictController.class);
	@Autowired
	private SysDictService p;
	@Autowired
	private SysDictDao qu;
	@Autowired
	private Xn017TsnyzdService cm;

	@RequestMapping({"/sysDict/f_json/getValue"})
	@ResponseBody
	public void r(HttpServletRequest request, HttpServletResponse response, String unitId, String dictTypeCode) {
		List list = this.p.u(dictTypeCode, unitId);
		this.b(response, list);
	}

	@RequestMapping({"/sysDict/f_json/findTop"})
	@ResponseBody
	public void s(HttpServletRequest request, HttpServletResponse response, String unitId, String dictTypeCode) {
		List list = this.p.findTop(dictTypeCode, unitId);
		this.b(response, list);
	}

	@RequestMapping({"/sysDict/f_json/getByParentDictCode"})
	@ResponseBody
	public void t(HttpServletRequest request, HttpServletResponse response, String dictTypeCode, String dictCode) {
		List list = this.p.m(dictTypeCode, dictCode, (String) null);
		this.b(response, list);
	}

	@RequestMapping({"/sysDict/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String code) {
		modelMap.put("code", code);
		return "dict/sysDictList";
	}

	@RequestMapping({"/sysDict/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "标准字典--标准字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response, SysDict sysDict) {
		if (sysDict.getSearchString() != null && !"".equals(sysDict.getSearchString())) {
			sysDict.setSearchString(ab.aR(sysDict.getSearchString()));
		}

		MyPage page = this.p.a(sysDict);
		this.b(response, page);
	}

	@RequestMapping({"/sysDict/f_json/getAllSpecDescribes"})
	@ResponseBody
	@SqlLog(p = "标准字典--重点菌字典列表")
	public void b(HttpServletRequest request, HttpServletResponse response, SysDict sysDict) {
		List page = this.p.getAllSpecDescribes(sysDict);
		this.b(response, page);
	}

	@RequestMapping({"/sysDict/f_view/toeditSpecDescribes"})
	public String i(HttpServletRequest request, ModelMap modelMap, String id, String dictTypeCode) {
		SysDict sysDict;
		if (StringUtils.isNotBlank(id)) {
			sysDict = this.p.get(id);
			if (n.gm.getValue().toString().equals(sysDict.getScopeLevel())) {
				sysDict.setScopeLevel((String) null);
			}

			modelMap.put("sysDict", sysDict);
		} else {
			sysDict = new SysDict();
			sysDict.setDictTypeCode(dictTypeCode);
			int num = this.qu.getMaxSpecDescribesNum();
			sysDict.setSequenceNumber(Long.valueOf((long) num + 1L));
			modelMap.put("sysDict", sysDict);
		}

		return "mdr/specDescribesEdit";
	}

	@RequestMapping({"/sysDict/f_json/deleteSpecDescribes"})
	@ResponseBody
	@SqlLog(p = "标准字典--删除重点菌字典")
	public void u(HttpServletRequest request, HttpServletResponse response, String id, String specDescribe) {
		Result result = null;

		try {
			result = new Result();
			this.p.delete(id);
			this.cm.deleteBySpecDescribe(specDescribe);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDict/f_json/queryList"})
	public void a(HttpServletRequest request, HttpServletResponse response, SysDict sysDict, String q) {
		sysDict.setUnitId(this.c(request));
		if (q != null && !"".equals(q)) {
			sysDict.setSearchString(ab.aR(q));
		}

		List list = this.p.getAll(sysDict);
		this.b(response, list);
	}

	@RequestMapping({"/sysDict/f_view/toedit"})
	@SqlLog(p = "标准字典--标准字典项详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String id, String dictTypeCode) {
		SysDict sysDict;
		if (StringUtils.isNotBlank(id)) {
			sysDict = this.p.get(id);
			if (n.gm.getValue().toString().equals(sysDict.getScopeLevel())) {
				sysDict.setScopeLevel((String) null);
			}

			modelMap.put("sysDict", sysDict);
		} else {
			sysDict = new SysDict();
			sysDict.setDictTypeCode(dictTypeCode);
			modelMap.put("sysDict", sysDict);
		}

		return "dict/sysDictEdit";
	}

	@RequestMapping({"/sysDict/f_json/updateSort"})
	@ResponseBody
	@SqlLog(p = "标准字典--更新标准字典项顺序")
	public void s(HttpServletRequest request, HttpServletResponse response, String params) {
		Result result = null;

		try {
			result = new Result();
			String[] e = params.split("#");
			ArrayList sdlist = new ArrayList();
			String[] arg9 = e;
			int arg8 = e.length;

			for (int arg7 = 0; arg7 < arg8; ++arg7) {
				String s = arg9[arg7];
				if (ab.isNotEmpty(s)) {
					String[] sdArr = s.split(",");
					SysDict sd = new SysDict();
					sd.setId(sdArr[0]);
					sd.setSequenceNumber(new Long(sdArr[1]));
					sdlist.add(sd);
				}
			}

			this.p.z(sdlist);
			result.setResult("success");
		} catch (Exception arg12) {
			c.error("获取信息异常!", arg12);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDict/f_json/start"})
	@ResponseBody
	@SqlLog(p = "标准字典--启用标准字典项")
	public void c(HttpServletRequest request, HttpServletResponse response, SysDict sysDict) {
		Result result = null;

		try {
			result = new Result();
			sysDict = this.p.get(sysDict.getId());
			sysDict.setDictStatus(p.gy.getValue());
			this.p.update(sysDict);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDict/f_view/toselect"})
	public String a(HttpServletRequest request, ModelMap modelMap, SysDict sysDict) {
		modelMap.put("sysDict", sysDict);
		return "dict/sysDictListSelect";
	}

	@RequestMapping({"/sysDict/f_json/findList"})
	public void b(HttpServletRequest request, HttpServletResponse response, SysDict sysDict, String checkeds) {
		sysDict.setUnitId(this.c(request));
		List list = this.p.getAll(sysDict);
		if (StringUtils.isNotBlank(checkeds) && list.size() > 0) {
			String[] dictCodes = checkeds.split(",");
			if (dictCodes != null && dictCodes.length > 0) {
				List codeList = Arrays.asList(dictCodes);
				Iterator arg8 = list.iterator();

				while (arg8.hasNext()) {
					SysDict dict = (SysDict) arg8.next();
					dict.setChecked(codeList.contains(dict.getDictCode()));
				}
			}
		}

		this.b(response, list);
	}

	@RequestMapping({"/sysDict/f_json/stop"})
	@ResponseBody
	@SqlLog(p = "标准字典--停用标准字典项")
	public void d(HttpServletRequest request, HttpServletResponse response, SysDict sysDict) {
		Result result = null;

		try {
			result = new Result();
			sysDict = this.p.get(sysDict.getId());
			sysDict.setDictStatus(p.gz.getValue());
			this.p.update(sysDict);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDict/f_json/save"})
	@ResponseBody
	@SqlLog(p = "标准字典--保存标准字典项")
	public void e(HttpServletRequest request, HttpServletResponse response, SysDict sysDict) {
		Result result = null;

		try {
			LoginUser e = this.d(request);
			result = new Result();
			if (ab.isEmpty(sysDict.getScopeLevel())) {
				sysDict.setScopeLevel(n.gm.getValue().toString());
				sysDict.setUnitId("0");
			} else if (sysDict.getScopeLevel().equals(n.gi.getValue().toString())) {
				sysDict.setUnitId(e.getUnitId());
				sysDict.setScopeLevel(n.gi.getValue().toString());
			} else {
				sysDict.setUnitId(sysDict.getUnitId());
				sysDict.setScopeLevel(n.gk.getValue().toString());
			}

			sysDict.setDictStatus(p.gy.getValue());
			if (StringUtils.isBlank(sysDict.getId())) {
				this.p.save(sysDict);
			} else {
				this.p.update(sysDict);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDict/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "标准字典--删除标准字典项")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.p.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysDict/f_view/getParent"})
	public void t(HttpServletRequest request, HttpServletResponse response, String code) {
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeCode(code);
		List page = this.p.getAll(sysDict);
		this.b(response, page);
	}

	@RequestMapping({"/sysDict/f_view/getDict"})
	public void k(HttpServletRequest request, HttpServletResponse response, String q, String dictTypeCode,
			String allowSetValue) {
		SysDict sysDict = new SysDict();
		sysDict.setUnitId(this.c(request));
		sysDict.setDictTypeCode(dictTypeCode);
		sysDict.setSearchString(q);
		List list = this.p.getAll(sysDict);
		ArrayList items = new ArrayList();
		if (h.fP.getCode().toString().equals(allowSetValue) && ab.isNotEmpty(q)) {
			items.add(new KvEntity(q, q));
		}

		Iterator arg9 = list.iterator();

		while (arg9.hasNext()) {
			SysDict dict = (SysDict) arg9.next();
			items.add(new KvEntity(dict.getDictCode(), dict.getDictName()));
		}

		this.b(response, items);
	}
}