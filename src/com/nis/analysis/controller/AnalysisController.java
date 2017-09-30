package com.nis.analysis.controller;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.entity.ZdmxWeight;
import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.analysis.model.a;
import com.nis.analysis.model.c;
import com.nis.analysis.model.d;
import com.nis.analysis.model.f;
import com.nis.analysis.service.AnalysisBcService;
import com.nis.analysis.service.AnalysisCdcBcService;
import com.nis.analysis.service.AnalysisCdcPacsService;
import com.nis.analysis.service.AnalysisCdcYzService;
import com.nis.analysis.service.AnalysisCdcZdService;
import com.nis.analysis.service.AnalysisCheckoutService;
import com.nis.analysis.service.AnalysisGzService;
import com.nis.analysis.service.AnalysisJyService;
import com.nis.analysis.service.AnalysisKjywService;
import com.nis.analysis.service.AnalysisModelService;
import com.nis.analysis.service.AnalysisResistantService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.comm.utils.z;
import com.nis.dict.entity.Zg005Yygrzd;
import com.nis.dict.service.Zg005YygrzdService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.zg.entity.Zg007Grys;
import com.nis.zg.service.Zg007GrysService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnalysisController extends BaseController {
	private static final Logger c = Logger.getLogger(AnalysisController.class);
	@Autowired
	private AnalysisBcService y;
	@Autowired
	private AnalysisJyService z;
	@Autowired
	private AnalysisCdcPacsService A;
	@Autowired
	private AnalysisCdcZdService B;
	@Autowired
	private AnalysisGzService C;
	@Autowired
	private AnalysisModelService D;
	@Autowired
	private Zg005YygrzdService E;
	@Autowired
	private Zg007GrysService F;
	@Autowired
	private AnalysisCheckoutService G;
	@Autowired
	private SysParamService j;
	@Autowired
	private AnalysisKjywService H;
	@Autowired
	private AnalysisResistantService I;
	@Autowired
	private SysJudgeLogService J;
	@Autowired
	private AnalysisCdcBcService K;
	@Autowired
	private AnalysisCdcYzService L;
	private static boolean M = false;
	private static boolean N = false;
	private static boolean O = false;
	private static boolean P = false;
	private static boolean Q = false;
	private static boolean R = false;
	private static boolean S = false;
	private static boolean T = false;
	private static boolean U = false;

	@RequestMapping({"/analysis/jy"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response) {
		Result e;
		if (R) {
			e = new Result("error", "检验预警分析进程执行中，不支持重复执行");
			this.a(response, e);
		} else {
			try {
				R = true;
				e = new Result();
				a result1 = this.z.e(false);
				e.setResult(String.valueOf(result1.b()));
				c.info("传染病检验分析执行完毕！");
				this.a(response, e);
			} catch (Exception arg7) {
				Result result = new Result("error", "传染病检验分析异常");
				c.error("传染病检验分析异常!", arg7);
				this.a(response, result);
			} finally {
				R = false;
			}

		}
	}

	@RequestMapping({"/analysis/cdcPacs"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response) {
		Result e;
		if (S) {
			e = new Result("error", "检验预警分析进程执行中，不支持重复执行");
			this.a(response, e);
		} else {
			try {
				S = true;
				e = new Result();
				a result1 = this.A.b(false);
				e.setResult(String.valueOf(result1.b()));
				if (result1.b()) {
					e.setMsg("本次传染病影像分析执行完毕！本次预警记录数：" + result1.getSucTotals());
				} else {
					e.setMsg(result1.getInfo());
				}

				c.info("传染病影像分析执行完毕！");
				this.a(response, e);
			} catch (Exception arg7) {
				Result result = new Result("error", "传染病影像分析异常");
				c.error("传染病影像分析异常!", arg7);
				this.a(response, result);
			} finally {
				S = false;
			}

		}
	}

	@RequestMapping({"/analysis/zd"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response) {
		Result e;
		if (Q) {
			e = new Result("error", "诊断预警分析进程执行中，不支持重复执行");
			this.a(response, e);
		} else {
			try {
				Q = true;
				e = new Result();
				a result1 = this.B.d(false);
				e.setResult(String.valueOf(result1.b()));
				c.info("传染病诊断分析执行完毕！");
				this.a(response, e);
			} catch (Exception arg7) {
				Result result = new Result("error", "传染病诊断分析异常");
				c.error("传染病诊断分析异常!", arg7);
				this.a(response, result);
			} finally {
				Q = false;
			}

		}
	}

	@RequestMapping({"/analysis/cdcBc"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response) {
		Result e;
		if (T) {
			e = new Result("error", "病程预警分析进程执行中，不支持重复执行");
			this.a(response, e);
		} else {
			try {
				T = true;
				e = new Result();
				a result1 = this.K.a(false);
				e.setResult(String.valueOf(result1.b()));
				if (result1.b()) {
					e.setMsg("本次传染病病程分析执行完毕！本次预警记录数：" + result1.getSucTotals());
				} else {
					e.setMsg(result1.getInfo());
				}

				c.info("传染病病程分析执行完毕！");
				this.a(response, e);
			} catch (Exception arg7) {
				Result result = new Result("error", "传染病病程分析异常");
				c.error("传染病病程分析异常!", arg7);
				this.a(response, result);
			} finally {
				T = false;
			}

		}
	}

	@RequestMapping({"/analysis/cdcYz"})
	@ResponseBody
	public void e(HttpServletRequest request, HttpServletResponse response) {
		Result e;
		if (U) {
			e = new Result("error", "医嘱预警分析进程执行中，不支持重复执行");
			this.a(response, e);
		} else {
			try {
				U = true;
				e = new Result();
				a result1 = this.L.c(false);
				e.setResult(String.valueOf(result1.b()));
				if (result1.b()) {
					e.setMsg("本次传染病医嘱分析执行完毕！本次预警记录数：" + result1.getSucTotals());
				} else {
					e.setMsg(result1.getInfo());
				}

				c.info("传染病医嘱分析执行完毕！");
				this.a(response, e);
			} catch (Exception arg7) {
				Result result = new Result("error", "传染病医嘱分析异常");
				c.error("传染病医嘱分析异常!", arg7);
				this.a(response, result);
			} finally {
				U = false;
			}

		}
	}

	@RequestMapping({"/analysis/i_adapter/infectionDiagnosis"})
	@ResponseBody
	public void g(HttpServletRequest request, HttpServletResponse response, String data) {
		Result result = new Result();
		ArrayList mapList = new ArrayList();
		Map map = (Map) l.ar(data);
		if (map != null) {
			String sex = map.get("sex").toString();
			String age = map.get("age").toString();
			String desc = map.get("desc").toString();
			Object featuresList = new ArrayList();
			if (map.get("features") != null) {
				featuresList = (List) map.get("features");
			}

			f anrs = this.C.l(desc);
			ArrayList gr018List = new ArrayList();
			Iterator modelResult = anrs.getKeyMap().entrySet().iterator();

			while (modelResult.hasNext()) {
				Entry cryxxb = (Entry) modelResult.next();
				Gr018Ysgrys weightList = new Gr018Ysgrys();
				weightList.setElementId(((d) cryxxb.getValue()).getElementid());
				weightList.setElementName(((d) cryxxb.getValue()).getElementname());
				gr018List.add(weightList);
			}

			modelResult = ((List) featuresList).iterator();

			label84 : while (modelResult.hasNext()) {
				Map arg25 = (Map) modelResult.next();
				Iterator weightMap = arg25.entrySet().iterator();

				while (true) {
					Entry arg28;
					do {
						do {
							if (!weightMap.hasNext()) {
								continue label84;
							}

							arg28 = (Entry) weightMap.next();
						} while (!((String) arg28.getKey()).equals("exceptCode"));
					} while (!ab.isNotEmpty((String) arg28.getValue()));

					String[] clementIds = ((String) arg28.getValue()).split(",");
					String[] zdmxList = clementIds;
					int reMap = clementIds.length;

					for (int arg18 = 0; arg18 < reMap; ++arg18) {
						String k = zdmxList[arg18];
						Gr018Ysgrys reason = new Gr018Ysgrys();
						reason.setElementId(k);
						gr018List.add(reason);
					}
				}
			}

			St003Cryxxb arg26 = new St003Cryxxb();
			arg26.setSex(sex);
			arg26.setAgeFw(age);
			c arg27 = this.D.a(gr018List, arg26);
			if (arg27.b()) {
				List arg29 = arg27.getZdList();
				Iterator arg31 = arg29.iterator();

				label59 : while (arg31.hasNext()) {
					Map arg30 = (Map) arg31.next();
					Iterator arg33 = arg30.entrySet().iterator();

					while (true) {
						Entry arg32;
						do {
							if (!arg33.hasNext()) {
								continue label59;
							}

							arg32 = (Entry) arg33.next();
						} while (((ZdmxWeight) arg32.getValue()).getWeight().intValue() <= 0);

						HashMap arg34 = new HashMap();
						List arg35 = ((ZdmxWeight) arg32.getValue()).getZdmxList();
						String arg36 = "";
						Iterator arg23 = arg35.iterator();

						while (arg23.hasNext()) {
							Zg006Zdmx zg005 = (Zg006Zdmx) arg23.next();
							Zg007Grys grys = this.F.get(zg005.getElementId());
							if (grys != null) {
								arg36 = arg36 + grys.getElementName() + ",";
							}
						}

						Zg005Yygrzd arg37 = this.E.get((String) arg32.getKey());
						arg34.put("diagnosis", arg37.getInfectName());
						if (((ZdmxWeight) arg32.getValue()).getWeight().intValue() > 100) {
							arg34.put("percent", "100");
						} else {
							arg34.put("percent", ((ZdmxWeight) arg32.getValue()).getWeight().toString());
						}

						arg34.put("reason", arg36);
						mapList.add(arg34);
					}
				}

				result.setData(mapList);
				result.setResult("success");
			} else {
				result.setResult("error");
			}
		}

		this.a(response, result);
	}

	@RequestMapping({"/analysis/testPost"})
	@ResponseBody
	public void f(HttpServletRequest request, HttpServletResponse response) {
		String res = this.D.d();
		if (ab.isNotEmpty(res)) {
			Map map = (Map) l.ar(res);
			this.a(response, map);
		}

	}

	@RequestMapping({"/analysis/bacteria"})
	@ResponseBody
	public void g(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		if (M) {
			result = new Result("error", "标本分析（细菌）分析进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				M = true;
				result = new Result();
				String e = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
				String id = com.nis.comm.utils.z.a(bg.my);
				SysJudgeLog sysJudgeLog = new SysJudgeLog();
				sysJudgeLog.setId(id);
				sysJudgeLog.setStartTime(new Date());
				sysJudgeLog.setJudgeCode(ae.in.getCode());
				sysJudgeLog.setStatus("0");
				this.J.save(sysJudgeLog);
				this.G.c(e, id);
				result.setResult("success");
				c.info("标本分析（细菌）执行完毕！");
			} catch (Exception arg9) {
				result = new Result("error", "标本分析（细菌）异常");
				c.error("标本分析（细菌）异常!", arg9);
			} finally {
				M = false;
			}

			this.a(response, result);
		}
	}

	@RequestMapping({"/analysis/commCheck"})
	@ResponseBody
	public void h(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		if (N) {
			result = new Result("error", "常规检查分析进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				N = true;
				result = new Result();
				String e = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
				String id = com.nis.comm.utils.z.a(bg.my);
				SysJudgeLog sysJudgeLog = new SysJudgeLog();
				sysJudgeLog.setId(id);
				sysJudgeLog.setStartTime(new Date());
				sysJudgeLog.setJudgeCode(ae.io.getCode());
				sysJudgeLog.setStatus("0");
				this.J.save(sysJudgeLog);
				this.G.d(e, id);
				result.setResult("success");
				c.info("常规检查分析执行完毕！");
			} catch (Exception arg9) {
				result = new Result("error", "常规检查分析异常");
				c.error("常规检查分析异常!", arg9);
			} finally {
				N = false;
			}

			this.a(response, result);
		}
	}

	@RequestMapping({"/analysis/kjywAnalysis"})
	@ResponseBody
	public void i(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		if (O) {
			result = new Result("error", "医嘱抗菌药物分析进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				O = true;
				result = new Result();
				String e = com.nis.comm.utils.z.a(bg.my);
				SysJudgeLog sysJudgeLog = new SysJudgeLog();
				sysJudgeLog.setId(e);
				sysJudgeLog.setStartTime(new Date());
				sysJudgeLog.setJudgeCode(ae.ik.getCode());
				sysJudgeLog.setStatus("0");
				this.J.save(sysJudgeLog);
				this.H.m(e);
				result.setResult("success");
				c.info("医嘱抗菌药物分析执行完毕！");
			} catch (Exception arg8) {
				result = new Result("error", "医嘱抗菌药物分析异常");
				c.error("医嘱抗菌药物分析异常!", arg8);
			} finally {
				O = false;
			}

			this.a(response, result);
		}
	}

	@RequestMapping({"/analysis/resisAnalysis"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Date startDate, Date endDate,
			String testOrderNo) {
		Result result = null;
		if (P) {
			result = new Result("error", "多重耐药分析进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				P = true;
				result = new Result();
				String e = com.nis.comm.utils.z.a(bg.my);
				SysJudgeLog sysJudgeLog = new SysJudgeLog();
				sysJudgeLog.setId(e);
				sysJudgeLog.setStartTime(new Date());
				sysJudgeLog.setJudgeCode(ae.ir.getCode());
				sysJudgeLog.setStatus("0");
				this.J.save(sysJudgeLog);
				this.I.a(startDate, endDate, testOrderNo, e);
				result.setResult("success");
				c.info("多重耐药分析执行完毕！");
			} catch (Exception arg11) {
				result = new Result("error", "多重耐药分析异常");
				c.error("多重耐药分析异常!", arg11);
			} finally {
				P = false;
			}

			this.a(response, result);
		}
	}
}