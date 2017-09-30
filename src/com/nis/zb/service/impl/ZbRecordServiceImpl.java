package com.nis.zb.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ah;
import com.nis.comm.utils.f;
import com.nis.log.entity.SysLog;
import com.nis.log.service.SysLogService;
import com.nis.zb.dao.ZbRecordDao;
import com.nis.zb.entity.ZbRecord;
import com.nis.zb.service.ZbRecordService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ZbRecordServiceImpl implements ZbRecordService {
	@Autowired
	private ZbRecordDao yl;
	@Autowired
	private ApplicationContext dO;
	@Autowired
	private SysLogService aV;

	public MyPage<ZbRecord> a(ZbRecord zbRecord) {
		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
		} catch (ParseException arg4) {
			arg4.printStackTrace();
		}

		HashMap paramMap = new HashMap();
		paramMap.put("startDate", zbRecord.getStartDate());
		paramMap.put("endDate", zbRecord.getEndDate());
		this.yl.excutePrOut(paramMap);
		List caseRegistCount = this.yl.findCaseRegistCount(zbRecord);
		List data = this.yl.findZbRecordList(zbRecord);
		caseRegistCount.addAll(data);
		return new MyPage(1, 100, 50, caseRegistCount);
	}

	public String c(ZbRecord zbRecord) {
		List data = this.yl.findZbRecordList(zbRecord);
		return data.isEmpty() ? "0" : "" + ((ZbRecord) data.get(0)).getUnReportAmount();
	}

	public List<SysLog> b(ZbRecord zbRecord) {
		return this.aV.findbyBusinessId("wlzb" + zbRecord.getItemCode());
	}

	public String[] d(ZbRecord zbRecord) {
		String[] datas = new String[3];
		String ids = "";

		try {
			ArrayList e = new ArrayList();
			List zBMapList0 = null;
			List infectstatusZBMapList = null;
			List infectFactorZBMapList = null;
			List exemplarZBMapList = null;
			List pathogenyZBMapList = null;
			List useAntibZBMapList = null;
			List outhospitalinputZBMapList = null;
			List st005SsxxbZBMapList = null;
			List st005SsxxbZBMapListDatail = null;
			List needLeMasterZBMapList = null;
			List needlestickZBMapList = null;
			List bloodZBMapList = null;
			List icuInfectZBMapList = null;
			List icuSampleZBMapList = null;
			List icuIllBodyZBMapList = null;
			List icuAntiZBMapList = null;
			List icuPotiantLogZBMapList = null;
			List icuDangerGradeZBMapList = null;
			List nicuInfectZBMapList = null;
			List nicuSampleZBMapList = null;
			List nicuIllBodyZBMapList = null;
			List nicuAntiZBMapList = null;
			List nicuLogZBMapList = null;
			List antibInputZBMapList = null;
			List nowUseAntibZBMapList = null;
			List jcReportMasterZBMapList = null;
			List jcReportDetailZBMapList = null;
			List ycxjymasterZBMapList = null;
			List ycxjydetailZBMapList = null;
			List wsclmasterZBMapList = null;
			List wscldetailZBMapList = null;
			ArrayList idList = new ArrayList();
			Iterator arg37;
			HashMap arg39;
			if ("0".equals(zbRecord.getItemCode())) {
				zBMapList0 = this.yl.caseRegist(zbRecord);

				for (arg37 = zBMapList0.iterator(); arg37.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
					arg39 = (HashMap) arg37.next();
				}

				datas[0] = "" + zBMapList0.size();
				datas[1] = ids;
				datas[2] = ah.a(zBMapList0, "BODY", "DATA");
				return datas;
			} else {
				int map;
				if ("1".equals(zbRecord.getItemCode())) {
					infectstatusZBMapList = this.yl.findInfectstatusZBList(zbRecord);

					for (map = 0; map < infectstatusZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) infectstatusZBMapList.get(map)).get("KEY_ID"));
					}

					infectFactorZBMapList = this.yl.findInfectFactorZBList(idList);
					exemplarZBMapList = this.yl.findExemplarZBMapList(idList);
					pathogenyZBMapList = this.yl.findPathogenyZBMapList(idList);
					useAntibZBMapList = this.yl.findUseAntibZBMapList(idList);
					if (infectstatusZBMapList.size() > 0) {
						e.addAll(infectstatusZBMapList);
					}

					if (infectFactorZBMapList.size() > 0) {
						e.addAll(infectFactorZBMapList);
					}

					if (exemplarZBMapList.size() > 0) {
						e.addAll(exemplarZBMapList);
					}

					if (pathogenyZBMapList.size() > 0) {
						e.addAll(pathogenyZBMapList);
					}

					if (useAntibZBMapList.size() > 0) {
						e.addAll(useAntibZBMapList);
					}

					for (arg37 = infectstatusZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + infectstatusZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("2".equals(zbRecord.getItemCode())) {
					outhospitalinputZBMapList = this.yl.findOuthospitalinputZBList(zbRecord);

					for (arg37 = outhospitalinputZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + outhospitalinputZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(outhospitalinputZBMapList, "BODY", "DATA");
					return datas;
				} else if ("5".equals(zbRecord.getItemCode())) {
					st005SsxxbZBMapList = this.yl.findSt005SsxxbZBMapList(zbRecord);

					for (map = 0; map < st005SsxxbZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) st005SsxxbZBMapList.get(map)).get("KEY_ID"));
					}

					st005SsxxbZBMapListDatail = this.yl.findSt005SsxxbZBMapListDatail(idList);
					if (st005SsxxbZBMapList.size() > 0) {
						e.addAll(st005SsxxbZBMapList);
					}

					if (st005SsxxbZBMapListDatail.size() > 0) {
						e.addAll(st005SsxxbZBMapListDatail);
					}

					for (arg37 = st005SsxxbZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + st005SsxxbZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("6".equals(zbRecord.getItemCode())) {
					needLeMasterZBMapList = this.yl.findNeedLeMasterZBMapList(zbRecord);

					for (map = 0; map < needLeMasterZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) needLeMasterZBMapList.get(map)).get("KEY_ID"));
					}

					needlestickZBMapList = this.yl.findNeedlestickZBMapList(idList);
					bloodZBMapList = this.yl.findBloodZBMapList(idList);
					if (needLeMasterZBMapList.size() > 0) {
						e.addAll(needLeMasterZBMapList);
					}

					if (needlestickZBMapList.size() > 0) {
						e.addAll(needlestickZBMapList);
					}

					if (bloodZBMapList.size() > 0) {
						e.addAll(bloodZBMapList);
					}

					for (arg37 = needLeMasterZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					for (arg37 = needLeMasterZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + needLeMasterZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("7".equals(zbRecord.getItemCode())) {
					icuInfectZBMapList = this.yl.findIcuInfectZBMapList(zbRecord);

					for (map = 0; map < icuInfectZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) icuInfectZBMapList.get(map)).get("KEY_ID"));
					}

					icuSampleZBMapList = this.yl.findIcuSampleZBMapList(idList);
					icuIllBodyZBMapList = this.yl.findIcuIllBodyZBMapList(idList);
					icuAntiZBMapList = this.yl.findIcuAntiZBMapList(idList);
					icuPotiantLogZBMapList = this.yl.findIcuPotiantLogZBMapList(zbRecord);
					icuDangerGradeZBMapList = this.yl.findIcuDangerGradeZBMapList(zbRecord);
					if (icuInfectZBMapList.size() > 0) {
						e.addAll(icuInfectZBMapList);
					}

					if (icuSampleZBMapList.size() > 0) {
						e.addAll(icuSampleZBMapList);
					}

					if (icuIllBodyZBMapList.size() > 0) {
						e.addAll(icuIllBodyZBMapList);
					}

					if (icuAntiZBMapList.size() > 0) {
						e.addAll(icuAntiZBMapList);
					}

					if (icuPotiantLogZBMapList.size() > 0) {
						e.addAll(icuPotiantLogZBMapList);
					}

					if (icuDangerGradeZBMapList.size() > 0) {
						e.addAll(icuDangerGradeZBMapList);
					}

					for (arg37 = icuInfectZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + icuInfectZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("9".equals(zbRecord.getItemCode())) {
					nicuInfectZBMapList = this.yl.findNicuInfectZBMapList(zbRecord);

					for (map = 0; map < nicuInfectZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) nicuInfectZBMapList.get(map)).get("KEY_ID"));
					}

					nicuSampleZBMapList = this.yl.findNicuSampleZBMapList(idList);
					nicuIllBodyZBMapList = this.yl.findNicuIllBodyZBMapList(idList);
					nicuAntiZBMapList = this.yl.findNicuAntiZBMapList(idList);
					nicuLogZBMapList = this.yl.findNicuLogZBMapList(zbRecord);
					if (nicuInfectZBMapList.size() > 0) {
						e.addAll(nicuInfectZBMapList);
					}

					if (nicuSampleZBMapList.size() > 0) {
						e.addAll(nicuSampleZBMapList);
					}

					if (nicuIllBodyZBMapList.size() > 0) {
						e.addAll(nicuIllBodyZBMapList);
					}

					if (nicuAntiZBMapList.size() > 0) {
						e.addAll(nicuAntiZBMapList);
					}

					if (nicuLogZBMapList.size() > 0) {
						e.addAll(nicuLogZBMapList);
					}

					for (arg37 = nicuInfectZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + nicuInfectZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("10".equals(zbRecord.getItemCode())) {
					antibInputZBMapList = this.yl.findAntibInputZBMapList(zbRecord);

					for (map = 0; map < antibInputZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) antibInputZBMapList.get(map)).get("KEY_ID"));
					}

					nowUseAntibZBMapList = this.yl.findNowUseAntibZBMapList(idList);
					if (antibInputZBMapList.size() > 0) {
						e.addAll(antibInputZBMapList);
					}

					if (nowUseAntibZBMapList.size() > 0) {
						e.addAll(nowUseAntibZBMapList);
					}

					for (arg37 = antibInputZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + antibInputZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("11".equals(zbRecord.getItemCode())) {
					jcReportMasterZBMapList = this.yl.findJcReportMasterZBMapList(zbRecord);

					for (map = 0; map < jcReportMasterZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) jcReportMasterZBMapList.get(map)).get("KEY_ID"));
					}

					jcReportDetailZBMapList = this.yl.findJcReportDetailZBMapList(idList);
					if (jcReportMasterZBMapList.size() > 0) {
						e.addAll(jcReportMasterZBMapList);
					}

					if (jcReportDetailZBMapList.size() > 0) {
						e.addAll(jcReportDetailZBMapList);
					}

					for (arg37 = jcReportMasterZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + jcReportMasterZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else if ("12".equals(zbRecord.getItemCode())) {
					ycxjymasterZBMapList = this.yl.findYcxjymasterZBMapList(zbRecord);

					for (map = 0; map < ycxjymasterZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) ycxjymasterZBMapList.get(map)).get("KEY_ID"));
					}

					ycxjydetailZBMapList = this.yl.findycxjydetailZBMapList(idList);
					if (ycxjymasterZBMapList.size() > 0) {
						e.addAll(ycxjymasterZBMapList);
					}

					if (ycxjydetailZBMapList.size() > 0) {
						e.addAll(ycxjydetailZBMapList);
					}

					for (arg37 = ycxjymasterZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + ycxjymasterZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				} else {
					wsclmasterZBMapList = this.yl.findWsclmasterZBMapList(zbRecord);

					for (map = 0; map < wsclmasterZBMapList.size(); ++map) {
						idList.add((String) ((HashMap) wsclmasterZBMapList.get(map)).get("KEY_ID"));
					}

					wscldetailZBMapList = this.yl.findWscldetailZBMapList(idList);
					if (wsclmasterZBMapList.size() > 0) {
						e.addAll(wsclmasterZBMapList);
					}

					if (wscldetailZBMapList.size() > 0) {
						e.addAll(wscldetailZBMapList);
					}

					for (arg37 = wsclmasterZBMapList.iterator(); arg37
							.hasNext(); ids = ids + arg39.get("KEY_ID") + ",") {
						arg39 = (HashMap) arg37.next();
					}

					datas[0] = "" + wsclmasterZBMapList.size();
					datas[1] = ids;
					datas[2] = ah.a(e, "BODY", "DATA");
					return datas;
				}
			}
		} catch (Exception arg38) {
			arg38.printStackTrace();
			datas[0] = "0";
			datas[1] = "";
			datas[2] = "";
			return datas;
		}
	}

	public void cM(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateOuthospitalinputZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cL(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateSt005SsxxbZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cN(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk002GrzdZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cO(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBl002SjdjZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cP(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk002GrzdICUZBResult(id, "1", f.getCurDate());
			}
		}

	}

	public void cQ(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk002GrzdNICUZBResult(id, "1", f.getCurDate());
			}
		}

	}

	public void cR(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateJkYzLhyyZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cS(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateHw101JcdjZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cT(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk003YgysZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cU(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateSt005SsxxbDetailZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cV(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBl002SjdjZBFlag1(id, "1", f.getCurDate());
			}
		}

	}

	public void cW(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBl002SjdjZBFlag2(id, "1", f.getCurDate());
			}
		}

	}

	public List<ZbRecord> findZbxhlList(Date startDate, Date endDate) {
		List data = this.yl.findZbxhlList(startDate, endDate);
		return data;
	}

	public void cX(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateSt003CryxxbZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cY(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb3ICUZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void cZ(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb4ICUZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void da(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb5ICUZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void b(String reportResult, Date startDate, Date endDate) {
		this.yl.updateGm003YbsjICUZBFlag(startDate, endDate, "1", f.getCurDate());
	}

	public void a(String reportResult, Date startDate, Date endDate) {
		this.yl.updateGm001DjpdmICUZBFlag(startDate, endDate, "1", f.getCurDate());
	}

	public void db(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb6NICUZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void dc(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb7NICUZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void dd(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb8NICUZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void c(String reportResult, Date startDate, Date endDate) {
		this.yl.updateGm004JcmxNICUZBFlag(startDate, endDate, "1", f.getCurDate());
	}

	public void de(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateSt015YzxxbKjywZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void df(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateHw102JcdmxZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void dg(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004SjbbZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public void dh(String reportResult) {
		String[] ids = reportResult.split(",");
		String[] arg5 = ids;
		int arg4 = ids.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			String id = arg5[arg3];
			if (!StringUtils.isBlank(id)) {
				this.yl.updateBk004Sjbb1ZBFlag(id, "1", f.getCurDate());
			}
		}

	}

	public ZbRecord e(ZbRecord zbRecord) {
		List data = this.yl.getRate(zbRecord);
		long wbCount = 0L;
		long ybCount = 0L;
		String itemName = "";
		if (data.size() > 0) {
			Iterator arg8 = data.iterator();

			while (arg8.hasNext()) {
				ZbRecord count = (ZbRecord) arg8.next();
				if (count.getItemCode().equals(zbRecord.getItemCode())) {
					wbCount = count.getUnReportAmount().longValue();
					ybCount = count.getReportAmount().longValue();
					itemName = count.getItemName();
				}
			}
		}

		long count1 = wbCount + ybCount;
		double rate = (double) ybCount / (double) count1 * 100.0D;
		String reportProgress = String.valueOf(rate) + "%";
		zbRecord.setReportProgress(reportProgress);
		zbRecord.setItemName(itemName);
		return zbRecord;
	}
}