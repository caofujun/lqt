package com.nis.analysis.service.impl;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.AnalysisKjywService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.log.service.SysLogService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St006TwxxService;
import com.nis.patient.service.St012KjywService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisKjywServiceImpl implements AnalysisKjywService {
	private static final Logger c = Logger.getLogger(AnalysisKjywServiceImpl.class);
	private int bw = 0;
	private int bx = 0;
	@Autowired
	private St012KjywService bM;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private SysParamService j;
	@Autowired
	private St006TwxxService bN;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private Gm004JcmxService bP;
	@Autowired
	private Gr018YsgrysService aR;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysJudgeLogService J;
	@Autowired
	private TaskJobService aU;

	public void m(String id) {
		SysJudgeLog sysJudgeLog = this.J.get(id);
		TaskJob taskJob = this.aU.findByLink(ae.ik.getUrl());
		if (taskJob != null && taskJob.getStatus().intValue() == 50) {
			sysJudgeLog.setStatus("3");
			sysJudgeLog.setEndTime(new Date());
			this.J.update(sysJudgeLog);
		} else {
			int total = this.bu.findKjywWaitCount();
			sysJudgeLog.setTotalCount(Integer.valueOf(total));
			this.J.update(sysJudgeLog);
			Object drugLine = null;
			List dictList = this.p.u("drug_use_path_warn", (String) null);
			HashSet usePath = new HashSet();
			Iterator surDay = dictList.iterator();

			while (surDay.hasNext()) {
				SysDict useDay = (SysDict) surDay.next();
				usePath.add(useDay.getDictName());
			}

			dictList = null;
			int arg19 = r.d(this.j.findByParamCode(Param.NIS_ANA_KJYW_USE_DAY));
			int arg20 = r.d(this.j.findByParamCode(Param.NIS_ANA_KJYW_SURGERY_DAY));
			String twFever = this.j.findByParamCode(Param.NIS_TW_FR);
			Double twValue = r.e(twFever);
			List yzxxList = null;
			boolean hasMore = true;
			Integer size = Integer.valueOf(1000);
			Date submiAt = new Date();
			int index = r.d(this.j.findByParamCode(Param.NIS_ANA_PREVENT_INFINITE_LOOP));
			boolean num = false;

			while (hasMore) {
				taskJob = this.aU.findByLink(ae.ik.getUrl());
				if (taskJob != null && taskJob.getStatus().intValue() == 50) {
					sysJudgeLog.setStatus("3");
					sysJudgeLog.setEndTime(new Date());
					this.J.update(sysJudgeLog);
					hasMore = false;
				}

				--index;
				yzxxList = this.bu.findKjywWaitAnaly(size);
				int arg21 = this.bu.findKjywWaitCount();
				if (yzxxList == null || arg21 < size.intValue() || index <= 0) {
					hasMore = false;
				}

				if (yzxxList.size() > 0) {
					try {
						this.a((Integer) drugLine, yzxxList, arg19, submiAt, twValue, twFever, arg20, usePath,
								sysJudgeLog);
						this.bw += yzxxList.size();
						sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
						sysJudgeLog.setEndTime(new Date());
						this.J.update(sysJudgeLog);
					} catch (Exception arg18) {
						++this.bx;
						sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
						sysJudgeLog.setStatus("2");
						this.J.update(sysJudgeLog);
						arg18.printStackTrace();
						c.error("抗菌药物阶段分析执行异常!", arg18);
						this.aV.a(ah.iG, "抗菌药物分析异常{AnalysisKjywServiceImpl.analysisKjyw()}", id, arg18.getMessage());
					}
				}
			}

			this.bu.updKeepToWaitState();
			yzxxList = null;
			if (this.bw > sysJudgeLog.getTotalCount().intValue()) {
				this.bw = sysJudgeLog.getTotalCount().intValue();
			}

			sysJudgeLog.setSuccessCount(Integer.valueOf(this.bw));
			sysJudgeLog.setFailCount(Integer.valueOf(this.bx));
			sysJudgeLog.setEndTime(new Date());
			if (this.bx == 0) {
				sysJudgeLog.setStatus("1");
			}

			this.J.update(sysJudgeLog);
		}

	}

	private void a(Integer drugLine, List<St003Cryxxb> yzxxList, int useDay, Date submiAt, Double twValue,
			String twFever, int surDay, Set<String> usePath, SysJudgeLog sysJudgeLog) {
		Date curr = new Date();
		byte num = 0;
		boolean isWarn = false;
		List st004List = null;
		Iterator arg14 = yzxxList.iterator();

		while (true) {
			St003Cryxxb st003;
			do {
				do {
					do {
						if (!arg14.hasNext()) {
							st004List = null;
							return;
						}

						st003 = (St003Cryxxb) arg14.next();
						st003 = this.bg.get(st003.getZyid());
					} while (st003 == null);

					st004List = this.bu.findKjywByZyid(st003.getZyid());
				} while (st004List == null);
			} while (st004List.size() <= 0);

			Iterator arg16 = st004List.iterator();

			while (arg16.hasNext()) {
				St004Yzxxb st004Yzxxb = (St004Yzxxb) arg16.next();

				try {
					this.a(st003, isWarn, curr, st004Yzxxb, drugLine, useDay, submiAt, twValue, twFever, num, surDay,
							usePath);
				} catch (Exception arg18) {
					arg18.printStackTrace();
					c.error("抗菌药物分析执行异常!", arg18);
					this.aV.a(ah.iG, "抗菌药物分析异常{AnalysisKjywServiceImpl.kjywAnalysis()},zyid=" + st003.getZyid() + "id="
							+ st004Yzxxb.getId(), sysJudgeLog.getId(), arg18.getMessage());
				}
			}
		}
	}

	@Transactional(rollbackFor = {Exception.class})
	private void a(St003Cryxxb st003, boolean isWarn, Date curr, St004Yzxxb st004Yzxxb, Integer drugLine, int useDay,
			Date submiAt, Double twValue, String twFever, int num, int surDay, Set<String> usePath) {
		isWarn = false;
		st004Yzxxb.setIsKjywAna("1");
		st004Yzxxb.setAnalysisAt(curr);
		if (StringUtils.isNotBlank(st004Yzxxb.getAdminRouteName())
				&& !usePath.contains(st004Yzxxb.getAdminRouteName())) {
			this.bu.updAnalFlag(st004Yzxxb);
		} else {
			if (st003.getInHospAt() != null && st004Yzxxb.getOrderAt() != null
					&& f.c(st004Yzxxb.getOrderAt(), st003.getInHospAt()) >= 48) {
				Date operAt = this.bO.getRecentOperAt(st004Yzxxb.getZyid(), st004Yzxxb.getOrderAt());
				if (1 == st004Yzxxb.getOrderType().intValue() && operAt != null
						&& f.a(st004Yzxxb.getOrderAt(), operAt) > 3) {
					drugLine = this.bM.getDrugLine(st004Yzxxb.getOrderName());
					Date orderAt = this.bu.getOrderAtNearStart(st004Yzxxb.getZyid(), operAt, st004Yzxxb.getOrderAt());
					if (orderAt == null || orderAt != null && f.a(orderAt, operAt) > 3) {
						if (drugLine == null) {
							this.a(st004Yzxxb, submiAt, "KJ000050", "使用非限制级抗菌药物<" + st004Yzxxb.getOrderName() + ">",
									curr, "使用非限制级抗菌药物");
							isWarn = true;
						} else if (2 == drugLine.intValue()) {
							this.a(st004Yzxxb, submiAt, "KJ000001", "使用限制级抗菌药物<" + st004Yzxxb.getOrderName() + ">",
									curr, "使用限制级抗菌药物");
							isWarn = true;
						} else if (3 == drugLine.intValue()) {
							this.a(st004Yzxxb, submiAt, "KJ000005", "使用特殊级抗菌药物<" + st004Yzxxb.getOrderName() + ">",
									curr, "使用特殊级抗菌药物");
							isWarn = true;
						} else {
							this.a(st004Yzxxb, submiAt, "KJ000050", "使用非限制级抗菌药物<" + st004Yzxxb.getOrderName() + ">",
									curr, "使用非限制级抗菌药物");
							isWarn = true;
						}
					}

					if (!isWarn) {
						St004Yzxxb st004 = this.aR.c(st004Yzxxb.getZyid(), st004Yzxxb.getOrderAt());
						if (st004Yzxxb.getStopAt() == null && st003.getOutAt() != null) {
							st004Yzxxb.setStopAt(st003.getOutAt());
						}

						if (st004 != null && st004.getStopAt() != null
								&& f.a(st004Yzxxb.getOrderAt(), st004.getStopAt()) >= useDay || st004 == null) {
							if (drugLine != null && 2 == drugLine.intValue()) {
								num = this.bu.getUseUnLimitNum(st004Yzxxb.getZyid(), operAt, st004Yzxxb.getOrderAt());
								if (num > 0) {
									num = this.bu.getUseDrugNum(st004Yzxxb.getZyid(), f.a(st004Yzxxb.getOrderAt(), -3),
											st004Yzxxb.getOrderAt(), st004Yzxxb.getOrderId(), st004Yzxxb.getId());
									if (num == 0) {
										this.a(st004Yzxxb, submiAt, "KJ000001",
												"使用限制级抗菌药物<" + st004Yzxxb.getOrderName() + ">", curr, "使用限制级抗菌药物");
										isWarn = true;
									}
								}
							} else if (drugLine != null && 3 == drugLine.intValue()) {
								num = this.bu.getUseLimitNum(st004Yzxxb.getZyid(), operAt, st004Yzxxb.getOrderAt());
								if (num > 0) {
									num = this.bu.getUseDrugNum(st004Yzxxb.getZyid(), f.a(st004Yzxxb.getOrderAt(), -3),
											st004Yzxxb.getOrderAt(), st004Yzxxb.getOrderId(), st004Yzxxb.getId());
									if (num == 0) {
										this.a(st004Yzxxb, submiAt, "KJ000001",
												"使用限制级抗菌药物<" + st004Yzxxb.getOrderName() + ">", curr, "使用限制级抗菌药物");
										isWarn = true;
									}
								}
							}

							if (!isWarn) {
								num = this.aR.d(st004Yzxxb.getZyid(), st004Yzxxb.getOrderAt());
								if (num == 0) {
									num = this.bu.getDrugNumTheDay(st004Yzxxb.getZyid(), f.a(st004Yzxxb.getOrderAt()),
											f.b(st004Yzxxb.getOrderAt()));
									if (num > 3) {
										this.a(st004Yzxxb, submiAt, "KJ000060", "联合使用" + num + "联抗菌药物", curr,
												"联合使用抗菌药物");
										isWarn = true;
									}
								}
							}

							num = this.bN.getNumBeforeFever(st004Yzxxb.getZyid(), st004Yzxxb.getOrderAt(), twValue);
							if (num > 0) {
								this.a(st004Yzxxb, submiAt, "KJ000015",
										"手术后发热" + twFever + "度使用抗菌药物<" + st004Yzxxb.getOrderName() + ">", curr,
										"手术后发热" + twFever + "度使用抗菌药物");
								isWarn = true;
							}
						}
					}

					if (isWarn) {
						num = this.bP.getNumByTypeId(st004Yzxxb.getZyid(), "04", this.a(st004Yzxxb.getOrderAt()),
								this.b(st004Yzxxb.getStopAt()));
						if (num > 0) {
							this.a(st004Yzxxb, submiAt, "KJ000030", "使用泌尿道插管", curr, "使用泌尿道插管");
						}

						num = this.bP.getNumByTypeId(st004Yzxxb.getZyid(), "05", this.a(st004Yzxxb.getOrderAt()),
								this.b(st004Yzxxb.getStopAt()));
						if (num > 0) {
							this.a(st004Yzxxb, submiAt, "KJ000025", "使用中心静脉插管", curr, "使用中心静脉插管");
						}

						num = this.bP.getNumByTypeId(st004Yzxxb.getZyid(), "06", this.a(st004Yzxxb.getOrderAt()),
								this.b(st004Yzxxb.getStopAt()));
						if (num > 0) {
							this.a(st004Yzxxb, submiAt, "KJ000020", "使用呼吸机插管", curr, "使用呼吸机插管");
						}
					}
				}
			}

			this.bu.updAnalFlag(st004Yzxxb);
		}
	}

	private int a(St004Yzxxb st004Yzxxb, int useDay) {
		boolean re = false;
		Date endDate = f.b(st004Yzxxb.getOrderAt());
		Date startDate = f.a(f.a(st004Yzxxb.getOrderAt(), -useDay));
		int re1 = this.bu.getUseDrugNum(st004Yzxxb.getZyid(), startDate, endDate, st004Yzxxb.getOrderId(),
				st004Yzxxb.getId());
		if (re1 > 0) {
			return 0;
		} else {
			re1 = this.bu.getUseDrugNumNo(st004Yzxxb.getZyid(), startDate, endDate, st004Yzxxb.getOrderId());
			return re1 > 0 ? 1 : 0;
		}
	}

	private Date a(Date date) {
		return date != null ? f.a(date) : null;
	}

	private Date b(Date date) {
		return date != null ? f.b(date) : null;
	}

	private void a(St004Yzxxb st004Yzxxb, Date submiAt, String elementId, String originalContent, Date curr,
			String elementName) {
		int num = this.aR.a(st004Yzxxb.getZyid(), st004Yzxxb.getOrderAt(), elementId, "医嘱");
		if (num == 0) {
			submiAt.setTime(submiAt.getTime() + 1000L);
			Gr018Ysgrys gr018Ysgrys = new Gr018Ysgrys();
			gr018Ysgrys.setZyid(st004Yzxxb.getZyid());
			gr018Ysgrys.setDataDate(f.f(st004Yzxxb.getOrderAt(), submiAt));
			gr018Ysgrys.setElementId(elementId);
			gr018Ysgrys.setElementName(elementName);
			gr018Ysgrys.setMonitorAt(curr);
			gr018Ysgrys.setDataForm("医嘱");
			gr018Ysgrys.setOriginalContent(originalContent);
			gr018Ysgrys.setDataType("文本");
			gr018Ysgrys.setState(Integer.valueOf(0));
			gr018Ysgrys.setSjId(st004Yzxxb.getId());
			this.aR.save(gr018Ysgrys);
		}

	}
}