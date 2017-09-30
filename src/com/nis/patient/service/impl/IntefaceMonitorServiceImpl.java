package com.nis.patient.service.impl;

import com.nis.analysis.service.Gr019YsgrmxService;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.f;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.log.entity.SysLog;
import com.nis.log.service.SysLogService;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.patient.service.InterfaceMonitorService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St006TwxxService;
import com.nis.patient.service.St008BcjlService;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St012ZkjlService;
import com.nis.patient.service.St014PacsService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntefaceMonitorServiceImpl implements InterfaceMonitorService {
	@Autowired
	private SysParamService j;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private St012ZkjlService wx;
	@Autowired
	private St008BcjlService aP;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private St014PacsService bj;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St006TwxxService bN;
	@Autowired
	private Gm004JcmxService bP;
	@Autowired
	private Xn011DclymxService cn;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private Gr019YsgrmxService bV;
	@Autowired
	private SysLogService aV;
	@Autowired
	private DepService e;

	public String ai() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bg.cr((String) null);
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.c(f.y(), lastAt) + "天" + "内无新入院患者！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String aj() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bg.cs((String) null);
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.c(f.y(), lastAt) + "天" + "内无新出院患者！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String ak() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.wx.getMonitorPatientInZkxxLastAt((String) null);
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.c(f.y(), lastAt) + "天" + "内无患者转科信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String al() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.wx.getMonitorPatientOutZkxxLastAt((String) null);
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.c(f.y(), lastAt) + "小时内无转科出科患者！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String am() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			String jkDeptIds = this.j.findByParamCode(Param.NIS_JK_DEP_ID);
			Date lastAt = this.bu.getMonitorPatientYzxxLastAt((String) null);
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.c(f.y(), lastAt) + "小时内未获取到新医嘱信息！";
				return msgContent;
			}

			if (!"0".equals(jkDeptIds)) {
				String monitorDeptTime = this.j.findByParamCode(Param.NIS_INTEFACE_DEPT_MONITOR_TIME);
				List depts = this.e.getDept();
				Iterator arg7 = depts.iterator();

				while (arg7.hasNext()) {
					Dep dept = (Dep) arg7.next();
					boolean isExsit = true;
					String[] jkDeptId = jkDeptIds.split(",");
					String[] arg13 = jkDeptId;
					int arg12 = jkDeptId.length;

					for (int arg11 = 0; arg11 < arg12; ++arg11) {
						String deptId = arg13[arg11];
						if (dept.getDeptId().equals(deptId)) {
							isExsit = false;
						}
					}

					if (isExsit) {
						lastAt = this.bu.getMonitorPatientYzxxLastAt(dept.getDeptId());
						if (lastAt != null && f.c(f.y(), lastAt) > Integer.parseInt(monitorDeptTime)) {
							msgContent = dept.getDeptName() + "病区患者医嘱接口异常：" + f.a(f.y(), lastAt) + "天内未获取到新医嘱信息！";
						}
					}
				}
			}
		} catch (Exception arg14) {
			arg14.printStackTrace();
		}

		return msgContent;
	}

	public String an() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.aP.getMonitorPatientBcjlLastAt();
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex) + 1) {
				msgContent = f.c(f.y(), lastAt) + "小时内无患者新病程！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String ao() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			String jkDeptIds = this.j.findByParamCode(Param.NIS_JK_DEP_ID);
			Map lastAt = this.bE.getMonitorPatientTestLastAt((String) null);
			Map lastAt2 = this.bE.getMonitorPatientTestsLastAt((String) null);
			Date SUBMIAT = (Date) lastAt.get("SUBMIAT");
			Date TESTDATE = (Date) lastAt.get("TESTDATE");
			Date SUBMIATS = (Date) lastAt2.get("SUBMIATS");
			Date RESULTDATE = (Date) lastAt2.get("RESULTDATE");
			boolean isSUBMIAT = false;
			boolean isTESTDATE = false;
			boolean isSUBMIATS = false;
			boolean isRESULTDATE = false;
			if (SUBMIAT != null && f.c(f.y(), SUBMIAT) <= Integer.parseInt(ex) + 1) {
				if (TESTDATE != null && f.c(f.y(), TESTDATE) <= Integer.parseInt(ex) + 1) {
					if (SUBMIATS != null && f.c(f.y(), SUBMIATS) <= Integer.parseInt(ex) + 1) {
						if (RESULTDATE == null || f.c(f.y(), RESULTDATE) > Integer.parseInt(ex) + 1) {
							isRESULTDATE = true;
						}
					} else {
						isSUBMIATS = true;
					}
				} else {
					isTESTDATE = true;
				}
			} else {
				isSUBMIAT = true;
			}

			if (isSUBMIAT && isTESTDATE && isSUBMIATS && isRESULTDATE) {
				if (SUBMIAT != null) {
					msgContent = "患者检验接口异常：" + f.c(f.y(), SUBMIAT) + "小时内无患者新检验信息！";
				} else if (TESTDATE != null) {
					msgContent = "患者检验接口异常：" + f.c(f.y(), TESTDATE) + "小时内无患者新检验信息！";
				} else if (SUBMIATS != null) {
					msgContent = "患者检验接口异常：" + f.c(f.y(), SUBMIATS) + "小时内无患者新检验信息！";
				} else if (RESULTDATE != null) {
					msgContent = "患者检验接口异常：" + f.c(f.y(), RESULTDATE) + "小时内无患者新检验信息！";
				} else {
					msgContent = "患者检验接口异常：无患者新检验信息！";
				}
			} else if (!"0".equals(jkDeptIds)) {
				String monitorDeptTime = this.j.findByParamCode(Param.NIS_INTEFACE_DEPT_MONITOR_TIME);
				List depts = this.e.getDept();
				Iterator arg16 = depts.iterator();

				while (true) {
					Dep dept;
					boolean isExsit;
					do {
						if (!arg16.hasNext()) {
							return msgContent;
						}

						dept = (Dep) arg16.next();
						isExsit = true;
						String[] jkDeptId = jkDeptIds.split(",");
						String[] arg22 = jkDeptId;
						int arg21 = jkDeptId.length;

						for (int arg20 = 0; arg20 < arg21; ++arg20) {
							String deptId = arg22[arg20];
							if (dept.getDeptId().equals(deptId)) {
								isExsit = false;
							}
						}
					} while (!isExsit);

					SUBMIAT = null;
					TESTDATE = null;
					SUBMIATS = null;
					RESULTDATE = null;
					lastAt = this.bE.getMonitorPatientTestLastAt(dept.getDeptId());
					lastAt2 = this.bE.getMonitorPatientTestsLastAt(dept.getDeptId());
					if (lastAt != null) {
						SUBMIAT = (Date) lastAt.get("SUBMIAT");
						TESTDATE = (Date) lastAt.get("TESTDATE");
					}

					if (lastAt2 != null) {
						SUBMIATS = (Date) lastAt2.get("SUBMIATS");
						RESULTDATE = (Date) lastAt2.get("RESULTDATE");
					}

					isSUBMIAT = false;
					isTESTDATE = false;
					isSUBMIATS = false;
					isRESULTDATE = false;
					if (SUBMIAT != null && f.c(f.y(), SUBMIAT) <= Integer.parseInt(monitorDeptTime)) {
						if (TESTDATE != null && f.c(f.y(), TESTDATE) <= Integer.parseInt(monitorDeptTime)) {
							if (SUBMIATS != null && f.c(f.y(), SUBMIATS) <= Integer.parseInt(monitorDeptTime)) {
								if (RESULTDATE == null || f.c(f.y(), RESULTDATE) > Integer.parseInt(monitorDeptTime)) {
									isRESULTDATE = true;
								}
							} else {
								isSUBMIATS = true;
							}
						} else {
							isTESTDATE = true;
						}
					} else {
						isSUBMIAT = true;
					}

					if (isSUBMIAT && isTESTDATE && isSUBMIATS && isRESULTDATE) {
						if (SUBMIAT != null) {
							msgContent = dept.getDeptName() + "病区患者检验接口异常：" + f.a(f.y(), SUBMIAT) + "天内无患者新检验信息！";
						} else if (TESTDATE != null) {
							msgContent = dept.getDeptName() + "病区患者检验接口异常：" + f.a(f.y(), TESTDATE) + "天内无患者新检验信息！";
						} else if (SUBMIATS != null) {
							msgContent = dept.getDeptName() + "病区患者检验接口异常：" + f.a(f.y(), SUBMIATS) + "天内无患者新检验信息！";
						} else if (RESULTDATE != null) {
							msgContent = dept.getDeptName() + "病区患者检验接口异常：" + f.a(f.y(), RESULTDATE) + "天内无患者新检验信息！";
						} else {
							msgContent = dept.getDeptName() + "病区患者检验接口异常：无患者新检验信息！";
						}
					}
				}
			}
		} catch (Exception arg23) {
			arg23.printStackTrace();
		}

		return msgContent;
	}

	public String ap() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bj.getMonitorPatientPacsLastAt();
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex) + 1) {
				msgContent = f.c(f.y(), lastAt) + "小时内无患者新影像信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String aq() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bj.getMonitorPatientZkxxsLastAt();
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex) + 1) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新影像信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String ar() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bO.getMonitorPatientSsxxLastAt((String) null);
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex + 1)) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新手术信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String as() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bN.getMonitorPatientTwxxLastAt();
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新体温信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String at() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bP.getMonitorPatientTubeLastAt();
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex) + 1) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新三管分析信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String au() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.cn.getMonitorPatientMdrLastAt();
			if (lastAt != null && f.c(f.y(), lastAt) > Integer.parseInt(ex + 1)) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新多耐分析信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String av() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.aR.getMonitorPatientBcLastAt();
			if (lastAt == null) {
				msgContent = "患者病程分析程序异常：无患者新病程分析信息！";
				return msgContent;
			}

			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新病程分析信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String aw() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			Date lastAt = this.bV.getMonitorPatientGrLastAt();
			if (f.c(f.y(), lastAt) > Integer.parseInt(ex)) {
				msgContent = f.a(f.y(), lastAt) + "小时内无患者新病例预警信息！";
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return msgContent;
	}

	public String ax() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			SysLog sysLog = new SysLog();
			sysLog.setLogArea("kettle");
			sysLog.setLogType("nis_jk");
			sysLog.setQueryStartDate(f.d(f.y(), -Integer.parseInt(ex)));
			sysLog.setQueryEndDate(f.y());
			List sysLogList = this.aV.findSysLog(sysLog);
			if (sysLogList != null && sysLogList.size() > 0) {
				msgContent = "KETTLE工具获取医院数据或进行处理时有异常产生！";
				return msgContent;
			}
		} catch (Exception arg4) {
			arg4.printStackTrace();
		}

		return msgContent;
	}

	public String ay() {
		String msgContent = null;

		try {
			String ex = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_TIME);
			SysLog sysLog = new SysLog();
			sysLog.setLogArea("kettle");
			sysLog.setLogType("nis7");
			sysLog.setQueryStartDate(f.d(f.y(), -Integer.parseInt(ex)));
			sysLog.setQueryEndDate(f.y());
			List sysLogList = this.aV.findSysLog(sysLog);
			if (sysLogList != null && sysLogList.size() > 0) {
				msgContent = "KETTLE工具获取医院数据或进行处理时有异常产生！";
				return msgContent;
			}
		} catch (Exception arg4) {
			arg4.printStackTrace();
		}

		return msgContent;
	}
}