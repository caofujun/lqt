package com.nis.organization.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ae;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.zg.entity.Zg031Sqks;
import com.nis.zg.service.Zg031SqksService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DepController extends BaseController {
	private static final Logger c = Logger.getLogger(DepController.class);
	@Autowired
	private DepService e;
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg031SqksService dF;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysLogService aV;
	@Autowired
	private UnitService se;

	@RequestMapping({"/dep/json/query"})
	@ResponseBody
	public void r(HttpServletRequest request, HttpServletResponse response, String unitId, String name, String q) {
		if (StringUtils.isBlank(unitId)) {
			AcAccount list = (AcAccount) this.b(request);
			unitId = list.getUnitId();
		}

		if (StringUtils.isNotBlank(q)) {
			name = ab.aR(q);
		}

		List list1 = this.e.findLike(unitId, name);
		ArrayList kvList = new ArrayList();
		Iterator arg8 = list1.iterator();

		while (arg8.hasNext()) {
			Dep dep = (Dep) arg8.next();
			String showDepName = dep.getDeptName();
			kvList.add(new KvEntity(dep.getDeptId(), showDepName));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/dep/json/queryList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId,
			String dataType, Long ifcaseoffice, Long ifenvoffice, Long ificu, Long ifchildoffice, Long flag,
			Integer isCaseOrEnvo, Long iffocus, Long ifmzoffice, String chargeManId, String q, int page, int size) {
		Dep dep = new Dep();
		if (StringUtils.isNotBlank(q)) {
			dep.setSearchString(ab.aR(q));
		}

		dep.setHospId(hospId);
		dep.setDeptId(deptId);
		dep.setIficu(ificu);
		dep.setIfchildoffice(ifchildoffice);
		dep.setIfcaseoffice(ifcaseoffice);
		dep.setIfenvoffice(ifenvoffice);
		dep.setIsCaseOrEnvo(isCaseOrEnvo);
		dep.setIffocus(iffocus);
		if (ifmzoffice != null && ifmzoffice.intValue() == 1) {
			dep.setIfmzoffice(ifmzoffice);
		}

		dep.setFlag(flag);
		dep.setChargeManId(chargeManId);
		if ("1".equals(dataType)) {
			dep.setIfcaseoffice(new Long(1L));
		}

		if ("mz".equals(dataType)) {
			dep.setDeptTypeId("2");
			dep.setFlag(new Long(1L));
		}

		if ("zy".equals(dataType)) {
			dep.setDeptTypeId("1");
			dep.setFlag(new Long(1L));
		}

		dep.setPage(Integer.valueOf(page));
		dep.setSize(Integer.valueOf(size));
		MyPage depPage = this.e.b(dep);
		this.b(response, depPage.getRows());
	}

	@RequestMapping({"/dep/json/queryByDepType"})
	@ResponseBody
	public void aj(HttpServletRequest request, HttpServletResponse response, String depType) {
		AcAccount ac = (AcAccount) this.b(request);
		Dep depSearch = new Dep();
		depSearch.setHospId(ac.getUnitId());
		depSearch.setDeptTypeId(depType);
		List list = this.e.d(depSearch);
		ArrayList kvList = new ArrayList();
		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			Dep dep = (Dep) arg8.next();
			kvList.add(new KvEntity(dep.getDeptId(), dep.getDeptName()));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/dep/json/queryDepIdByDepType"})
	@ResponseBody
	public void ak(HttpServletRequest request, HttpServletResponse response, String depType) {
		AcAccount ac = (AcAccount) this.b(request);
		Dep depSearch = new Dep();
		depSearch.setHospId(ac.getUnitId());
		depSearch.setDeptTypeId(depType);
		List list = this.e.d(depSearch);
		ArrayList kvList = new ArrayList();
		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			Dep dep = (Dep) arg8.next();
			kvList.add(new KvEntity(dep.getDeptId().toString(), dep.getDeptName()));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/dep/json/queryByDepTypeEasyUi"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String depType, String allDepName,
			Boolean isScope) throws UnsupportedEncodingException {
		AcAccount ac = (AcAccount) this.b(request);
		List list = Collections.emptyList();
		if (isScope.booleanValue()) {
			String kvList = "";
			if (n.gj.getValue() == ac.getDataScope()) {
				kvList = ab.aV(ac.getScopeInfo());
			} else if (n.gi.getValue() == ac.getDataScope() || n.gh.getValue() == ac.getDataScope()) {
				kvList = ab.aV(ac.getDepNo());
			}

			Dep kvEntity = new Dep();
			kvEntity.setHospId(ac.getUnitId());
			list = this.e.getDepList(kvEntity);
		} else {
			Dep kvList1 = new Dep();
			kvList1.setHospId(ac.getUnitId());
			kvList1.setDeptTypeId(depType);
			list = this.e.d(kvList1);
		}

		ArrayList kvList2 = new ArrayList();
		KvEntity kvEntity1 = new KvEntity("alldep_nos",
				ab.isEmpty(allDepName) ? "全部科室" : URLDecoder.decode(allDepName, "utf-8"));
		kvEntity1.setSelected(true);
		kvList2.add(kvEntity1);
		Iterator arg10 = list.iterator();

		while (arg10.hasNext()) {
			Dep dep = (Dep) arg10.next();
			String depTypeName = this.p.k("dep_type", dep.getDeptTypeId(), this.c(request));
			kvList2.add(new KvEntity(dep.getDeptId(), dep.getDeptName() + "(" + depTypeName + ")"));
		}

		this.b(response, kvList2);
	}

	@RequestMapping({"/dep/json/queryByDepTypeEasyUiExt"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String depTypeId, String allDepName,
			Boolean isScope, String selected) throws UnsupportedEncodingException {
		AcAccount ac = (AcAccount) this.b(request);
		List list = Collections.emptyList();
		if (isScope.booleanValue()) {
			String kvList = "";
			if (n.gj.getValue() == ac.getDataScope()) {
				kvList = ab.aV(ac.getScopeInfo());
			} else if (n.gi.getValue() == ac.getDataScope() || n.gh.getValue() == ac.getDataScope()) {
				kvList = ab.aV(ac.getDepNo());
			}

			Dep kvEntity = new Dep();
			kvEntity.setDeptTypeId(depTypeId);
			kvEntity.setHospId(ac.getUnitId());
			list = this.e.getDepList(kvEntity);
		} else {
			list = this.e.getDepByUnitIdAndDepTypeExt(ac.getUnitId(), depTypeId, (String) null, Long.valueOf(0L));
		}

		ArrayList kvList1 = new ArrayList();
		KvEntity kvEntity1 = new KvEntity("alldep_nos",
				ab.isEmpty(allDepName) ? "全部科室" : URLDecoder.decode(allDepName, "utf-8"));
		boolean isSelected = false;
		kvList1.add(kvEntity1);

		KvEntity kv;
		for (Iterator arg12 = list.iterator(); arg12.hasNext(); kvList1.add(kv)) {
			Dep dep = (Dep) arg12.next();
			String depTypeName = this.p.k("dep_type", dep.getDeptTypeId(), this.c(request));
			kv = new KvEntity(dep.getDeptId(), dep.getDeptName() + "(" + depTypeName + ")");
			if (selected != null && selected.equals(dep.getDeptId())) {
				kv.setSelected(true);
				isSelected = true;
			}
		}

		if (!isSelected) {
			kvEntity1.setSelected(true);
		}

		this.b(response, kvList1);
	}

	@RequestMapping({"/dep/json/excuteIcu"})
	@ResponseBody
	public void M(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		String yzName = this.j.findByParamCode(Param.NIS_ICU_YZ);
		String deptIds = this.j.findByParamCode(Param.NIS_ICU_YZ_DEP);
		String icuDeptId = this.j.findByParamCode(Param.NIS_ICU_YZ_ICUDEP);
		if (!"0".equals(icuDeptId)) {
			String[] depId = deptIds.split(",");
			Dep icuDep = this.e.get(icuDeptId);
			String day = this.j.findByParamCode(Param.NIS_ICU_DAY);

			try {
				result = this.e.a(yzName, icuDep.getDeptId(), icuDep.getDeptName(), day, depId);
				List e = this.e.getICUList(yzName, day);
				if (e.size() > 0) {
					Iterator arg11 = e.iterator();

					while (arg11.hasNext()) {
						Map map = (Map) arg11.next();
						Timestamp orderAt = f.k(map.get("ORDERAT").toString(), "yyyy-MM-dd HH:mm:ss");
						String zyid = map.get("ZYID").toString();
						this.e.updateYzxxb(icuDep.getDeptId(), icuDep.getDeptName(), zyid, orderAt);
						this.e.updateSjbb(icuDep.getDeptId(), icuDep.getDeptName(), zyid, orderAt);
						this.e.updateSsxxb(icuDep.getDeptId(), icuDep.getDeptName(), zyid, orderAt);
						this.e.updateZkjl(icuDep.getDeptId(), icuDep.getDeptName(), zyid, orderAt);
					}
				}

				c.info("修改正常!【结果】success");
			} catch (Exception arg14) {
				c.error("获取信息异常!", arg14);
				this.aV.a(ah.iG, "医嘱修改当前科室异常{excuteNicuAndCcu.excuteNicuAndCcu()}", "", arg14.getMessage());
				result = new Result("error", "获取信息异常");
			}
		}

		this.a(response, result);
	}

	@RequestMapping({"/dep/json/excuteNicuAndCcu"})
	@ResponseBody
	public void N(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		String yzName = this.j.findByParamCode(Param.NIS_ICU_YZ);
		String ccuId = this.j.findByParamCode(Param.NIS_CCU_DEPTID);
		String ccuName = this.j.findByParamCode(Param.NIS_CCU_DEPTNAME);
		String xccuId = this.j.findByParamCode(Param.NIS_XCCU_DEPTID);
		String xccuName = this.j.findByParamCode(Param.NIS_XCCU_DEPTNAME);
		String xsrId = this.j.findByParamCode(Param.NIS_NICU_DEPTID);
		String xsrName = this.j.findByParamCode(Param.NIS_NICU_DEPTNAME);
		String day = this.j.findByParamCode(Param.NIS_ICU_DAY);

		try {
			this.e.l(yzName, ccuId, ccuName, day);
			this.e.n(yzName, xccuId, xccuName, day);
			List e = this.e.getICUList(yzName, day);
			List xsrMap = this.e.getXsrList(day);
			Map map;
			Iterator arg14;
			if (xsrMap.size() > 0) {
				arg14 = xsrMap.iterator();

				while (arg14.hasNext()) {
					map = (Map) arg14.next();
					String orderAt = map.get("ZYID").toString();
					Timestamp zyid = f.k(map.get("INHOSPAT").toString(), "yyyy-MM-dd HH:mm:ss");
					this.e.updateYzxxb(xsrId, xsrName, orderAt, zyid);
					this.e.updateSjbb(xsrId, xsrName, orderAt, zyid);
					this.e.updateSsxxb(xsrId, xsrName, orderAt, zyid);
					this.e.updateZkjl(xsrId, xsrName, orderAt, zyid);
				}
			}

			result = this.e.m(yzName, xsrId, xsrName, day);
			if (e.size() > 0) {
				arg14 = e.iterator();

				label41 : while (true) {
					String deptId;
					Timestamp orderAt1;
					String zyid1;
					do {
						while (true) {
							if (!arg14.hasNext()) {
								break label41;
							}

							map = (Map) arg14.next();
							orderAt1 = f.k(map.get("ORDERAT").toString(), "yyyy-MM-dd HH:mm:ss");
							zyid1 = map.get("ZYID").toString();
							deptId = map.get("EXECOFFICECODE").toString();
							if (!"102".equals(deptId) && !ccuId.equals(deptId)) {
								break;
							}

							this.e.updateYzxxb(ccuId, ccuName, zyid1, orderAt1);
							this.e.updateSjbb(ccuId, ccuName, zyid1, orderAt1);
							this.e.updateSsxxb(ccuId, ccuName, zyid1, orderAt1);
							this.e.updateZkjl(ccuId, ccuName, zyid1, orderAt1);
						}
					} while (!"136".equals(deptId) && !xccuId.equals(deptId));

					this.e.updateYzxxb(xccuId, xccuName, zyid1, orderAt1);
					this.e.updateSjbb(xccuId, xccuName, zyid1, orderAt1);
					this.e.updateSsxxb(xccuId, xccuName, zyid1, orderAt1);
					this.e.updateZkjl(xccuId, xccuName, zyid1, orderAt1);
				}
			}

			c.info("修改正常!【结果】success");
		} catch (Exception arg18) {
			c.error("获取信息异常!", arg18);
			this.aV.a(ah.iG, "医嘱修改当前科室异常{excuteNicuAndCcu.excuteNicuAndCcu()}", "", arg18.getMessage());
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/dep/json/excuteNicuAndCcuBack"})
	@ResponseBody
	public void O(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		List depMap = null;
		String yzName = this.j.findByParamCode(Param.NIS_ICU_YZ);
		String ccuId = this.j.findByParamCode(Param.NIS_CCU_DEPTID);
		String day = this.j.findByParamCode(Param.NIS_ICU_DAY);
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			depMap = this.e.getNicuListBack(yzName, day);
			if (depMap.size() > 0) {
				String orderAt;
				String zyid;
				for (Iterator arg9 = depMap.iterator(); arg9
						.hasNext(); result = this.e.g(orderAt, zyid, ccuId, day, yzName)) {
					Map e = (Map) arg9.next();
					orderAt = f.formatDate((String) e.get("ORDERAT"), "yyyy-MM-dd HH:mm:ss");
					zyid = (String) e.get("ZYID");
				}

				c.info("还原正常!【结果】success");
			}
		} catch (Exception arg12) {
			c.error("获取信息异常!", arg12);
			this.aV.a(ah.iG, "医嘱还原当前科室异常{excuteNicuAndCcu.excuteNicuAndCcuBack()}", "", arg12.getMessage());
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/dep/json/queryAllInfoByDepTypeEasyUi"})
	@ResponseBody
	public void s(HttpServletRequest request, HttpServletResponse response, String depType, String allDepName,
			String dgsType) throws UnsupportedEncodingException {
		AcAccount ac = (AcAccount) this.b(request);
		Dep depSearch = new Dep();
		depSearch.setHospId(ac.getUnitId());
		depSearch.setDeptTypeId(depType);
		List list = this.e.d(depSearch);
		boolean sum = false;
		boolean docSum = false;
		Dep all = new Dep();
		all.setDeptName(ab.isEmpty(allDepName) ? "全院" : allDepName);
		list.add(0, all);
		this.b(response, list);
	}

	@RequestMapping({"/dep/json/get"})
	@ResponseBody
	public void T(HttpServletRequest request, HttpServletResponse response, String unitId, String id) {
		Dep dep = this.e.F(unitId, id);
		if (dep != null) {
			this.a(response, new KvEntity(dep.getDeptId(), dep.getDeptName()));
		}

	}

	@RequestMapping({"/dep/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap, Dep dep) {
		if (StringUtils.isBlank(dep.getHospId())) {
			AcAccount acAccount = (AcAccount) this.b(request);
			dep.setHospId(acAccount.getUnitId());
		}

		modelMap.put("dep", dep);
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		return "organization/depList";
	}

	@RequestMapping({"/dep/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "科室管理--科室列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Dep dep) {
		if (dep.getSearchString() != null && !"".equals(dep.getSearchString())) {
			dep.setSearchString(ab.aR(dep.getSearchString()));
		}

		MyPage page = this.e.b(dep);
		this.b(response, page);
	}

	@RequestMapping({"/dep/f_view/toedit"})
	@SqlLog(p = "科室管理--科室详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			Dep dep = this.e.get(id);
			modelMap.put("dep", dep);
		}

		return "organization/depEdit";
	}

	@RequestMapping({"/dep/f_json/save"})
	@ResponseBody
	@SqlLog(p = "科室管理--保存科室信息")
	public void b(HttpServletRequest request, HttpServletResponse response, Dep dep) {
		Result result = null;

		try {
			result = this.e.f(dep);
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/dep/f_json/upload"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long unitId, MultipartFile photo) {
		Result result = null;

		try {
			result = new Result();
			String e = "";
			if (photo != null) {
				e = ae.a(photo, request, Integer.valueOf(5), "depPhoto");
				if ("maxSize".equals(e)) {
					new Result("maxSize", "图片大小超过5M!");
				} else if ("typeNoMatch".equals(e)) {
					new Result("typeNoMatch", "上传的不是图片文件!");
				} else {
					result.setResult("success");
					result.setMsg(e);
				}
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/dep/f_json/pageQueryForAuthDept"})
	@ResponseBody
	public void P(HttpServletRequest request, HttpServletResponse response) {
		List deptList = null;
		AcAccount account = (AcAccount) this.b(request);
		List sqksList = this.dF.getAll(account.getDoctorId());
		ArrayList deptIdList = new ArrayList();
		Iterator deptId = sqksList.iterator();

		while (deptId.hasNext()) {
			Zg031Sqks deptIds = (Zg031Sqks) deptId.next();
			deptIdList.add(deptIds.getAuthDeptId());
		}

		String arg12 = account.getScopeInfo();
		if (ab.isNotEmpty(arg12)) {
			String[] arg13 = arg12.split(",");
			String[] arg11 = arg13;
			int arg10 = arg13.length;

			for (int arg9 = 0; arg9 < arg10; ++arg9) {
				String id = arg11[arg9];
				deptIdList.add(id);
			}
		}

		if (deptIdList.size() > 0) {
			deptList = this.e.getByDeptIds(deptIdList);
		}

		this.a(response, deptList);
	}
}