package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import com.nis.comm.utils.ab;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public enum Param {
	NIS_SYSTEM_HOSPTIAL_LICENSE("nis.system.hospital.license", "医院注册码",
			"IECsBwPAi6/o9SxMAjeIF43DcVxU2wJ2QVJkq+Ye7B9oprZ7xJ0Cqgc35FW9ZB6n27Yf77wGUn5rYe11UJzZZvcG1TBKAgVfKCZ2LFxKwKg="), NIS_MONITOR_DAY_DATETYPE(
					"nis.montior.day.datetype", "每日监测默认日期类型，支持每日多耐菌、每日手术，用逗号隔开（-1=前一天(默认)，0=当天）",
					"-1,-1"), NIS_FXZB_MAX_SCORE("nis.fxzb.max.score", "风险指标最大分数（默认8）", "8"), NIS_OPERA_RISK_RATE(
							"nis.opera.risk.rate", "手术危险等级是否按手术时间的 75 百分位计算（1=是(默认)，0=否）",
							"1"), NIS_OPERA_INPUT_REQUIRED("nis.opera.input.required",
									"手术必填字段控制（operName,operDate,operLengTime,operAtStart,operAtEnd,opedocName,asa）",
									"operName,operDate,operLengTime,operAtStart,operAtEnd,opedocName,asa"), NIS_REGISTRY_VERSION(
											"nis.registry.version", "现患率使用版本（默认2016,gx为广西版本）",
											"2016"), NIS_SB_KJYW_TS("nis.sb.kjyw.ts", "报卡上报时未勾选抗菌药物提示(默认0提示，1不提示)",
													"0"), NIS_MONITOR_INFECT_DAYS("nis.monitor.infect.days",
															"默认查看感染病例监测感染日期查询天数（默认30）", "30"), NIS_YGBK_PRINT(
																	"nis.ygbk.print", "院感报卡打印抗菌药物分页打印（默认0不分页，1分页）",
																	"0"), NIS_BL_FC_DAYS("nis.bl.fc.days",
																			"最近复查天数(默认7)", "7"), NIS_hzda_YZORDERBY(
																					"nis.hzda.yzOrderBy",
																					"医嘱、检验、病程排序规则（1=升序，0=降序）",
																					"1"), NIS_TW_FR("nis.tw.fr",
																							"体温发热温度(默认38)",
																							"38"), NIS_CGPG_TIME(
																									"nis.cgpg.time",
																									"插管未评估时间（单位小时）(默认48小时)",
																									"48"), NIS_HXJ_CGPGSHOW(
																											"nis.hxj.cgpgShow",
																											"插管评估是否预警（0=是，1=否）",
																											"1"), NIS_SQ_HOURS(
																													"nis.sq.hours",
																													"患者入院多少小时内为社区感染(默认48小时)",
																													"48"), NIS_BK_GRZDSB(
																															"nis.hzda.grzdsb",
																															"感染诊断报卡是否允许重复上报（1不允许上报，0允许）",
																															"1"), NIS_YSBC_URL(
																																	"nis.ysbc.url",
																																	"外部系统原始病程地址",
																																	"0"), NIS_PATIENT_DIS_DAYS(
																																			"nis.patient.dis.days",
																																			"默认查看病程天数（默认90）",
																																			"90"), NIS_HYGIENE_QUERY_DAYS(
																																					"nis.hygiene.query.days",
																																					"环境卫生学监测默认日期查询天数（1=是，0=否）",
																																					"7"), NIS_HYGIENE_ISENABLE_AUDIT(
																																							"nis.hygiene.isEnable.audit",
																																							"环境卫生学是否启用审核模式(1=启用，0=不启用)",
																																							"0"), NIS_PATIENT_ZY_VALUE(
																																									"nis.patient.zy.value",
																																									"患者住院号显示值自定义（住院号：zyid, 患者号：patientId）",
																																									"zyid"), NIS_PATIENT_NO_TITLE(
																																											"nis.patient.no.title",
																																											"患者病案号标签",
																																											"病案号"), NIS_PATIENT_ZY_TITLE(
																																													"nis.patient.zy.title",
																																													"患者住院号标签",
																																													"住院号"), NIS_OUT_BREAK_DAYS(
																																															"nis.out.break.days",
																																															"暴发预警图标统计显示天数(默认30)",
																																															"30"), NIS_MZBJ_OPEN_SCOPE(
																																																	"nis.mzbj.open.scope",
																																																	"医院门诊信息是否显示病程(0不显示，1显示)",
																																																	"0"), NIS_IE_FLASHPLAYER_URL(
																																																			"nis.ie.flashplayer.url",
																																																			"flashPlayer地址(默认download/install_flash_player_ax_ie_26.0.0.151.exe)",
																																																			"download/install_flash_player_ax_ie_26.0.0.151.exe"), NIS_CHROME_FLASHPLAYER_URL(
																																																					"nis.chrome.flashplayer.url",
																																																					"flashPlayer地址(默认download/install_flash_player_ppapi_26.0.0.151.exe)",
																																																					"download/install_flash_player_ppapi_26.0.0.151.exe"), NIS_CZSC_M_URL(
																																																							"nis.czsc.m.url",
																																																							"院感操作手册地址(默认download/help/nis7_m.doc)",
																																																							"download/help/nis7_m.doc"), NIS_CZSC_C_URL(
																																																									"nis.czsc.c.url",
																																																									"临床操作手册地址(默认download/help/nis7_c.doc)",
																																																									"download/help/nis7_c.doc"), NIS_GK_DEPTID(
																																																											"nis.gk.deptid",
																																																											"院感科室id",
																																																											"001"), NIS_ICU_YZ_DEP(
																																																													"nis.icu.yz.dep",
																																																													"ICU医嘱监测科室（默认是0=不监测，设置科室ID逗号分隔）",
																																																													"0"), NIS_ICU_YZ(
																																																															"nis.icu.yz",
																																																															"ICU医嘱名称",
																																																															"重症监护(不另收级别护理和一般专项护理)/小时"), NIS_ICU_YZ_ICUDEP(
																																																																	"nis.icu.yz.icudep",
																																																																	"ICU虚拟科室（默认是0=无，设置ICU科室ID）",
																																																																	"0"), NIS_ICU_DAY(
																																																																			"nis.icu.day",
																																																																			"ICU医嘱计算天数",
																																																																			"120"), NIS_XCCU_DEPTID(
																																																																					"nis.xccu.deptid",
																																																																					"心外科ICU的科室id",
																																																																					"XCCUID"), NIS_XCCU_DEPTNAME(
																																																																							"nis.xccu.deptname",
																																																																							"心外科ICU的科室名称",
																																																																							"XCCU名称"), NIS_NICU_DEPTID(
																																																																									"nis.nccu.deptid",
																																																																									"NICU的科室id",
																																																																									"NICUID"), NIS_NICU_DEPTNAME(
																																																																											"nis.nccu.deptname",
																																																																											"NICU的科室名称",
																																																																											"NICU名称"), NIS_CCU_DEPTID(
																																																																													"nis.ccu.deptid",
																																																																													"CCU的科室id",
																																																																													"CCUID"), NIS_CCU_DEPTNAME(
																																																																															"nis.ccu.deptname",
																																																																															"CCU的科室名称",
																																																																															"CCU名称"), NIS_BK_IS_YGYS_REQUIRED(
																																																																																	"nis.bk.is.ygys.required",
																																																																																	"报卡是否易感因素必填(0,非必填，1，必填)",
																																																																																	"0"), NIS_BK_IS_INCISION_REQUIRED(
																																																																																			"nis.bk.is.incision.required",
																																																																																			"报卡选择手术部位感染后，切口是否必填(0,非必填，1，必填)",
																																																																																			"0"), NIS_BK_IS_SSMC_REQUIRED(
																																																																																					"nis.bk.is.ssmc.required",
																																																																																					"报卡选择手术部位感染后，手术名称是否必填(0,非必填，1，必填)",
																																																																																					"0"), NIS_BK_IS_SSMC_OPEN(
																																																																																							"nis.bk.is.ssmc.open",
																																																																																							"报卡感染诊断不为手术感染时，手术名称及切口类型是否开放(0,不开放，1，开放)",
																																																																																							"0"), NIS_BK_IS_CGSY_OPEN(
																																																																																									"nis.bk.is.cgsy.open",
																																																																																									"报卡上报时是否开放侵入性操作(0,不开放，1，开放)",
																																																																																									"0"), NIS_DOC_DEFAULT_PWD(
																																																																																											"nis.doc.default.pwd",
																																																																																											"临床端医生默认密码(默认888888)",
																																																																																											"888888"), NIS_SY_PATIENT_LIST(
																																																																																													"nis.sy.patient.list",
																																																																																													"临床是否显示首页患者列表（1=显示，0=不显示）",
																																																																																													"0"), NIS_SY_DEPT_QX(
																																																																																															"nis.sy.dept.qx",
																																																																																															"临床切换科室权限（0=本科室，1=住院，2=门诊，3=住院门诊）",
																																																																																															"0"), NIS_ZYBL_PGCZ(
																																																																																																	"nis.zybl.pgcz",
																																																																																																	"临床端能否进行职业暴露评估及处置和暴露后预防性治疗方案填写（0不允许，1允许填写职业暴露评估及处置，2都允许）",
																																																																																																	"0"), NIS_ZYBL_CAN_SEE_OTHERS_DEPT(
																																																																																																			"nis.zybl.can.see.others.dept",
																																																																																																			"可以查看其他人员上报的职业暴露的科室编号（默认为空）",
																																																																																																			""), NIS_ZYBL_QM(
																																																																																																					"nis.zybl.qm",
																																																																																																					"职业暴露签名（0=不签名，1=签名）",
																																																																																																					"0"), NIS_ZYBL_JB(
																																																																																																							"nis.zybl.jb",
																																																																																																							"职业暴露级别与验证程度未勾选患者HIV阳性患者是否显示（0=显示，1不显示）",
																																																																																																							"0"), NIS_ZYBL_LC(
																																																																																																									"nis.zybl.lc",
																																																																																																									"职业暴露流程显示(0=都显示，1=显示应急处理步骤，2=显示职业暴露处理流程)",
																																																																																																									"0"), NIS_PMS_ROLE_DOCTOR_CODE(
																																																																																																											"nis.pms.role.doctor.code",
																																																																																																											"临床端医生角色默认编号",
																																																																																																											"d897a7d387a34e389e0b71013a6dbe4d"), NIS_MDRO_ZHONG(
																																																																																																													"nis.mdro.zhong",
																																																																																																													"临床多耐菌列表是否只查询重点菌（0，不查重点菌，1，只查重点菌）",
																																																																																																													"0"), NIS_JK_SYNC_DAY(
																																																																																																															"nis.jk.sync.day",
																																																																																																															"中间库同步数据到业务库最大数据天数(默认30)",
																																																																																																															"60"), NIS_GLY_ID(
																																																																																																																	"nis.gly.id",
																																																																																																																	"系统管理员ID",
																																																																																																																	"admin"), NIS_GLY_NAME(
																																																																																																																			"nis.gly.name",
																																																																																																																			"系统管理员名称",
																																																																																																																			"管理员"), NIS_SYSTEM_THEME_STYLE(
																																																																																																																					"nis.system.theme.style",
																																																																																																																					"系统主题设置(默认default)",
																																																																																																																					"default"), NIS_SYSTEM_LOG_DAYS(
																																																																																																																							"nis.system.log.days",
																																																																																																																							"系统日志文件保留天数(默认30天)",
																																																																																																																							"30"), NIS_IMC_IS_SHOW(
																																																																																																																									"nis.imc.is.show",
																																																																																																																									"系统是否提示IMC要下载(1=不提示，0=提示)",
																																																																																																																									"1"), NIS_IS_SHOW_MSG_BOX(
																																																																																																																											"nis.is.show.msg.box",
																																																																																																																											"系统是否启用消息盒子(1=启用，0=不启用)",
																																																																																																																											"0"), NIS_CLINIC_REPORT_DEPT_SELECT(
																																																																																																																													"nis.clinic.report.dept.select",
																																																																																																																													"临床端报表功能是否允许选择科室(1=允许，0=不允许(默认0))",
																																																																																																																													"0"), NIS_SYS_ZB_URL(
																																																																																																																															"nis.sys.zb.url",
																																																																																																																															"系统直报地址(http://zb.yygr.cn:50050/niszb/data/server/receive.shtml)",
																																																																																																																															"http://zb.yygr.cn:50050/niszb/data/server/receive.shtml"), NIS_XHL_ZB_URL(
																																																																																																																																	"nis.xhl.zb.url",
																																																																																																																																	"现患率直报地址(http://zb.yygr.cn:50050/niszb/xhl/server/receive.shtml)",
																																																																																																																																	"http://zb.yygr.cn:50050/niszb/xhl/server/receive.shtml"), NIS_SYS_ZB_CHANNEL(
																																																																																																																																			"nis.sys.zb.channel",
																																																																																																																																			"系统直报渠道码",
																																																																																																																																			"00000000"), NIS_SYS_DN_ZJ(
																																																																																																																																					"nis.sys.dn.zj",
																																																																																																																																					"多耐是否计算中介默认0（0、不计算；1、计算）",
																																																																																																																																					"0"), NIS_MSG_OCX_URL(
																																																																																																																																							"nis.msg.ocx.url",
																																																																																																																																							"系统消息的ocx下载地址",
																																																																																																																																							"download/lqtMessage/"), NIS_MSG_TOKEN(
																																																																																																																																									"nis.msg.token",
																																																																																																																																									"系统消息会话的token验证(默认nis)",
																																																																																																																																									"nis"), NIS_MSG_URL(
																																																																																																																																											"nis.msg.url",
																																																																																																																																											"系统消息的接口的调用地址",
																																																																																																																																											"nis7/nis/f_adapter/msg.shtml?adapterType=getMsgByUser&data="), NIS_MSG_REFRESH_TIME(
																																																																																																																																													"nis.msg.refresh.time",
																																																																																																																																													"系统消息刷新时间(默认10000毫秒)",
																																																																																																																																													"10000"), TASK_MAIN_CRON(
																																																																																																																																															"task.main.cron",
																																																																																																																																															"系統主线程的时间表达式（0/30 * * * * ?）",
																																																																																																																																															"0/30 * * * * ?"), JOBLOG_CLEAN_CRON(
																																																																																																																																																	"joblog.clean.cron",
																																																																																																																																																	"系統定時器清空调度记录表达式（0 0 23 * * ?）",
																																																																																																																																																	"0 0 23 * * ?"), JOBLOG_SAVE_DAY(
																																																																																																																																																			"joblog.save.day",
																																																																																																																																																			"系統定時器调度记录保存天数（默认15）",
																																																																																																																																																			"15"), JOBLOG_SQL_SAVE_DAY(
																																																																																																																																																					"joblog.sql.save.day",
																																																																																																																																																					"系統定時器调度SQL日志记录保存天数（默认3）",
																																																																																																																																																					"3"), NIS_BROWSER_DOWNLOAD_URL(
																																																																																																																																																							"nis.browser.download.url",
																																																																																																																																																							"系統标准浏览器下载地址(默认download/chrome_49.exe)",
																																																																																																																																																							"download/chrome_49.exe"), NIS_BROWSER_IE8X86_DOWNLOAD_URL(
																																																																																																																																																									"nis.browser.ie8x86.download.url",
																																																																																																																																																									"系統标准浏览器下载地址(默认/download/ie8_x86.exe)",
																																																																																																																																																									"/download/ie8_x86.exe"), NIS_BROWSER_IE8X64_DOWNLOAD_URL(
																																																																																																																																																											"nis.browser.ie8x64.download.url",
																																																																																																																																																											"系統标准浏览器下载地址(默认/download/ie8_x64.exe)",
																																																																																																																																																											"/download/IE8-WindowsXP-x86-CHS.exe"), NIS_PMS_USER_SETPWD_TIMEOUT(
																																																																																																																																																													"nis.pms.user.setpwd.timeout",
																																																																																																																																																													"系统链接超时时间(默认72)",
																																																																																																																																																													"72"), NIS_IS_SHOW_INTERFACE_MSG_ALERT(
																																																																																																																																																															"nis.is.show.interface.msg.alert",
																																																																																																																																																															"系统是否启用接口消息弹窗（1=启用，0=不启用）",
																																																																																																																																																															"0"), NIS_JK_DEP_ID(
																																																																																																																																																																	"nis.jk.dept.id",
																																																																																																																																																																	"接口监测监控是否按科室级别监控（1=是，0=否）",
																																																																																																																																																																	"0"), NIS_INTEFACE_MONITOR_SCOPE(
																																																																																																																																																																			"nis.inteface.monitor.scope",
																																																																																																																																																																			"接口监控范围(默认inHosp,outHosp,inTransfer,outTransfer,emr,operation,order,pacs,test,nurse,tube,mdr,course,monitor,kettle_jk,kettle_nis)",
																																																																																																																																																																			"inHosp,outHosp,inTransfer,outTransfer,emr,operation,order,pacs,test,nurse,tube,mdr,course,monitor,kettle_jk,kettle_nis"), NIS_INTEFACE_MONITOR_MSG_TYPE(
																																																																																																																																																																					"nis.inteface.monitor.msg.type",
																																																																																																																																																																					"接口监控报警方式(local，本地;remote，远程)",
																																																																																																																																																																					"local"), NIS_INTEFACE_MONITOR_TIME(
																																																																																																																																																																							"nis.inteface.monitor.time",
																																																																																																																																																																							"接口预警有效时间（单位小时）(默认24小时)",
																																																																																																																																																																							"24"), NIS_INTEFACE_MONITOR_VERSION(
																																																																																																																																																																									"nis.inteface.monitor.version",
																																																																																																																																																																									"接口预警版本(默认7)",
																																																																																																																																																																									"7"), NIS_INTEFACE_DEPT_MONITOR_TIME(
																																																																																																																																																																											"nis.inteface.dept.monitor.time",
																																																																																																																																																																											"接口按科室预警有效时间(默认48小时)",
																																																																																																																																																																											"48"), NIS_INTEFACE_MONITOR_REMOTE_URL(
																																																																																																																																																																													"nis.inteface.remote.url",
																																																																																																																																																																													"接口监控信息远程发送地址",
																																																																																																																																																																													"http://www.yygr.net/nis/f_inteface/monitor/msg.shtml"), NIS_MIN_WEIGHT(
																																																																																																																																																																															"nis.min.weight",
																																																																																																																																																																															"预警最小权值，低于不预警出来(默认30)",
																																																																																																																																																																															"30"), NIS_YJ_SSGR(
																																																																																																																																																																																	"nis.YJ.SSGR",
																																																																																																																																																																																	"预警手术类感染是否判断患者存在手术(默认0=判断，1=不判断)",
																																																																																																																																																																																	"0"), NIS_ANA_KJYW_USE_DAY(
																																																																																																																																																																																			"nis.ana.kjyw.use.day",
																																																																																																																																																																																			"预警抗菌药物间隔天数(默认3)",
																																																																																																																																																																																			"3"), NIS_ANA_KJYW_SURGERY_DAY(
																																																																																																																																																																																					"nis.ana.kjyw.surgery.day",
																																																																																																																																																																																					"预警抗菌药物手术与医嘱间隔天数(默认3)",
																																																																																																																																																																																					"3"), NIS_ANA_PREVENT_INFINITE_LOOP(
																																																																																																																																																																																							"nis.ana.prevent.infinite.loop",
																																																																																																																																																																																							"预警分析查询次数限制（防死循环）(默认1000)",
																																																																																																																																																																																							"1000"), NIS_YJ_IS_VERSION(
																																																																																																																																																																																									"nis.yj.is.version",
																																																																																																																																																																																									"预警功能测试与正式切换(研发测试用)",
																																																																																																																																																																																									""), NIS_YJ_IS_PACS(
																																																																																																																																																																																											"nis.yj.is.pacs",
																																																																																																																																																																																											"预警影像权重是否计算到预警模型(0，计算;1，不计算)",
																																																																																																																																																																																											"0"), NIS_HOSPITAL_GBCODE(
																																																																																																																																																																																													"nis.hosptal.gbcode",
																																																																																																																																																																																													"传染病当前医院所在国标码(参考CTG_SYS004_DICTADDRAREA表)",
																																																																																																																																																																																													"43010504"), NIS_CDC_MIN_AGE(
																																																																																																																																																																																															"nis.cdc.min.age",
																																																																																																																																																																																															"传染病需填写家长姓名的最大年龄(默认值14)",
																																																																																																																																																																																															"14"), NIS_CDC_IFCHECK_BLFL2(
																																																																																																																																																																																																	"nis.cdc.ifcheck.blfl2",
																																																																																																																																																																																																	"传染病报卡中是否需要验证病例分类2(1=是，0=否)",
																																																																																																																																																																																																	"0"), NIS_CDC_EMPTY_CARD_GENERATE_INFO(
																																																																																																																																																																																																			"nis.cdc.empty.card.generate.info",
																																																																																																																																																																																																			"传染病相关报卡空卡上报时是否生成部分信息(1=是，0=否)",
																																																																																																																																																																																																			"1"), NIS_CDC_CAN_FILL_BASE_DEATH_CAUSE(
																																																																																																																																																																																																					"nis.cdc.can.fill.base.death.cause",
																																																																																																																																																																																																					"传染病死因报卡临床端是否可以填写根本死因(1=是，0=否)",
																																																																																																																																																																																																					"1"), NIS_CDC_DEATH_CARD_IS_FAMILY_INFO_NECESSARY(
																																																																																																																																																																																																							"nis.cdc.death.card.is.family.info.necessary",
																																																																																																																																																																																																							"传染病死因报卡是否必填家属相关信息(1=是，0=否)",
																																																																																																																																																																																																							"0"), NIS_CDC_IS_SHOW_PRINT_TAG(
																																																																																																																																																																																																									"nis.cdc.is.show.print.tag",
																																																																																																																																																																																																									"传染病公卫报卡是否显示已打印标识(1=显示，0=不显示)",
																																																																																																																																																																																																									"1"), NIS_CRB_BK(
																																																																																																																																																																																																											"nis.crb.bk",
																																																																																																																																																																																																											"传染病报卡是否显示删除和退卡(1=显示，0=不显示)",
																																																																																																																																																																																																											"1"), NIS_CDC_IS_ALLOW_CLINIC_PRINT(
																																																																																																																																																																																																													"nis.cdc.is.allow.clinic.print",
																																																																																																																																																																																																													"传染病是否允许临床医生打印报卡(1=是，0=否)",
																																																																																																																																																																																																													"0"), NIS_IS_SHOW_CDC_EWLOGO(
																																																																																																																																																																																																															"nis.is.show.cdc.ewlogo",
																																																																																																																																																																																																															"传染病预警图标是否在病人列表中显示(1=显示，0=不显示)",
																																																																																																																																																																																																															"0"), NIS_CDC_GENERATE_DC_SERIAL_NUMBER(
																																																																																																																																																																																																																	"nis.cdc.generate.dc.serial.number",
																																																																																																																																																																																																																	"传染病是否启用死因报卡流水号生成功能(1=启用，0=不启用)",
																																																																																																																																																																																																																	"1"), NIS_CDC_GDSN_ORG_CODE(
																																																																																																																																																																																																																			"nis.cdc.generate.dc.org.code",
																																																																																																																																																																																																																			"传染病死因报卡流水号中的机构编号(默认值9999999)",
																																																																																																																																																																																																																			"9999999"), NIS_CDC_GDSN_NEXT_NUMBER(
																																																																																																																																																																																																																					"nis.cdc.gdsn.next.number",
																																																																																																																																																																																																																					"传染病死因报卡流水号中的起始编号(默认值1)",
																																																																																																																																																																																																																					"1"), NIS_CDC_GDSN_RECORD_YEAR(
																																																																																																																																																																																																																							"nis.cdc.gdsn.record.year",
																																																																																																																																																																																																																							"传染病死因报卡流水号中的年份(默认值2017)",
																																																																																																																																																																																																																							"2017"), NIS_CRB_FJHZZD(
																																																																																																																																																																																																																									"nis.crb.fjhzzd",
																																																																																																																																																																																																																									"传染病转诊单（肺结核）是否开启填写与打印（ 0:开启，1:不开启）",
																																																																																																																																																																																																																									"0"), NIS_CRB_ZB(
																																																																																																																																																																																																																											"nis.crb.zb",
																																																																																																																																																																																																																											"传染病导出直报文件按钮是否显示（ 0:显示，1:不显示）",
																																																																																																																																																																																																																											"0"), NIS_CDC_OPEN_REPORT(
																																																																																																																																																																																																																													"nis.cdc.open.report",
																																																																																																																																																																																																																													"传染病是否开放管理端预警、疑似菜单下的代报功能(1=启用，0=不启用)",
																																																																																																																																																																																																																													"1"), NIS_CDC_YG_BT(
																																																																																																																																																																																																																															"nis.cdc.yg.bt",
																																																																																																																																																																																																																															"传染病乙肝附卡项目是否必填（1=必填，0=非必填）",
																																																																																																																																																																																																																															"0"), NIS_CDC_INTERFACE_IS_RBA_ENABLED(
																																																																																																																																																																																																																																	"nis.cdc.interface.is.rba.enabled",
																																																																																																																																																																																																																																	"传染病病例上报接口是否启用待报功能(1=启用，0=不启用)",
																																																																																																																																																																																																																																	"0"), NIS_CDC_RD_VALUE_FROM_DOCTOR(
																																																																																																																																																																																																																																			"nis.cdc.rd.value.from.doctor",
																																																																																																																																																																																																																																			"传染病报卡上报科室值是否取当前科室(1=取当前科室，0=不取当前科室)",
																																																																																																																																																																																																																																			"0"), NIS_CDC_IS_RPR_NECESSARY(
																																																																																																																																																																																																																																					"nis.cdc.rpr.is.necessary",
																																																																																																																																																																																																																																					"传染病报卡中RPR是否必填（1=必填，0=非必填）",
																																																																																																																																																																																																																																					"1"), NIS_MSG_DD_URL(
																																																																																																																																																																																																																																							"nis.msg.dd.url",
																																																																																																																																																																																																																																							"消息调用钉钉的webservice的url",
																																																																																																																																																																																																																																							"0"), NIS_MSG_DD_APPID(
																																																																																																																																																																																																																																									"nis.msg.dd.appid",
																																																																																																																																																																																																																																									"消息调用钉钉的webservice的appid",
																																																																																																																																																																																																																																									"0"), NIS_MSG_DD_QNAME(
																																																																																																																																																																																																																																											"nis.msg.dd.qname",
																																																																																																																																																																																																																																											"消息调用钉钉的webservice的qname",
																																																																																																																																																																																																																																											"0"), NIS_MSG_DD_COLOR(
																																																																																																																																																																																																																																													"nis.msg.dd.color",
																																																																																																																																																																																																																																													"消息调用钉钉的webservice的color",
																																																																																																																																																																																																																																													"0"), NIS_MSG_WEBCATE_APPID(
																																																																																																																																																																																																																																															"nis.msg.webCat.appid",
																																																																																																																																																																																																																																															"消息调用微信的webservice的appid",
																																																																																																																																																																																																																																															"0"), NIS_MSG_WEBCATE_URL(
																																																																																																																																																																																																																																																	"nis.msg.webCat.url",
																																																																																																																																																																																																																																																	"消息调用微信的webservice的url",
																																																																																																																																																																																																																																																	"0"), NIS_GRYJ_QZ_VALUE(
																																																																																																																																																																																																																																																			"nis.gryj.qz.value",
																																																																																																																																																																																																																																																			"感染预警显示的权重值",
																																																																																																																																																																																																																																																			"80"), NIS_PROJECT_NAME(
																																																																																																																																																																																																																																																					"nis.project.name",
																																																																																																																																																																																																																																																					"院感系统访问工程名称(默认nis7)",
																																																																																																																																																																																																																																																					"nis7"), NIS_INFO_TICKET(
																																																																																																																																																																																																																																																							"nis.infon.ticket",
																																																																																																																																																																																																																																																							"外部系统调用Ticket验证(默认nis)",
																																																																																																																																																																																																																																																							"nis"), NIS_REPORT_URL(
																																																																																																																																																																																																																																																									"nis.report.url",
																																																																																																																																																																																																																																																									"报表文件服务器地址（默认WebReport/ReportServer?reportlet=）",
																																																																																																																																																																																																																																																									"WebReport/ReportServer?reportlet="), NIS_HTTP_URL(
																																																																																																																																																																																																																																																											"nis.http.url",
																																																																																																																																																																																																																																																											"NIS http 项目地址",
																																																																																																																																																																																																																																																											"0"), NIS_DN_JUDGE_DAY(
																																																																																																																																																																																																																																																													"nis.dn.judge.day",
																																																																																																																																																																																																																																																													"多耐计算天数（默认最近30天）",
																																																																																																																																																																																																																																																													"30"), NIS_DN_ALL_YIN_EXCL(
																																																																																																																																																																																																																																																															"nis.dn.all.yin.excl",
																																																																																																																																																																																																																																																															"多耐分析st010全阴性排除",
																																																																																																																																																																																																																																																															"革兰,白细胞,上皮细胞"), NIS_IMAGE_UPLOAD_SAVE_DIR(
																																																																																																																																																																																																																																																																	"nis.image.upload.save.dir",
																																																																																																																																																																																																																																																																	"图片上传服务器保存目录(院内不需要配置)",
																																																																																																																																																																																																																																																																	"D://csm_photo"), NIS_SMS_SERVER_URL(
																																																																																																																																																																																																																																																																			"nis.sms.server.url",
																																																																																																																																																																																																																																																																			"系统发送短信服务器地址(院内不需要配置)",
																																																																																																																																																																																																																																																																			"http://csmsms.91160.cn/index.php?token=9b7725b8429e4673a5518875c747a743"), NIS_DOC_UPLOAD_SAVE_DIR(
																																																																																																																																																																																																																																																																					"nis.doc.upload.save.dir",
																																																																																																																																																																																																																																																																					"NIS文件上传服务器保存目录",
																																																																																																																																																																																																																																																																					"/apps/file/nis7_doc"), OPEN_OFFICE_HOME(
																																																																																																																																																																																																																																																																							"nis.open.office.home",
																																																																																																																																																																																																																																																																							"OPEN_OFFICE_HOME 目录(文档预览)",
																																																																																																																																																																																																																																																																							"C:/Program Files (x86)/OpenOffice 4/"), OPEN_OFFICE_HOME_PORT(
																																																																																																																																																																																																																																																																									"nis.open.office.home.port",
																																																																																																																																																																																																																																																																									"OPEN_OFFICE_HOME 端口",
																																																																																																																																																																																																																																																																									"8100"), NIS_DOC_FORMAT(
																																																																																																																																																																																																																																																																											"nis.doc.format",
																																																																																																																																																																																																																																																																											"上传文档支持的格式",
																																																																																																																																																																																																																																																																											"doc;ppt;xls;docx;pptx;xlsx;pdf;txt"), NIS_HHC_IS_NEED_INPUT_ERR_REASON(
																																																																																																																																																																																																																																																																													"nis.hhc.is.need.input.err.reason",
																																																																																																																																																																																																																																																																													"当手卫生调查不正确时是否需要输入不正确原因（1=是，0=否）",
																																																																																																																																																																																																																																																																													"1"), NIS_HHC_IS_ERR_REASON_NECESSARY(
																																																																																																																																																																																																																																																																															"nis.hhc.is.err.reason.necessary",
																																																																																																																																																																																																																																																																															"当手卫生调查不正确时是否必填不正确原因（1=是，0=否）",
																																																																																																																																																																																																																																																																															"1"), NIS_HHC_IS_QX(
																																																																																																																																																																																																																																																																																	"nis.hhc.is.qx",
																																																																																																																																																																																																																																																																																	"临床手卫生调查权限（0=查看本科室，1=查看本人填写）",
																																																																																																																																																																																																																																																																																	"0"), NIS_JCRB_IS_KFJC(
																																																																																																																																																																																																																																																																																			"nis.jcrb.is.kfjc",
																																																																																																																																																																																																																																																																																			"监测日报中是否展示医务人员的咳嗽腹泻和病人的咳嗽腹泻（0：不展示，1：展示）",
																																																																																																																																																																																																																																																																																			"0"), NIS_JCRB_IS_TSY(
																																																																																																																																																																																																																																																																																					"nis.jcrb.is.tsy",
																																																																																																																																																																																																																																																																																					"(新郑市人民医院)监测日报中是否展示退烧药医嘱（0：不展示，1：展示）",
																																																																																																																																																																																																																																																																																					"0"), NIS_ZYBL_ZJCLYJ(
																																																																																																																																																																																																																																																																																							"nis.zybl.zjclyj",
																																																																																																																																																																																																																																																																																							"职业暴露专家处理意见",
																																																																																																																																																																																																																																																																																							""), NIS_TASK_ZYBL_SENDMESSAGE(
																																																																																																																																																																																																																																																																																									"nis.task.zybl.sendmessage",
																																																																																																																																																																																																																																																																																									"职业暴露复查短信提醒天数（默认3天）",
																																																																																																																																																																																																																																																																																									"3"), NIS_ZYBL_XLZXSYJ(
																																																																																																																																																																																																																																																																																											"nis.zybl.xlzxsyj",
																																																																																																																																																																																																																																																																																											"职业暴露心理咨询师意见",
																																																																																																																																																																																																																																																																																											""), NIS_ZDMX_XSRGR(
																																																																																																																																																																																																																																																																																													"nis.zdmx.xsrgr",
																																																																																																																																																																																																																																																																																													"新生儿感染是否直接预警为医院感染（0：是，1：否）",
																																																																																																																																																																																																																																																																																													"0"), NIS_JCJ_JCJSH(
																																																																																																																																																																																																																																																																																															"nis.jcj.jcjsh",
																																																																																																																																																																																																																																																																																															"检验科审核菌功能是否开启(0:不开启，1:开启)",
																																																																																																																																																																																																																																																																																															"0");

	private String code;
	private String name;
	private String value;
	private static List<KvEntity> list = new ArrayList();

	public static List<KvEntity> getList() {
		if (list.size() > 0) {
			return list;
		} else {
			EnumSet set = EnumSet.allOf(Param.class);
			Iterator arg1 = set.iterator();

			while (arg1.hasNext()) {
				Param p = (Param) arg1.next();
				list.add(new KvEntity(p.getCode(), p.getName()));
			}

			return list;
		}
	}

	public static List<KvEntity> likeList(String q) {
		if (list.size() > 0) {
			return list;
		} else {
			EnumSet set = EnumSet.allOf(Param.class);
			Iterator arg2 = set.iterator();

			while (true) {
				Param p;
				label27 : do {
					while (arg2.hasNext()) {
						p = (Param) arg2.next();
						if (ab.isNotEmpty(q)) {
							continue label27;
						}

						list.add(new KvEntity(p.getCode(), p.getName()));
					}

					return list;
				} while (p.getName().indexOf(q) <= -1 && p.getCode().indexOf(q) <= -1);

				list.add(new KvEntity(p.getCode(), p.getName()));
			}
		}
	}

	private Param(String code, String name, String value) {
		this.code = code;
		this.name = name;
		this.value = value;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}