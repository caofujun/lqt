package com.nis.analysis.service.impl;

import com.nis.analysis.model.a;
import com.nis.analysis.model.d;
import com.nis.analysis.model.f;
import com.nis.analysis.service.AnalysisCdcTextService;
import com.nis.analysis.service.AnalysisCdcZdService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.cdc.service.CtgSys009YjService;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.patient.entity.St002Zdxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St003CryxxbService;
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
public class AnalysisCdcZdServiceImpl implements AnalysisCdcZdService {
	private static final Logger c = Logger.getLogger(AnalysisCdcZdServiceImpl.class);
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg007DictService X;
	@Autowired
	private NyUnanalyzeBcService aT;
	@Autowired
	private NyUnanalyzeDictService ba;
	@Autowired
	private AnalysisCdcTextService bf;
	@Autowired
	private CtgSys009YjService bi;
	@Autowired
	private St002ZdxxbService bv;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysLogService aV;

	public a d(boolean errExit) {
		a rs = new a();
		ArrayList errList = new ArrayList();
		byte sucnum = 0;
		int errnum = 0;
		int findcs = 0;

		try {
			boolean ex = false;
			TaskJob taskJob = this.aU.findByLink("analysis/zd");
			if (taskJob == null || taskJob.getStatus().intValue() != 0) {
				ex = false;
				rs.setSuccess(true);
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

			ArrayList arg37 = new ArrayList();
			arg37.add(keyfd);
			arg37.add(keykd);
			arg37.add(keyjw);
			arg37.add(keygj);
			arg37.add(keybw);
			arg37.add(keysl);
			arg37.add(unBc);
			arg37.add(unDict);
			byte arg38 = 5;
			this.bf.f(arg37);
			this.bf.a(bjColor);

			label127 : while (ex) {
				Thread.sleep(1000L);
				++findcs;
				taskJob = this.aU.findByLink("analysis/zd");
				if (taskJob != null && taskJob.getStatus().intValue() == 0) {
					ex = true;
					List list = this.bv.findZyZdList(arg38);
					List zdList = this.bh.findMzZdList(arg38);
					if (list != null && !list.isEmpty() || zdList != null && !zdList.isEmpty()) {
						ex = true;

						int i;
						String mzid;
						List exit;
						for (i = 0; i < list.size(); ++i) {
							St002Zdxxb clinicPatients = (St002Zdxxb) list.get(i);
							mzid = clinicPatients.getZyid();
							String anrs = clinicPatients.getMzid();
							String keyMap = "";
							boolean yjs = false;
							if (!ab.aM(anrs)) {
								yjs = false;
								keyMap = anrs;
							} else {
								yjs = true;
								keyMap = mzid;
							}

							f ctgSys009Yj = this.bf.b("", clinicPatients.getDiagnosisName());
							if (ctgSys009Yj.getSuccess()) {
								Map keyMap1 = ctgSys009Yj.getKeyMap();
								exit = this.a(yjs, keyMap, keyMap1);
								CtgSys009Yj ctgSys009Yj1;
								if (!exit.isEmpty()) {
									for (Iterator arg34 = exit.iterator(); arg34.hasNext(); this.bi
											.save(ctgSys009Yj1)) {
										ctgSys009Yj1 = (CtgSys009Yj) arg34.next();
										List exit1 = this.bi.isExit(ctgSys009Yj1);
										if (exit1 != null && !exit1.isEmpty()) {
											this.bi.delete(((CtgSys009Yj) exit1.get(0)).getMasterid());
										}
									}
								}

								clinicPatients.setCdcanaldt(new Date());
								clinicPatients.setCdcanalflag(Integer.valueOf(1));
								this.bv.update(clinicPatients);
							} else {
								++errnum;
								if (errExit) {
									errList.add(ctgSys009Yj.getErrMsg());
									break;
								}

								errList.add(ctgSys009Yj.getErrMsg());
							}
						}

						i = 0;

						while (true) {
							if (i >= zdList.size()) {
								continue label127;
							}

							St020ClinicPatients arg39 = (St020ClinicPatients) zdList.get(i);
							mzid = arg39.getMzid();
							f arg40 = this.bf.b("", arg39.getDiagnosisName());
							if (!arg40.getSuccess()) {
								++errnum;
								if (errExit) {
									errList.add(arg40.getErrMsg());
									continue label127;
								}

								errList.add(arg40.getErrMsg());
							} else {
								Map arg41 = arg40.getKeyMap();
								List arg42 = this.a(false, mzid, arg41);
								CtgSys009Yj arg43;
								if (!arg42.isEmpty()) {
									for (Iterator arg44 = arg42.iterator(); arg44.hasNext(); this.bi.save(arg43)) {
										arg43 = (CtgSys009Yj) arg44.next();
										exit = this.bi.isExit(arg43);
										if (exit != null && !exit.isEmpty()) {
											this.bi.delete(((CtgSys009Yj) exit.get(0)).getMasterid());
										}
									}
								}

								arg39.setCdcanaldt(new Date());
								arg39.setCdcanalflag(Integer.valueOf(1));
								this.bh.update(arg39);
							}

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
		} catch (Exception arg36) {
			errList.add(arg36.getMessage());
			c.error("analysisBc", arg36);
			rs.setSuccess(false);
		}

		rs.setErrMsgList(errList);
		rs.setSucTotals(sucnum);
		rs.setErrTotals(errnum);
		rs.setTotals(errnum + sucnum);
		return rs;
	}

	public List<CtgSys009Yj> a(boolean isZyPatient, String zymzid, Map<String, d> keymap) {
		ArrayList yjs = new ArrayList();
		if (!keymap.isEmpty()) {
			Set keySet = keymap.keySet();
			Iterator arg6 = keySet.iterator();

			while (arg6.hasNext()) {
				String k = (String) arg6.next();
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
				if (nodeResult2 != null && ab.isNotEmpty(yj.getMasterid())) {
					yj.setYjcode(nodeResult2.getElementid());
					yj.setYjname(nodeResult2.getElementname());
					yj.setYjdt(new Date());
					yj.setYjsource("诊断");
					yj.setYjcontent(nodeResult2.getOriginalContent());
					yj.setSourceId("");
				}

				if (ab.isNotEmpty(yj.getMasterid())) {
					yjs.add(yj);
				}
			}
		}

		return yjs;
	}
}