package com.nis.msg.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcAccountService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.enums.e;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ae;
import com.nis.comm.utils.f;
import com.nis.comm.utils.l;
import com.nis.dict.entity.Sop;
import com.nis.dict.service.SopService;
import com.nis.log.entity.SysLoginLog;
import com.nis.log.service.SysLoginLogService;
import com.nis.msg.entity.NyMessageTheme;
import com.nis.msg.entity.NyMessageUser;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageThemeService;
import com.nis.msg.service.NyMessageUserDetailService;
import com.nis.msg.service.NyMessageUserService;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DoctorService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.zg.service.Zg003YyzgService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
public class NyMessageThemeController extends BaseController {
	private static final Logger c = Logger.getLogger(NyMessageThemeController.class);
	@Autowired
	private NyMessageThemeService uS;
	@Autowired
	private NyMessageDetailService uR;
	@Autowired
	private NyMessageUserService uT;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private NyMessageUserDetailService cT;
	@Autowired
	private Zg003YyzgService q;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysLoginLogService tI;
	@Autowired
	private AcAccountService uJ;
	@Autowired
	private DoctorService f;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private SopService qz;
	@Autowired
	private NyMessageDetailService cV;

	@SqlLog(p = "干预消息--消息记录")
	@RequestMapping({"/nyMessageTheme/f_view/toedit"})
	public String a(HttpServletRequest request, ModelMap modelMap, String mzid, String zyid, String msgType,
			String pzId, String themeId, String deptId, String userId, String fileId, String defContent,
			String testOrderNo) {
		AcAccount account = (AcAccount) this.b(request);
		List nyMessageUser;
		NyMessageTheme e1;
		NyMessageUser nyMessageUser1;
		if (ab.isNotEmpty(zyid) && !"null".equals(zyid)) {
			St003Cryxxb refresh2 = this.bg.get(zyid);
			e1 = this.uS.getByZyid(zyid);
			if (e1 != null) {
				this.cT.updateByThemeIdAndUserId(e1.getThemeId(), this.d(request).getUsername());
				nyMessageUser = this.uR.getbyThemeId(e1.getThemeId());
				modelMap.put("messageDetailList", nyMessageUser);
			} else {
				e1 = new NyMessageTheme();
				e1.setCreateTime(new Date());
				e1.setCreateUser(this.d(request).getUsername());
				e1.setZyid(zyid);
				e1.setTheme("患者" + refresh2.getPatientName() + "消息内容");
				this.uS.save(e1);
			}

			if (account.getRoleCur().getRoleType().equals(e.fD.getValue())) {
				nyMessageUser1 = new NyMessageUser();
				if (refresh2.getChargeDrId() != null) {
					nyMessageUser1.setThemeId(e1.getThemeId());
					nyMessageUser1.setUserId(refresh2.getChargeDrId());
					nyMessageUser1.setCreateTime(new Date());
					nyMessageUser1 = this.uT.a(nyMessageUser1);
					modelMap.put("nyMessageUser", nyMessageUser1);
				}

				if (refresh2.getDeptCode() != null) {
					nyMessageUser1 = new NyMessageUser();
					nyMessageUser1.setDeptId(refresh2.getDeptCode());
					nyMessageUser1.setThemeId(e1.getThemeId());
					nyMessageUser1.setCreateTime(new Date());
					this.uT.a(nyMessageUser1);
				}
			} else if (account.getRoleCur().getRoleType().equals(e.fE.getValue())) {
				nyMessageUser1 = new NyMessageUser();
				nyMessageUser1.setDeptId(this.j.findByParamCode(Param.NIS_GK_DEPTID));
				nyMessageUser1.setThemeId(e1.getThemeId());
				nyMessageUser1.setCreateTime(new Date());
				nyMessageUser1 = this.uT.a(nyMessageUser1);
				modelMap.put("nyMessageUser", nyMessageUser1);
			}

			if (ab.isEmpty(msgType)) {
				msgType = al.jl.getValue();
			}

			modelMap.put("nyMessageTheme", e1);
			modelMap.put("pzId", pzId);
			modelMap.put("msgType", msgType);
		} else if (ab.isNotEmpty(mzid) && !"null".equals(mzid)) {
			St020ClinicPatients refresh1 = this.bh.getByMzid(mzid, (String) null);
			e1 = this.uS.getByMzid(mzid);
			if (e1 != null) {
				this.cT.updateByThemeIdAndUserId(e1.getThemeId(), this.d(request).getUsername());
				nyMessageUser = this.uR.getbyThemeId(e1.getThemeId());
				modelMap.put("messageDetailList", nyMessageUser);
			} else {
				e1 = new NyMessageTheme();
				e1.setCreateTime(new Date());
				e1.setCreateUser(this.d(request).getUsername());
				e1.setMzid(mzid);
				e1.setTheme("患者" + refresh1.getParentName() + "消息内容");
				this.uS.save(e1);
			}

			if (account.getRoleCur().getRoleType().equals(e.fD.getValue())) {
				nyMessageUser1 = new NyMessageUser();
				if (refresh1.getDoctorId() != null) {
					nyMessageUser1.setThemeId(e1.getThemeId());
					nyMessageUser1.setUserId(refresh1.getDoctorId());
					nyMessageUser1.setCreateTime(new Date());
					nyMessageUser1 = this.uT.a(nyMessageUser1);
					modelMap.put("nyMessageUser", nyMessageUser1);
				}

				nyMessageUser1 = new NyMessageUser();
				if (refresh1.getDeptId() == null) {
					nyMessageUser1.setDeptId(refresh1.getDeptId());
					nyMessageUser1.setThemeId(e1.getThemeId());
					nyMessageUser1.setCreateTime(new Date());
					this.uT.a(nyMessageUser1);
				}
			} else if (account.getRoleCur().getRoleType().equals(e.fE.getValue())) {
				nyMessageUser1 = new NyMessageUser();
				nyMessageUser1.setDeptId(this.j.findByParamCode(Param.NIS_GK_DEPTID));
				nyMessageUser1.setThemeId(e1.getThemeId());
				nyMessageUser1.setCreateTime(new Date());
				nyMessageUser1 = this.uT.a(nyMessageUser1);
				modelMap.put("nyMessageUser", nyMessageUser1);
			}

			if (ab.isEmpty(msgType)) {
				msgType = al.jl.getValue();
			}

			modelMap.put("nyMessageTheme", e1);
			modelMap.put("pzId", pzId);
			modelMap.put("msgType", msgType);
		} else {
			new NyMessageTheme();
			NyMessageTheme refresh;
			if (ab.isEmpty(themeId)) {
				refresh = this.uS.getByCreateUser(this.d(request).getUsername());
				if (refresh == null) {
					refresh = new NyMessageTheme();
					refresh.setCreateTime(new Date());
					refresh.setCreateUser(this.d(request).getUsername());
					refresh.setZyid(zyid);
					refresh.setTheme(this.d(request).getRealname() + "的消息内容");
					this.uS.save(refresh);
				}
			} else {
				refresh = this.uS.get(themeId);
			}

			if (ab.isNotEmpty(deptId) || ab.isNotEmpty(userId)) {
				this.cT.updateByThemeIdAndUserId(refresh.getThemeId(), this.d(request).getUsername());
				NyMessageUser e = new NyMessageUser();
				e.setThemeId(refresh.getThemeId());
				e.setDeptId(deptId);
				e.setUserId(userId);
				e.setCreateTime(new Date());
				e = this.uT.a(e);
				modelMap.put("nyMessageUser", e);
			}

			this.cT.updateByThemeIdAndUserId(refresh.getThemeId(), this.d(request).getUsername());
			modelMap.put("nyMessageTheme", refresh);
		}

		if (ab.isNotEmpty(fileId)) {
			Sop refresh3 = this.qz.get(fileId);
			if (refresh3 != null) {
				modelMap.put("model", refresh3);
			}
		}

		String refresh4;
		if (ab.isNotEmpty(defContent)) {
			System.err.println(defContent);
			refresh4 = "";

			try {
				refresh4 = URLDecoder.decode(defContent, "UTF-8");
			} catch (UnsupportedEncodingException arg16) {
				arg16.printStackTrace();
			}

			System.err.println(refresh4);
			modelMap.put("defContent", refresh4);
		}

		if (ab.isNotEmpty(testOrderNo)) {
			modelMap.put("testOrderNo", testOrderNo);
		}

		refresh4 = this.j.findByParamCode(Param.NIS_MSG_REFRESH_TIME);
		modelMap.put("refresh", refresh4);
		modelMap.put("realname", this.d(request).getRealname());
		return "msg/nyMessageThemeEdit";
	}

	@RequestMapping({"/nyMessageTheme/f_view/index"})
	public String o(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate) {
		if (ab.isEmpty(startDate)) {
			startDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a(new Date(), -30));
			endDate = com.nis.comm.utils.f.formatDate(new Date());
		}

		AcAccount acc = (AcAccount) this.b(request);
		AcRole role = acc.getRoleCur();
		if (role != null && "clinical".equals(role.getRoleType())) {
			modelMap.put("uid", acc.getUserId());
		}

		modelMap.put("startDate", startDate);
		modelMap.put("endDate", endDate);
		return "msg/nyMessageThemeList";
	}

	@RequestMapping({"/nyMessageTheme/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, NyMessageTheme nyMessageTheme) {
		if (nyMessageTheme.getSearchString() != null) {
			nyMessageTheme.setSearchString(ab.aR(nyMessageTheme.getSearchString()));
		}

		MyPage page = this.uS.a(nyMessageTheme);
		this.a(response, page);
	}

	@RequestMapping({"/nyMessageTheme/f_json/getByThemeId"})
	@ResponseBody
	public void ah(HttpServletRequest request, HttpServletResponse response, String themeId) {
		this.cT.updateByThemeIdAndUserId(themeId, this.d(request).getUsername());
		List messageDetailList = this.uR.getbyThemeId(themeId);
		this.a(response, messageDetailList);
	}

	@RequestMapping({"/nyMessageTheme/f_json/pageQueryHP"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, NyMessageTheme nyMessageTheme) {
		Result result = null;

		try {
			MyPage e = this.uS.a(nyMessageTheme);
			Iterator arg6 = e.getRows().iterator();

			while (arg6.hasNext()) {
				NyMessageTheme messageTheme = (NyMessageTheme) arg6.next();
				if (StringUtils.isNoneBlank(new CharSequence[]{messageTheme.getTheme()})
						&& messageTheme.getTheme().length() > 15) {
					messageTheme.setTheme(messageTheme.getTheme().substring(0, 15) + "...");
				}
			}

			result = new Result("success");
			result.setData(e);
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.b(response, result);
	}

	@RequestMapping({"/nyMessageTheme/f_json/upload"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long unitId, MultipartFile imgFile) {
		HashMap map = new HashMap();

		try {
			String e = "";
			if (imgFile != null) {
				e = ae.a(imgFile, request, Integer.valueOf(5), "hospitalPhoto");
				if ("maxSize".equals(e)) {
					new Result("maxSize", "图片大小超过5M!");
				} else if ("typeNoMatch".equals(e)) {
					new Result("typeNoMatch", "上传的不是图片文件!");
				} else {
					map.put("error", Integer.valueOf(0));
					map.put("url", e);
				}
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

		this.a(response, map);
	}

	@RequestMapping({"/nis/f_adapter/msg"})
	@ResponseBody
	public void S(HttpServletRequest request, HttpServletResponse response, String adapterType, String data) {
		response.setHeader("connection", "close");
		Result result = null;
		result = new Result();
		String isShow = this.j.findByParamCode(Param.NIS_IMC_IS_SHOW);
		if ("0".equals(isShow)) {
			if (com.nis.comm.enums.f.fG.getValue().equals(adapterType)) {
				Map userList = (Map) l.ar(data);
				if (userList != null && userList.get("macAddress") != null) {
					SysLoginLog userList3 = this.tI.getByMacAddress(userList.get("macAddress").toString());
					if (userList3 != null) {
						List account1 = this.cT.getByUserId(userList3.getUserId());
						result.setData(account1);
						result.setResult("success");
						result.setMsg(userList3.getUserId() + "," + userList3.getUsername());
					} else {
						result.setResult("error");
					}
				} else if (userList != null && userList.get("userId") != null) {
					List userList1 = this.cT.getByUserId(userList.get("userId").toString());
					result.setData(userList1);
					result.setResult("success");
					AcAccount account = this.uJ.b(userList.get("userId").toString());
					if (account == null) {
						Doctor doctor = this.f.ck(userList.get("userId").toString());
						if (doctor != null) {
							account = new AcAccount();
							account.setUsername(doctor.getEmployeeId());
							account.setPasswd(doctor.getPwd());
							account.setRealname(doctor.getEmployeeName());
							result.setMsg(userList.get("userId") + "," + account.getRealname());
						}
					}
				} else {
					result.setResult("error");
				}

				this.a(response, result);
			}
		} else {
			ArrayList userList2 = new ArrayList();
			result.setData(userList2);
			result.setResult("success");
		}

		this.a(response, result);
	}

	@RequestMapping({"/nis/f_adapter/msgHistroy"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String userId, Integer orderType,
			String orderBy, String startDate, String endDate, Integer page, Integer size) {
		response.setHeader("connection", "close");
		Result result = null;
		result = new Result();
		String isShow = this.j.findByParamCode(Param.NIS_IMC_IS_SHOW);
		MyPage userList;
		if ("0".equals(isShow)) {
			if (ab.isNotEmpty(userId)) {
				userList = this.cT.a(userId, page, size, startDate, endDate, orderType, orderBy);
				result.setData(userList);
				result.setResult("success");
			} else {
				result.setResult("error");
			}
		} else {
			userList = new MyPage(1, 10, 0, (List) null);
			result.setData(userList);
			result.setResult("success");
		}

		this.a(response, result);
	}
}