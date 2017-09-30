package com.nis.analysis.service.impl;

import com.nis.analysis.entity.Gr018Bcgrys;
import com.nis.analysis.model.a;
import com.nis.analysis.model.d;
import com.nis.analysis.model.f;
import com.nis.analysis.service.AnalysisCdcPacsService;
import com.nis.analysis.service.AnalysisCdcTextService;
import com.nis.analysis.service.Gr018BcgrysService;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.analysis.service.Zg007DictService;
import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.cdc.service.CtgSys009YjService;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.entity.Gr018Ysgrys;
import com.nis.monitor.service.Gr018YsgrysService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St008Bcjl;
import com.nis.patient.entity.St014Pacs;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St008BcjlService;
import com.nis.patient.service.St014PacsService;
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
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalysisCdcPacsServiceImpl implements AnalysisCdcPacsService {
	private static final Logger c = Logger.getLogger(AnalysisCdcPacsServiceImpl.class);
	@Autowired
	private Zg007DictService X;
	@Autowired
	private St008BcjlService be;
	@Autowired
	private St014PacsService bj;
	@Autowired
	private Gr018BcgrysService bk;
	@Autowired
	private Gr018YsgrysService bl;
	@Autowired
	private St014PacsService aS;
	@Autowired
	private CtgSys009YjService bi;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private NyUnanalyzeBcService aT;
	@Autowired
	private NyUnanalyzeDictService ba;
	@Autowired
	private AnalysisCdcTextService bf;
	@Autowired
	private SysDictService p;
	@Autowired
	private TaskJobService aU;

	public a b(boolean errExit) {
		a rs = new a();
		ArrayList errList = new ArrayList();
		byte sucnum = 0;
		int errnum = 0;
		int findcs = 0;

		try {
			boolean ex = false;
			TaskJob taskJob = this.aU.findByLink("analysis/cdcPacs");
			if (taskJob == null || taskJob.getStatus().intValue() != 0) {
				ex = false;
				rs.setSuccess(false);
				rs.setInfo("传染病影像分析执行失败！原因：参数未启用！");
				System.err.println("传染病影像分析执行失败！原因：参数未启用！");
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

			ArrayList arg36 = new ArrayList();
			arg36.add(keyfd);
			arg36.add(keykd);
			arg36.add(keyjw);
			arg36.add(keygj);
			arg36.add(keybw);
			arg36.add(keysl);
			arg36.add(unBc);
			arg36.add(unDict);
			byte arg37 = 20;
			this.bf.f(arg36);
			this.bf.a(bjColor);

			label81 : while (ex) {
				Thread.sleep(1000L);
				++findcs;
				taskJob = this.aU.findByLink("analysis/cdcPacs");
				if (taskJob != null && taskJob.getStatus().intValue() == 0) {
					ex = true;
					List list = this.bj.getPacsListForCDC(arg37);
					if (list != null && list.size() > 0) {
						ex = true;
						int i = 0;

						while (true) {
							if (i >= list.size()) {
								continue label81;
							}

							St014Pacs pacs = (St014Pacs) list.get(i);
							String zyid = pacs.getZyid();
							String mzid = pacs.getMzid();
							String zymzid = "";
							boolean isZYPatient = false;
							if (!ab.aM(zyid)) {
								isZYPatient = true;
								zymzid = zyid;
							} else {
								isZYPatient = false;
								zymzid = mzid;
							}

							f anrs = this.bf.b("", pacs.getCheckDesc() + ";" + pacs.getCheckImpr());
							if (!anrs.getSuccess()) {
								++errnum;
								if (errExit) {
									errList.add(anrs.getErrMsg());
									continue label81;
								}

								errList.add(anrs.getErrMsg());
							} else {
								Map keyMap = anrs.getKeyMap();
								List yjs = this.a(isZYPatient, zymzid, keyMap, pacs);
								CtgSys009Yj ctgSys009Yj;
								if (!yjs.isEmpty()) {
									for (Iterator arg33 = yjs.iterator(); arg33.hasNext(); this.bi.save(ctgSys009Yj)) {
										ctgSys009Yj = (CtgSys009Yj) arg33.next();
										List exit = this.bi.isExit(ctgSys009Yj);
										if (exit != null && !exit.isEmpty()) {
											this.bi.delete(((CtgSys009Yj) exit.get(0)).getMasterid());
										}
									}
								}

								pacs.setCdcanaldt(new Date());
								pacs.setCdcanalflag(Integer.valueOf(1));
								this.bj.updateAnalFlagAndDate(pacs);
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
		} catch (Exception arg35) {
			errList.add(arg35.getMessage());
			c.error("analysisBc", arg35);
			rs.setSuccess(false);
		}

		rs.setErrMsgList(errList);
		rs.setSucTotals(sucnum);
		rs.setErrTotals(errnum);
		rs.setTotals(errnum + sucnum);
		return rs;
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(St008Bcjl bcjl, List<Gr018Bcgrys> gr018BcInsert, List<Gr018Ysgrys> gr018YsInsert,
			List<Gr018Ysgrys> gr018YsUpdate) {
		this.be.updateAnalResultTest(bcjl);
		if (gr018YsInsert.size() > 0) {
			this.bl.saveList(gr018YsInsert);
		}

		if (gr018YsUpdate.size() > 0) {
			this.bl.K(gr018YsUpdate);
		}

		if (gr018BcInsert.size() > 0) {
			this.bk.saveList(gr018BcInsert);
		}

	}

	@Transactional(rollbackFor = {Exception.class})
	public void h(String zyid, String bcid) {
		this.bk.delete(zyid, bcid);
		this.bl.bQ(zyid);
		this.be.updateAnalFlag(zyid, "0");
		this.aS.updateAnalFlag(zyid, "0");
	}

	public List<CtgSys009Yj> a(boolean isZyPatient, String zymzid, Map<String, d> keymap, St014Pacs pacs) {
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
					yj.setYjsource("影像");
					yj.setYjcontent(nodeResult2.getOriginalContent());
					yj.setSourceId(pacs.getId());
				}

				if (ab.isNotEmpty(yj.getMasterid())) {
					yjs.add(yj);
				}
			}
		}

		return yjs;
	}
}