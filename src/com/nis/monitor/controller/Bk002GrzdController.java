package com.nis.monitor.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.dict.service.Zg005YygrzdService;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Gr016BkKjyw;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import com.nis.monitor.entity.St004BkCgxx;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Gr016BkKjywService;
import com.nis.monitor.service.St004BkCgxxService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.service.St004YzxxbService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bk002GrzdController extends BaseController {
	private static final Logger c = Logger.getLogger(Bk002GrzdController.class);
	@Autowired
	private Bk002GrzdService us;
	@Autowired
	private Bk001SbkService bW;
	@Autowired
	private Zg005YygrzdService E;
	@Autowired
	private SysParamService j;
	@Autowired
	private Gr016BkKjywService uu;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private St004BkCgxxService uv;

	@RequestMapping({"/bk002Grzd/f_json/updGrzdInfo"})
	@ResponseBody
	@SqlLog(p = "感染病例上报卡更新")
	public void a(HttpServletRequest request, HttpServletResponse response, Bk002Grzd bk002Grzd, String chargeDrId,
			String jbzd, String jbzdCode, String reportDeptId, String reportDeptName, Gr016BkKjyw gr016BkKjyw,
			St004BkCgxx st004BkCgxx) {
		Result result = new Result();
		String isYgys;
		if (StringUtils.isNotBlank(bk002Grzd.getInfectDiagnId())) {
			isYgys = this.j.findByParamCode(Param.NIS_BK_GRZDSB);
			if (isYgys.equals("1")) {
				List num = this.bW.isReportBefore(bk002Grzd.getZyid(), bk002Grzd.getInfectDiagnId(),
						"" + bk002Grzd.getInfectType());
				if (num != null && num.size() > 0) {
					result.setResult("error");
					result.setMsg("此感染之前已报过！不允许再次上报！");
					this.a(response, result);
					return;
				}
			}
		}

		isYgys = this.j.findByParamCode(Param.NIS_BK_IS_YGYS_REQUIRED);
		if (!"1".equals(isYgys) || bk002Grzd.getFactorIds() != null && bk002Grzd.getFactorIds().length != 0) {
			int arg24 = this.E.findNumByInfectCode("SSI", bk002Grzd.getInfectDiagnId());
			String isIncision = this.j.findByParamCode(Param.NIS_BK_IS_INCISION_REQUIRED);
			if ("1".equals(isIncision) && arg24 > 0 && StringUtils.isBlank(bk002Grzd.getMemo())) {
				result.setResult("error");
				result.setMsg("手术类感染切口必填！");
				this.a(response, result);
			} else {
				String nbisr = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_REQUIRED);
				if ("1".equals(nbisr) && (arg24 > 0 || "POP".equals(bk002Grzd.getInfectDiagnId()))
						&& StringUtils.isBlank(bk002Grzd.getOpeName())) {
					result.setResult("error");
					result.setMsg("该感染诊断属于手术部位感染，未选择手术，无法上报！");
					this.a(response, result);
				} else {
					this.uu.deleteByRefid(gr016BkKjyw.getRefid());
					String[] yzIds = gr016BkKjyw.getYzIds();

					try {
						if (yzIds != null && yzIds.length > 0) {
							for (int e = 0; e < yzIds.length; ++e) {
								Gr016BkKjyw bk001Sbk = new Gr016BkKjyw();
								St004Yzxxb yzIdList = this.bu.get(yzIds[e]);
								if (yzIdList != null) {
									List i = gr016BkKjyw.getGr16List();
									Iterator sdf = i.iterator();

									while (sdf.hasNext()) {
										Gr016SsbwKjyw st004BkCgxxTemp = (Gr016SsbwKjyw) sdf.next();
										if (st004BkCgxxTemp.getYzId().equals(yzIds[e])) {
											bk001Sbk.setPreYymd(st004BkCgxxTemp.getPreYymd());
										}
									}

									bk001Sbk.setYzId(yzIds[e]);
									bk001Sbk.setRefid(gr016BkKjyw.getRefid());
									bk001Sbk.setOrderType(yzIdList.getOrderTypeName());
									bk001Sbk.setOrderName(yzIdList.getOrderName());
									bk001Sbk.setOrderAt(yzIdList.getOrderAt());
									bk001Sbk.setStopAt(yzIdList.getStopAt());
									bk001Sbk.setDose(yzIdList.getDosage());
									bk001Sbk.setDosageUnit(yzIdList.getDosageUnit());
									bk001Sbk.setAdminRouteName(yzIdList.getAdminRouteName());
									bk001Sbk.setSypc(yzIdList.getSypc());
									bk001Sbk.setDateSection(yzIdList.getDateSection());
									bk001Sbk.setIsselect(1);
									this.uu.save(bk001Sbk);
								}
							}
						}

						LoginUser arg25 = this.d(request);
						Bk001Sbk arg26 = this.bW.get(bk002Grzd.getRefid());
						if (arg26 != null) {
							arg26.setChargeDrId(chargeDrId);
							arg26.setJbzd(jbzd);
							arg26.setJbzdCode(jbzdCode);
							arg26.setReportDeptId(reportDeptId);
							arg26.setReportDeptName(reportDeptName);
							this.us.a(arg26, bk002Grzd, arg25, chargeDrId, jbzd, jbzdCode, reportDeptId,
									reportDeptName);
							result.setResult("success");
							result.setData(bk002Grzd.getRefid());
						} else {
							result = new Result("error", "参数异常");
						}

						String[] arg27 = st004BkCgxx.getYzIdList();
						this.uv.deleteByRefid(gr016BkKjyw.getRefid());
						if (arg27 != null && arg27.length > 0) {
							for (int arg28 = 0; arg28 < arg27.length; ++arg28) {
								St004BkCgxx arg29 = new St004BkCgxx();
								arg29.setYzId(arg27[arg28]);
								SimpleDateFormat arg30 = new SimpleDateFormat("yyyy-MM-dd");
								String format = arg30.format(new Date());
								arg29.setCreateDate(arg30.parse(format));
								arg29.setRefid(gr016BkKjyw.getRefid());
								this.uv.save(arg29);
							}
						}
					} catch (Exception arg23) {
						c.error("获取信息异常!", arg23);
						result = new Result("error", "获取信息异常");
					}

					this.a(response, result);
				}
			}
		} else {
			result.setResult("error");
			result.setMsg("请选择易感因素！");
			this.a(response, result);
		}
	}

	@RequestMapping({"/bk002Grzd/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String relid) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(relid)) {
				result = new Result();
				this.us.bN(relid);
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

	@RequestMapping({"/bk002Grzd/f_json/queryGrPosition"})
	@ResponseBody
	public void L(HttpServletRequest request, HttpServletResponse response) {
		List grPosition = this.us.queryGrPosition();
		this.a(response, grPosition);
	}

	@RequestMapping({"/bk002Grzd/f_json/qrxczxgxgrlcs"})
	@ResponseBody
	public void l(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
			String orderType) {
		String queryStartDate = null;
		String queryEndDate = null;
		Object myDate = new Date();
		if (ab.isNotEmpty(date)) {
			myDate = f.k(date, "yyyy/MM/dd");
		}

		if (dateType.equals("month")) {
			if ("back".equals(orderType)) {
				myDate = f.b((Date) myDate, -1);
			}

			if ("next".equals(orderType)) {
				myDate = f.b((Date) myDate, 1);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, true));
			queryEndDate = f.formatDate(f.b((Date) myDate, true));
		} else if (dateType.equals("week")) {
			if ("back".equals(orderType)) {
				myDate = f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = f.a((Date) myDate, 16);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, -8));
			queryEndDate = f.formatDate(f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.us.findqrxczxgxgrlcs(queryStartDate, queryEndDate, ificu);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/bk002Grzd/f_json/canSelOprat"})
	@ResponseBody
	public void X(HttpServletRequest request, HttpServletResponse response, String infectCode) {
		int num = this.E.findNumByInfectCode("SSI", infectCode);
		this.a(response, Integer.valueOf(num));
	}

	@RequestMapping({"/bk002Grzd/f_json/findMainInfectionParts"})
	@ResponseBody
	public void Y(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = f.p(new Date());
			if (StringUtils.isEmpty(dataRange)) {
				dataRange = "month";
			}

			Date startDate;
			if ("month".equals(dataRange)) {
				startDate = f.a(e, false);
			} else if ("quarter".equals(dataRange)) {
				startDate = f.u(e);
			} else {
				startDate = f.getYearFirst();
			}

			List list = this.us.findMainInfectionParts(startDate, e);
			result = new Result("success");
			result.setData(list);
		} catch (Exception arg7) {
			result = new Result("error", arg7.getMessage());
			arg7.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/bk002Grzd/f_json/outcomne/update"})
	@ResponseBody
	@SqlLog(p = "报卡--更新转归信息")
	public void a(HttpServletRequest request, HttpServletResponse response, Bk002Grzd bk002Grzd) {
		Result result = new Result("success");
		this.us.updateOutcome(bk002Grzd);
		this.a(response, result);
	}

	@RequestMapping({"/bk002Grzd/f_json/findMainIncidence"})
	@ResponseBody
	public void Z(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = f.h(true);
			Date startDate = f.a(f.b(f.getCurDate(), -12), true);
			LinkedList value = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.us.findMainIncidence(startDate, e);
			Iterator arg12 = list.iterator();

			while (arg12.hasNext()) {
				Map resultMap = (Map) arg12.next();
				xAxisData.add(ab.g(resultMap.get("datetime")));
				value.add(r.e(resultMap.get("value")));
			}

			series.add(value);
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			resultMap1.put("formatter", "{value}%");
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg13) {
			result = new Result("error", arg13.getMessage());
			arg13.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/bk002Grzd/f_json/findBloodInfections"})
	@ResponseBody
	public void aa(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = f.q(f.a(f.getCurDate(), -1));
			Date startDate = f.p(f.a(e, -6));
			LinkedList value = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.us.findBloodInfections(startDate, e);
			Iterator arg12 = list.iterator();

			while (arg12.hasNext()) {
				Map resultMap = (Map) arg12.next();
				xAxisData.add(f.formatDate((Date) resultMap.get("datetime")));
				value.add(Integer.valueOf(r.d(resultMap.get("value"))));
			}

			series.add(value);
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg13) {
			result = new Result("error", arg13.getMessage());
			arg13.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/bk002Grzd/f_json/upPrint"})
	@ResponseBody
	public void K(HttpServletRequest request, HttpServletResponse response, String relid, String isPrint) {
		Result result = new Result();

		try {
			Bk002Grzd e = this.us.get(relid);
			String print = this.j.findByParamCode(Param.NIS_YGBK_PRINT);
			if (e != null) {
				e.setIsPrint(isPrint);
				this.us.update(e);
				result.setResult("success");
				result.setData(print);
			} else {
				result = new Result("error", "参数异常");
			}
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}