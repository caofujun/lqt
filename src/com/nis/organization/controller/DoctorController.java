package com.nis.organization.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcAccountService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.h;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ae;
import com.nis.organization.entity.Dep;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import com.nis.organization.units.a;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DoctorController extends BaseController {
	private static final Logger c = Logger.getLogger(DoctorController.class);
	@Autowired
	private DoctorService f;
	@Autowired
	private AcRoleService g;
	@Autowired
	private AcAccountService d;
	@Autowired
	private DepService e;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/doctor/json/query"})
	@ResponseBody
	public void r(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId, String name) {
		List list = this.f.findLike(hospId, deptId, name);
		ArrayList kvList = new ArrayList();
		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			Doctor doctor = (Doctor) arg8.next();
			kvList.add(new KvEntity(doctor.getId(), doctor.getEmployeeName()));
		}

		this.b(response, kvList);
	}

	@RequestMapping({"/doctor/json/queryToSelect"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId, String q,
			int page, int size, String defValue) {
		if (StringUtils.isBlank(hospId)) {
			AcAccount doctor = (AcAccount) this.b(request);
			hospId = doctor.getUnitId();
		}

		Doctor doctor1 = new Doctor();
		if (ab.isNotEmpty(q)) {
			doctor1.setSearchString(ab.aR(q));
		} else {
			doctor1.setEmployeeId(defValue);
		}

		doctor1.setHospId(hospId);
		doctor1.setDeptId(deptId);
		doctor1.setPage(Integer.valueOf(page));
		doctor1.setSize(Integer.valueOf(size));
		List list = this.f.queryToSelect(doctor1);
		this.b(response, list);
	}

	@RequestMapping({"/doctor/json/queryToSelect1"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId, String q,
			int page, int size, String defValue) {
		if (StringUtils.isBlank(hospId)) {
			AcAccount doctor = (AcAccount) this.b(request);
			hospId = doctor.getUnitId();
		}

		Doctor doctor1 = new Doctor();
		if (ab.isNotEmpty(q)) {
			doctor1.setSearchString(q);
		} else {
			doctor1.setEmployeeId(defValue);
		}

		doctor1.setHospId(hospId);
		doctor1.setDeptId(deptId);
		doctor1.setPage(Integer.valueOf(page));
		doctor1.setSize(Integer.valueOf(size));
		List list = this.f.queryToSelect1(doctor1);
		this.b(response, list);
	}

	@RequestMapping({"/doctor/json/queryList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId, String q,
			int page, int size, String defValue, String cclass) {
		if (StringUtils.isBlank(hospId)) {
			AcAccount doctor = (AcAccount) this.b(request);
			hospId = doctor.getUnitId();
		}

		Doctor doctor1 = new Doctor();
		if (ab.isNotEmpty(q)) {
			doctor1.setSearchString(ab.aR(q));
		} else {
			doctor1.setEmployeeId(defValue);
		}

		doctor1.setHospId(hospId);
		doctor1.setCclass(cclass);
		doctor1.setDeptId(deptId);
		doctor1.setPage(Integer.valueOf(page));
		doctor1.setSize(Integer.valueOf(size));
		MyPage pageDoctor = this.f.b(doctor1);
		List list = this.d.findRealname(q);
		List doctorList = pageDoctor.getRows();
		Iterator arg14 = list.iterator();

		while (arg14.hasNext()) {
			AcAccount account = (AcAccount) arg14.next();
			Doctor doc = new Doctor();
			doc.setEmployeeId(account.getUsername());
			doc.setEmployeeName(account.getRealname());
			doctorList.add(doc);
		}

		this.b(response, doctorList);
	}

	@RequestMapping({"/doctor/json/get"})
	@ResponseBody
	public void t(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId, String id) {
		Doctor doctor = this.f.get(id);
		if (doctor != null) {
			this.a(response, new KvEntity(doctor.getEmployeeId(), doctor.getEmployeeName()));
		}

	}

	@RequestMapping({"/doctor/json/findByid"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isNotEmpty(id)) {
				Doctor e = this.f.get(id);
				if (e != null) {
					String deptCode = e.getDeptId();
					if (deptCode.indexOf(",") == -1) {
						e.setDeptId(deptCode);
					} else if (deptCode.indexOf(",") > -1) {
						String defaultDept = deptCode.split(",")[0];
						e.setDeptId(defaultDept);
					}

					result.setData(e);
				}

				result.setResult("success");
			} else {
				result.setResult("error");
			}
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/doctor/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap, Doctor doctor) {
		if (StringUtils.isBlank(doctor.getHospId())) {
			AcAccount acAccount = (AcAccount) this.b(request);
			doctor.setHospId(acAccount.getUnitId());
		}

		modelMap.put("doctor", doctor);
		return "organization/doctorList";
	}

	@RequestMapping({"/doctor/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "医生管理--医生列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Doctor doctor) {
		if (doctor.getSearchString() != null && !"".equals(doctor.getSearchString())) {
			doctor.setSearchString(ab.aR(doctor.getSearchString()));
		}

		MyPage page = this.f.a(doctor);
		this.b(response, page);
	}

	@RequestMapping({"/doctor/f_view/toedit"})
	@SqlLog(p = "医生管理--医生详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String hospId, String deptId, String id) {
		Doctor acAccount;
		if (StringUtils.isNotBlank(id)) {
			acAccount = this.f.get(id);
			modelMap.put("doctor", acAccount);
		} else {
			acAccount = new Doctor();
			String roles = EncryptUtils.aj(this.j.findByParamCode(Param.NIS_DOC_DEFAULT_PWD));
			acAccount.setAuthCode(roles);
			modelMap.put("doctor", acAccount);
		}

		AcAccount acAccount1 = (AcAccount) this.b(request);
		List roles1 = this.g.getRoleByUnitId(acAccount1.getUnitId(), (String) null);
		modelMap.put("roles", roles1);
		return "organization/doctorEdit";
	}

	@RequestMapping({"/doctor/f_json/save"})
	@ResponseBody
	@SqlLog(p = "医生管理--保存医生信息")
	public void b(HttpServletRequest request, HttpServletResponse response, Doctor doctor) {
		Result result = null;

		try {
			AcAccount e = (AcAccount) this.b(request);
			result = new Result();
			doctor.setHospId(e.getUnitId());
			doctor.setLastAt(new Date());
			AcAccount acAccount = new AcAccount();
			acAccount.setMobilenum(doctor.getPhoneNo());
			acAccount.setUsername(doctor.getEmployeeId());
			acAccount.setEmail(doctor.getEmail());
			acAccount.setUserId(doctor.getId());
			List docts = this.d.c(acAccount);
			if (docts != null && docts.size() > 0) {
				result.setResult("error_extis");
				result.setMsg("员工编号、联系电话、或邮箱已存在！");
			} else {
				if (StringUtils.isBlank(doctor.getId())) {
					this.f.save(doctor);
				} else {
					this.f.update(doctor);
				}

				result.setResult("success");
			}
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/doctor/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "医生管理--删除医生信息")
	public void u(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId,
			String doctorId) {
		Result result = null;

		try {
			result = new Result();
			this.f.delete(hospId, deptId, doctorId);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/doctor/f_json/resetPassword"})
	@ResponseBody
	@SqlLog(p = "医生管理--重置医生密码")
	public void v(HttpServletRequest request, HttpServletResponse response, String hospId, String deptId,
			String doctorId) {
		Result result = null;

		try {
			result = new Result();
			String e = EncryptUtils.aj(this.j.findByParamCode(Param.NIS_DOC_DEFAULT_PWD));
			this.f.updatePwd(hospId, doctorId, e);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/doctor/f_json/upload"})
	@ResponseBody
	@SqlLog(p = "医生管理--上传医生图片")
	public void a(HttpServletRequest request, HttpServletResponse response, String hospId, MultipartFile photo) {
		Result result = null;

		try {
			result = new Result();
			String e = "";
			if (photo != null) {
				e = ae.a(photo, request, Integer.valueOf(5), "docPhoto");
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

	@RequestMapping({"/doctor/f_view/selectDoctindex"})
	public String a(HttpServletRequest request, ModelMap modelMap, Long depId) {
		modelMap.put("depId", depId);
		return "organization/doctorSelectList";
	}

	@RequestMapping({"/doctor/f_json/nosetDepQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Doctor doctor, String depId) {
		MyPage page = this.f.a(doctor, depId);
		this.b(response, page);
	}

	@RequestMapping({"/doctor/f_json/loadDoctByUnitId"})
	@ResponseBody
	public void al(HttpServletRequest request, HttpServletResponse response, String hospId) {
		List result = this.f.getAll(hospId);
		this.b(response, result);
	}

	@RequestMapping({"/doctor/f_view/toSeldep"})
	public String M(HttpServletRequest request, ModelMap modelMap, String id) {
		return "organization/doctorDep";
	}

	@RequestMapping({"/doctor/f_json/findMyDep"})
	@ResponseBody
	@SqlLog(p = "医生管理--医生科室授权列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String id, Dep dep) {
		if (dep.getFlag() == null) {
			dep.setFlag(Long.valueOf(1L));
		}

		List list = this.e.d(dep);
		Doctor doctor = this.f.get(id);
		Iterator arg7 = list.iterator();

		while (true) {
			while (arg7.hasNext()) {
				Dep page = (Dep) arg7.next();
				if (StringUtils.isNotBlank(doctor.getDeptId())
						&& (doctor.getDeptId() + ",").indexOf(page.getDeptId() + ",") > -1) {
					page.setIsHavegrant(h.fP.getCode().toString());
				} else {
					page.setIsHavegrant(h.fQ.getCode().toString());
				}
			}

			Collections.sort(list, new a());
			MyPage page1 = new MyPage(1, 10, list.size(), list);
			this.b(response, page1);
			return;
		}
	}

	@RequestMapping({"/doctor/f_json/saveDep"})
	@ResponseBody
	@SqlLog(p = "医生管理--保存医生科室授权")
	public void w(HttpServletRequest request, HttpServletResponse response, String id, String havegrants,
			String delDepIds) {
		Result result = new Result();

		try {
			this.f.r(id, havegrants, delDepIds);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("保存授权信息异常!", arg7);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}
}