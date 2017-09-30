package com.nis.bl.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcRole;
import com.nis.bl.entity.Bl002Sjdj;
import com.nis.bl.entity.Bl005Wtjg;
import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.service.Bl002SjdjService;
import com.nis.bl.service.Bl005WtjgService;
import com.nis.bl.service.Bl006JyjgService;
import com.nis.bl.service.Bl007FcsjService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.enums.e;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.msg.service.NyMessageUserDetailService;
import com.nis.organization.entity.Dep;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import com.nis.param.service.SysParamService;
import com.nis.user.service.UserService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bl002SjdjController extends BaseController {
	private static final Logger c = Logger.getLogger(Bl002SjdjController.class);
	@Autowired
	private Bl002SjdjService cP;
	@Autowired
	private Bl006JyjgService cQ;
	@Autowired
	private Bl007FcsjService cR;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysDictService p;
	@Autowired
	private Bl005WtjgService cS;
	@Autowired
	private DepService e;
	@Autowired
	private NyMessageUserDetailService cT;
	@Autowired
	private UserService cU;
	@Autowired
	private DoctorService f;
	@Autowired
	private NyMessageDetailService cV;

	@RequestMapping({"/bl002Sjdj/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate, String startDateFc,
			String endDateFc) {
		if (ab.isEmpty(startDateFc)) {
			startDateFc = com.nis.comm.utils.f.formatDate(new Date());
			endDateFc = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a(new Date(), 7));
		}

		AcAccount acc = (AcAccount) this.b(request);
		AcRole role = acc.getRoleCur();
		modelMap.put("roleType", role.getRoleType());
		if (role != null && "clinical".equals(role.getRoleType())) {
			String BTD = (String) this.b(request, "zg_dept");
			modelMap.put("deptId", BTD);
		}

		List BTD1 = this.p.u("bl_transmissible_diseases", "0");
		modelMap.put("BTD", BTD1);
		modelMap.put("startDateFc", startDateFc);
		modelMap.put("endDateFc", endDateFc);
		modelMap.put("days", this.j.findByParamCode(Param.NIS_BL_FC_DAYS));
		return "bl/bl002SjdjList";
	}

	@RequestMapping({"/bl002Sjdj/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String csmId, String q, int page,
			int size) {
		Bl002Sjdj bl002Sjdj = new Bl002Sjdj();
		bl002Sjdj.setSearchString(q);
		bl002Sjdj.setPage(Integer.valueOf(page));
		bl002Sjdj.setSize(Integer.valueOf(size));
		MyPage bl002SjdjPage = this.cP.a(bl002Sjdj);
		this.b(response, bl002SjdjPage.getRows());
	}

	@RequestMapping({"/bl002Sjdj/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "职业暴露--职业暴露列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Bl002Sjdj bl002Sjdj) {
		if (ab.isNotEmpty(bl002Sjdj.getDdeptId())) {
			Dep acc = this.e.get(bl002Sjdj.getDdeptId());
			if (acc != null) {
				bl002Sjdj.setDjDept(acc.getDeptName());
			}
		}

		AcAccount acc1 = (AcAccount) this.b(request);
		String deptCode = this.j.findByParamCode(Param.NIS_ZYBL_CAN_SEE_OTHERS_DEPT);
		String zj = this.j.findByParamCode(Param.NIS_ZYBL_ZJCLYJ);
		String[] userids = zj.split(",");
		boolean contains = Arrays.asList(userids).contains(acc1.getDocNo());
		String xlzx = this.j.findByParamCode(Param.NIS_ZYBL_XLZXSYJ);
		String[] xluids = xlzx.split(",");
		boolean xlcontains = Arrays.asList(xluids).contains(acc1.getDocNo());
		if (com.nis.comm.enums.e.fE.getValue().equals(acc1.getRoleCur().getRoleType()) && !acc1.getDepNo().equals(deptCode) && !contains
				&& !xlcontains) {
			bl002Sjdj.setDjMen(acc1.getRealname());
		}

		if (bl002Sjdj.getSearchString() != null && !"".equals(bl002Sjdj.getSearchString())) {
			bl002Sjdj.setSearchString(ab.aR(bl002Sjdj.getSearchString()));
		}

		MyPage page = this.cP.a(bl002Sjdj);
		this.b(response, page);
	}

	@RequestMapping({"/bl002Sjdj/f_json/exportExcel"})
	@ResponseBody
	@SqlLog(p = "职业暴露--职业暴露列表导出")
	public void b(HttpServletRequest request, HttpServletResponse response, Bl002Sjdj bl002Sjdj) {
		if (ab.isNotEmpty(bl002Sjdj.getSearchString())) {
			try {
				bl002Sjdj.setSearchString(URLDecoder.decode(bl002Sjdj.getSearchString(), "utf-8"));
			} catch (UnsupportedEncodingException arg7) {
				arg7.printStackTrace();
			}
		}

		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("职业暴露登记情况_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.cP.b(bl002Sjdj);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/f_task/zybl/sendMessage"})
	@SqlLog(p = "职业暴露--发送职业暴露复查提醒消息")
	public void j(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		c.info("患者数据接口监控开始!请求时间：" + com.nis.comm.utils.f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());

		try {
			String e = this.j.findByParamCode(Param.NIS_TASK_ZYBL_SENDMESSAGE);
			List listCount = this.cP.findFcMessageCount(e);
			List list = this.cP.findFcMessage(e);
			AcAccount acc = (AcAccount) this.b(request);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			for (int i = 0; i < listCount.size(); ++i) {
				String fcTime = "";
				String contentTemp = "";
				Iterator arg12 = list.iterator();

				while (arg12.hasNext()) {
					Bl002Sjdj bl002Sjdj = (Bl002Sjdj) arg12.next();
					fcTime = sdf.format(bl002Sjdj.getFcTime());
					if (((Bl002Sjdj) listCount.get(i)).getDjCardid().equals(bl002Sjdj.getDjCardid())) {
						String Temp = bl002Sjdj.getItemName();
						contentTemp = contentTemp + Temp.replace("推荐检测时间", "") + "、";
					}
				}

				contentTemp = contentTemp.substring(0, contentTemp.length() - 1);
				contentTemp = contentTemp + " 项目需要在 " + fcTime + " 进行复检!";
				this.cV.a((String) null, (String) null, acc.getUsername(), acc.getRealname(), contentTemp,
						new String[]{((Bl002Sjdj) listCount.get(i)).getDjCardid()}, (String[]) null, al.js.getValue(),
						(String) null);
			}

			c.info("患者数据接口监控处理结束!【结果】success");
		} catch (Exception arg14) {
			c.error("患者数据接口监控进程处理异常!【异常信息】" + arg14.getMessage());
			arg14.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_view/toedit"})
	@SqlLog(p = "职业暴露--职业暴露详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String id, String msgType) {
		AcAccount acc = (AcAccount) this.b(request);
		AcRole role = acc.getRoleCur();
		String pgcz = this.j.findByParamCode(Param.NIS_ZYBL_PGCZ);
		modelMap.put("pgcz", pgcz);
		String deptCode = this.j.findByParamCode(Param.NIS_ZYBL_CAN_SEE_OTHERS_DEPT);
		String qm = this.j.findByParamCode(Param.NIS_ZYBL_QM);
		modelMap.put("qm", qm);
		String jb = this.j.findByParamCode(Param.NIS_ZYBL_JB);
		modelMap.put("jb", jb);
		String zjyj = this.j.findByParamCode(Param.NIS_ZYBL_ZJCLYJ);
		modelMap.put("ZJCLYJ", zjyj);
		String xlzxyj = this.j.findByParamCode(Param.NIS_ZYBL_XLZXSYJ);
		modelMap.put("XLZXSYJ", xlzxyj);
		String deptId;
		if (ab.isNotEmpty(deptCode) && deptCode.equals(acc.getDepNo())) {
			if (ab.isEmpty(zjyj) && ab.isEmpty(xlzxyj)) {
				modelMap.put("canFill", "canFill");
			} else {
				String[] BTD = zjyj.split(",");
				String[] bl002Sjdj = xlzxyj.split(",");
				String[] arg17 = BTD;
				int arg16 = BTD.length;

				int cwkDoctor;
				for (cwkDoctor = 0; cwkDoctor < arg16; ++cwkDoctor) {
					deptId = arg17[cwkDoctor];
					if (ab.isNotEmpty(deptId) && acc.getUsername().equals(deptId)) {
						modelMap.put("canFill", "canFill");
					}
				}

				arg17 = bl002Sjdj;
				arg16 = bl002Sjdj.length;

				for (cwkDoctor = 0; cwkDoctor < arg16; ++cwkDoctor) {
					deptId = arg17[cwkDoctor];
					if (ab.isNotEmpty(deptId) && acc.getUsername().equals(deptId)) {
						modelMap.put("canFill", "canFill");
					}
				}
			}
		}

		modelMap.put("roleType", role.getRoleType());
		List arg18 = this.p.u("bl_transmissible_diseases", "0");
		modelMap.put("BTD", arg18);
		Bl002Sjdj arg19;
		if (StringUtils.isNotBlank(id)) {
			arg19 = this.cP.get(id);
			List arg20 = this.cS.findByBlId(id);
			modelMap.put("bl002Sjdj", arg19);
			Iterator arg22 = arg20.iterator();

			while (arg22.hasNext()) {
				Bl005Wtjg arg21 = (Bl005Wtjg) arg22.next();
				modelMap.put("wtjg" + arg21.getStId(), arg21.getShText());
			}

			Doctor arg23;
			if (ab.isNotEmpty(arg19.getKjyjQm())) {
				arg23 = this.f.get(arg19.getKjyjQm());
				if (arg23 != null && ab.isEmpty(arg23.getEmail())) {
					arg23.setEmail("/../download/zyblqmImages/" + arg23.getEmployeeId() + ".png");
				}

				modelMap.put("kjyjDoctor", arg23);
			}

			if (ab.isNotEmpty(arg19.getGrxjbkQm())) {
				arg23 = this.f.get(arg19.getGrxjbkQm());
				if (arg23 != null && ab.isEmpty(arg23.getEmail())) {
					arg23.setEmail("/../download/zyblqmImages/" + arg23.getEmployeeId() + ".png");
				}

				modelMap.put("grxjbkDoctor", arg23);
			}

			if (ab.isNotEmpty(arg19.getYgkQm())) {
				arg23 = this.f.get(arg19.getYgkQm());
				modelMap.put("ygkDoctor", arg23);
			}

			if (ab.isNotEmpty(arg19.getCwkQm())) {
				arg23 = this.f.get(arg19.getCwkQm());
				modelMap.put("cwkDoctor", arg23);
			}

			if (ab.isNotEmpty(msgType)) {
				this.cT.updateByCaseIdAndUserId(id, acc.getUserId());
			}
		} else {
			arg19 = new Bl002Sjdj();
			if (role != null && "clinical".equals(role.getRoleType())) {
				deptId = (String) this.b(request, "zg_dept");
				arg19.setDjDept(deptId);
			}

			modelMap.put("bl002Sjdj", arg19);
		}

		return "bl/bl002SjdjEdit";
	}

	@RequestMapping({"/bl002Sjdj/f_view/tonote"})
	public String d(HttpServletRequest request, ModelMap modelMap, String id) {
		String lc = this.j.findByParamCode(Param.NIS_ZYBL_LC);
		modelMap.put("lc", lc);
		return "bl/bl002SjdjNote";
	}

	@RequestMapping({"/bl002Sjdj/f_view/qmDetail"})
	public String e(HttpServletRequest request, ModelMap modelMap, String id) {
		return "bl/qmDetail";
	}

	@RequestMapping({"/bl002Sjdj/f_json/yzQm"})
	@ResponseBody
	@SqlLog(p = "职业暴露--职业暴露签名")
	public void a(HttpServletRequest request, HttpServletResponse response, String username, String password,
			String blId, String qmType, String ygkYj, String cwkYj) {
		Result result = null;

		try {
			result = this.cU.a(username, password, false, (String) null);
			AcAccount e = (AcAccount) result.getData();
			if (!"success".equals(result.getResult())) {
				result = this.cU.b(username, password, (String) null, true);
				e = (AcAccount) result.getData();
			}

			if ("success".equals(result.getResult())) {
				Bl002Sjdj sjdj = this.cP.get(blId);
				if ("grxjbk".equals(qmType)) {
					sjdj.setGrxjbkQm(e.getUserId());
				} else if ("ygk".equals(qmType)) {
					if (ab.isNotEmpty(ygkYj)) {
						sjdj.setYgkYj(URLDecoder.decode(ygkYj, "utf-8"));
					}

					sjdj.setYgkQm(e.getUserId());
				} else if ("cwk".equals(qmType)) {
					if (ab.isNotEmpty(cwkYj)) {
						sjdj.setCwkYj(URLDecoder.decode(cwkYj, "utf-8"));
					}

					sjdj.setCwkQm(e.getUserId());
				} else if ("kjyj".equals(qmType)) {
					sjdj.setKjyjQm(e.getUserId());
				}

				this.cP.update(sjdj);
			}
		} catch (Exception arg11) {
			c.error("获取信息异常!", arg11);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/save"})
	@ResponseBody
	@SqlLog(p = "职业暴露--保存职业暴露")
	public void c(HttpServletRequest request, HttpServletResponse response, Bl002Sjdj bl002Sjdj) {
		Result result = null;

		try {
			result = new Result();
			if (this.cP.get(bl002Sjdj.getBlId()) == null) {
				bl002Sjdj.setDjTime(new Date());
				bl002Sjdj.setDjMen(this.d(request).getRealname());
				bl002Sjdj.setSjState("0");
				bl002Sjdj.setBlStep("1");
				bl002Sjdj.setSjId("XY_TY_DJ");
				this.cP.save(bl002Sjdj);
			} else {
				this.cP.update(bl002Sjdj);
			}

			Map e = this.cS.a(request);
			Set set = e.entrySet();
			Iterator arg7 = set.iterator();

			label38 : while (true) {
				String key;
				String value;
				do {
					if (!arg7.hasNext()) {
						result.setMsg(bl002Sjdj.getBlId());
						result.setResult("success");
						break label38;
					}

					Entry entry = (Entry) arg7.next();
					key = entry.getKey().toString();
					value = entry.getValue().toString();
				} while (key.indexOf("wtjg") <= -1);

				String[] values = value.split(",");
				this.cS.delete(bl002Sjdj.getBlId(), Long.valueOf(Long.parseLong(key.substring(4))));
				String[] arg14 = values;
				int arg13 = values.length;

				for (int arg12 = 0; arg12 < arg13; ++arg12) {
					String vl = arg14[arg12];
					if (ab.isNotEmpty(vl)) {
						Bl005Wtjg wtjg = new Bl005Wtjg();
						wtjg.setBlId(bl002Sjdj.getBlId());
						wtjg.setSjId("XY_TY_DJ");
						wtjg.setStId(Long.valueOf(Long.parseLong(key.substring(4))));
						wtjg.setShText(vl);
						wtjg.setShType("1");
						wtjg.setShMemo(vl);
						wtjg.setFlag(Long.valueOf(1L));
						this.cS.save(wtjg);
					}
				}
			}
		} catch (Exception arg16) {
			c.error("获取信息异常!", arg16);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "职业暴露--删除职业暴露")
	public void c(HttpServletRequest request, HttpServletResponse response, String blId) {
		Result result = null;

		try {
			result = new Result();
			this.cP.delete(blId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/cancelSave"})
	@ResponseBody
	@SqlLog(p = "职业暴露--取消保存职业暴露")
	public void h(HttpServletRequest request, HttpServletResponse response, String blId) {
		Result result = null;

		try {
			result = new Result();
			Bl002Sjdj e = this.cP.get(blId);
			if (e.getSjState().equals("1")) {
				e.setSjState("2");
			} else if (e.getSjState().equals("5")) {
				e.setSjState("1");
			} else if (e.getSjState().equals("7")) {
				e.setSjState("5");
			} else if (e.getSjState().equals("9")) {
				e.setSjState("7");
			}

			this.cP.update(e);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/findzyblryksfb"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
			String orderType) {
		String queryStartDate = null;
		String queryEndDate = null;
		Object myDate = new Date();
		if (ab.isNotEmpty(date)) {
			myDate = com.nis.comm.utils.f.k(date, "yyyy/MM/dd");
		}

		if (dateType.equals("month")) {
			if ("back".equals(orderType)) {
				myDate = com.nis.comm.utils.f.b((Date) myDate, -1);
			}

			if ("next".equals(orderType)) {
				myDate = com.nis.comm.utils.f.b((Date) myDate, 1);
			}

			queryStartDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, true));
			queryEndDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.b((Date) myDate, true));
		} else if (dateType.equals("week")) {
			if ("back".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 16);
			}

			queryStartDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -8));
			queryEndDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -1));
		} else if (dateType.equals("day")) {
			if ("back".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 2);
			}

			queryStartDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -1));
			queryEndDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.cP.findzyblryksfb(queryStartDate, queryEndDate);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(com.nis.comm.utils.f.c(com.nis.comm.utils.f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ com.nis.comm.utils.f.c(com.nis.comm.utils.f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/findzyblfsgwtj"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
			String orderType) {
		String queryStartDate = null;
		String queryEndDate = null;
		Object myDate = new Date();
		if (ab.isNotEmpty(date)) {
			myDate = com.nis.comm.utils.f.k(date, "yyyy/MM/dd");
		}

		if (dateType.equals("month")) {
			if ("back".equals(orderType)) {
				myDate = com.nis.comm.utils.f.b((Date) myDate, -1);
			}

			if ("next".equals(orderType)) {
				myDate = com.nis.comm.utils.f.b((Date) myDate, 1);
			}

			queryStartDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, true));
			queryEndDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.b((Date) myDate, true));
		} else if (dateType.equals("week")) {
			if ("back".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 16);
			}

			queryStartDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -8));
			queryEndDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -1));
		} else if (dateType.equals("day")) {
			if ("back".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = com.nis.comm.utils.f.a((Date) myDate, 2);
			}

			queryStartDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -1));
			queryEndDate = com.nis.comm.utils.f.formatDate(com.nis.comm.utils.f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.cP.findzyblfsgwtj(queryStartDate, queryEndDate);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(com.nis.comm.utils.f.c(com.nis.comm.utils.f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ com.nis.comm.utils.f.c(com.nis.comm.utils.f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/findMainExposure"})
	@ResponseBody
	public void i(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = com.nis.comm.utils.f.getYearLast();
			if (StringUtils.isEmpty(dataRange)) {
				dataRange = "month";
			}

			Date startDate;
			if ("month".equals(dataRange)) {
				startDate = com.nis.comm.utils.f.a(e, false);
			} else if ("quarter".equals(dataRange)) {
				startDate = com.nis.comm.utils.f.u(e);
			} else {
				startDate = com.nis.comm.utils.f.getYearFirst();
			}

			List list = this.cP.findMainExposure(startDate, e);
			result = new Result("success");
			result.setData(list);
		} catch (Exception arg7) {
			result = new Result("error", arg7.getMessage());
			arg7.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_view/uploadImage"})
	public String e(HttpServletRequest request, ModelMap modelMap) {
		return "bl/uploadImage";
	}

	@RequestMapping({"/bl002Sjdj/f_json/replaceImage"})
	@ResponseBody
	public void h(HttpServletRequest request, HttpServletResponse response, String fileName, String file) {
		Result result = this.cP.a(request, fileName, file);
		this.b(response, result);
	}

	@RequestMapping({"/bl002Sjdj/f_json/fcrPageQuery"})
	@ResponseBody
	public void k(HttpServletRequest request, HttpServletResponse response) {
		Bl007Fcsj bl007Fcsj = new Bl007Fcsj();
		this.cR.findBl007FcsjList(bl007Fcsj);
	}
}