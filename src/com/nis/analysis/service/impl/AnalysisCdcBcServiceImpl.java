package com.nis.analysis.service.impl;

import com.nis.analysis.model.a;
import com.nis.analysis.model.d;
import com.nis.analysis.model.f;
import com.nis.analysis.service.AnalysisCdcBcService;
import com.nis.analysis.service.AnalysisCdcTextService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.cdc.service.CtgSys009YjService;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.comm.utils.v;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St008Bcjl;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St008BcjlService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalysisCdcBcServiceImpl implements AnalysisCdcBcService {
	private static final Logger c = Logger.getLogger(AnalysisCdcBcServiceImpl.class);
	@Autowired
	private Zg007DictService X;
	@Autowired
	private St008BcjlService be;
	@Autowired
	private NyUnanalyzeBcService aT;
	@Autowired
	private NyUnanalyzeDictService ba;
	@Autowired
	private AnalysisCdcTextService bf;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private St008BcjlService aP;
	@Autowired
	private CtgSys009YjService bi;
	@Autowired
	private SysDictService p;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysJudgeLogService J;

	public a a(boolean errExit) {
		a rs = new a();
		ArrayList errList = new ArrayList();
		int sucnum = 0;
		int errnum = 0;
		int totalcount = 0;

		try {
			boolean ex = false;
			TaskJob taskJob = this.aU.findByLink("analysis/cdcBc");
			if (taskJob == null || taskJob.getStatus().intValue() != 0) {
				ex = false;
				rs.setSuccess(false);
				rs.setInfo("传染病病程分析执行失败！原因：参数未启用！");
				System.err.println("传染病病程分析执行失败！原因：参数未启用！");
				return rs;
			}

			ex = true;
			String keyType = "A9B8E397C091559F";
			String dictType = "1544E1B70E4296E8";
			Map keyfd = this.X.f(keyType, dictType);
			keyType = "86A8433C4635E2ED";
			Map keykd = this.X.f(keyType, dictType);
			keyType = "0C4BC0DB8EDC474E";
			Map keyjw = this.X.f(keyType, dictType);
			keyType = "52B669C0526DBADC";
			Map keygj = this.X.f(keyType, dictType);
			keyType = "CB7C56987F20CA5F";
			Map keybw = this.X.f(keyType, dictType);
			keyType = "642D3D3B424E2D80";
			Map keysl = this.X.f(keyType, dictType);
			Map unBc = this.aT.getUnBcMap();
			Map unDict = this.ba.getUnDictMap();
			HashMap bjColor = new HashMap();
			List sysdictList = this.p.u("YJBJCYS", "0");
			Iterator rownum = sysdictList.iterator();

			while (rownum.hasNext()) {
				SysDict mapList = (SysDict) rownum.next();
				bjColor.put(mapList.getDictCode(), mapList.getDictName());
			}

			ArrayList arg33 = new ArrayList();
			arg33.add(keyfd);
			arg33.add(keykd);
			arg33.add(keyjw);
			arg33.add(keygj);
			arg33.add(keybw);
			arg33.add(keysl);
			arg33.add(unBc);
			arg33.add(unDict);
			byte arg34 = 100;
			this.bf.f(arg33);
			this.bf.a(bjColor);

			label89 : while (ex) {
				Thread.sleep(1000L);
				taskJob = this.aU.findByLink("analysis/cdcBc");
				if (taskJob != null && taskJob.getStatus().intValue() == 0) {
					ex = true;
					List list = this.be.getBcListForCDC(arg34);
					if (list != null && list.size() > 0) {
						ex = true;
						int i = 0;

						while (true) {
							if (i >= list.size()) {
								continue label89;
							}

							St008Bcjl bcjl = (St008Bcjl) list.get(i);
							if (bcjl != null && "1".equals(bcjl.getContentType())) {
								String anrs = bcjl.getCourseContent();
								if (ab.isNotEmpty(anrs)) {
									try {
										anrs = v.aI(anrs);
									} catch (Exception arg31) {
										c.debug("解析RTF原始病程失败", arg31);
										arg31.printStackTrace();
									}
								}

								bcjl.setCourseContent(anrs);
							}

							f arg35 = this.bf.b(bcjl.getBcName(), bcjl.getCourseContent());
							if (arg35.getSuccess()) {
								++totalcount;
								Map keyMap = arg35.getKeyMap();
								List yjs = this.a(true, bcjl.getZyid(), keyMap, bcjl);
								CtgSys009Yj ctgSys009Yj;
								if (!yjs.isEmpty()) {
									for (Iterator arg29 = yjs.iterator(); arg29.hasNext(); this.bi.save(ctgSys009Yj)) {
										ctgSys009Yj = (CtgSys009Yj) arg29.next();
										++sucnum;
										List exit = this.bi.isExit(ctgSys009Yj);
										if (exit != null && !exit.isEmpty()) {
											this.bi.delete(((CtgSys009Yj) exit.get(0)).getMasterid());
										}
									}
								}
							} else {
								++errnum;
								if (errExit) {
									errList.add(arg35.getErrMsg());
									continue label89;
								}

								errList.add(arg35.getErrMsg());
							}

							bcjl.setCdcanaldt(new Date());
							bcjl.setCdcanalflag(Integer.valueOf(1));
							this.aP.updateCdcAnalFlag(bcjl);
							++i;
						}
					}

					ex = false;
					break;
				}

				ex = false;
				break;
			}

			rs.setSuccess(true);
		} catch (Exception arg32) {
			errList.add(arg32.getMessage());
			rs.setInfo("传染病病程分析执行失败！原因：" + arg32.getMessage());
			System.err.println("传染病病程分析执行失败！原因：" + arg32.getMessage());
			c.error("analysisCdcBc", arg32);
		}

		rs.setErrMsgList(errList);
		rs.setSucTotals(sucnum);
		rs.setErrTotals(errnum);
		rs.setTotals(totalcount);
		return rs;
	}

	public List<CtgSys009Yj> a(boolean isZyPatient, String zymzid, Map<String, d> keymap, St008Bcjl bcjl) {
		ArrayList yjs = new ArrayList();
		if (!keymap.isEmpty()) {
			Set keySet = keymap.keySet();
			Iterator arg7 = keySet.iterator();

			while (arg7.hasNext()) {
				String k = (String) arg7.next();
				CtgSys009Yj yj = new CtgSys009Yj();
				if (isZyPatient) {
					St003Cryxxb nodeResult = this.bg.get(zymzid);
					if (nodeResult != null) {
						yj.setMasterid(af.getUUID32());
						yj.setMzzyid(nodeResult.getZyid());
						yj.setPatientId(nodeResult.getPatientId());
						yj.setPatientName(nodeResult.getPatientName());
						yj.setPatientType("ZY");
					}
				} else {
					St020ClinicPatients nodeResult1 = this.bh.get(zymzid);
					if (nodeResult1 != null) {
						yj.setMasterid(af.getUUID32());
						yj.setMzzyid(nodeResult1.getMzid());
						yj.setPatientId(nodeResult1.getPatientId());
						yj.setPatientName(nodeResult1.getPatientName());
						yj.setPatientType("MZ");
					}
				}

				d nodeResult2 = (d) keymap.get(k);
				if (nodeResult2 != null) {
					yj.setYjcode(nodeResult2.getElementid());
					yj.setYjname(nodeResult2.getElementname());
					yj.setYjdt(new Date());
					yj.setYjsource("病程");
					yj.setYjcontent(nodeResult2.getOriginalContent());
					yj.setSourceId(bcjl.getId());
				}

				if (ab.isNotEmpty(yj.getMasterid())) {
					yjs.add(yj);
				}
			}
		}

		return yjs;
	}
}