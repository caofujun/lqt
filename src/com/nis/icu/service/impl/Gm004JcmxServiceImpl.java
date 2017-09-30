package com.nis.icu.service.impl;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.f;
import com.nis.comm.utils.g;
import com.nis.icu.dao.Gm004JcmxDao;
import com.nis.icu.entity.CgPg;
import com.nis.icu.entity.Gm004Jcmx;
import com.nis.icu.entity.IcuCount;
import com.nis.icu.entity.NicuCount;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St003CryxxbService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gm004JcmxServiceImpl implements Gm004JcmxService {
	@Autowired
	private Gm004JcmxDao sp;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysJudgeLogService J;

	public void save(Gm004Jcmx gm004Jcmx) {
		this.sp.save(gm004Jcmx);
	}

	public void delete(Date creationdate) {
		this.sp.delete(creationdate);
	}

	public void update(Gm004Jcmx gm004Jcmx) {
		this.sp.update(gm004Jcmx);
	}

	public List<Map<String, Object>> getDeptPatient(String time, String DeptId) {
		return this.sp.getDeptPatient(time, DeptId);
	}

	public void updateSpecified(Gm004Jcmx gm004Jcmx, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.sp.updateSpecified(gm004Jcmx, updateAttrs);
		}

	}

	public Gm004Jcmx get(Date creationdate) {
		return this.sp.get(creationdate);
	}

	public MyPage<Gm004Jcmx> a(Gm004Jcmx gm004Jcmx) {
		int total = this.sp.findGm004JcmxCount(gm004Jcmx);
		List data = null;
		if (total > 0) {
			data = this.sp.findGm004Jcmx(gm004Jcmx);
		}

		return new MyPage(gm004Jcmx.getPage().intValue(), gm004Jcmx.getSize().intValue(), total, data);
	}

	public List<Gm004Jcmx> getAll() {
		return this.sp.getAll();
	}

	public List<Gm004Jcmx> findByZyid(String zyid, String time) {
		return this.sp.findByZyid(zyid, time);
	}

	public HSSFWorkbook a(String unitId, String typeid, String deptid, String neonatebw, Date startdate, Date enddate) {
		HSSFWorkbook workbook = null;
		List cryxxbList = this.bg.b(unitId, typeid, deptid, neonatebw, startdate, enddate);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 9; ++rowTitle) {
				e.setColumnWidth(rowTitle, 5000);
			}

			HSSFRow arg16 = e.createRow(0);
			g.a(arg16, 0, style, 1, "住院号");
			g.a(arg16, 1, style, 1, "姓名");
			g.a(arg16, 2, style, 1, "住院次数");
			g.a(arg16, 3, style, 1, "年龄");
			g.a(arg16, 4, style, 1, "床号");
			g.a(arg16, 5, style, 1, "入院科室");
			g.a(arg16, 6, style, 1, "当前科室");
			g.a(arg16, 7, style, 1, "入院时间");
			g.a(arg16, 8, style, 1, "出院时间");
			Integer index = Integer.valueOf(1);
			if (cryxxbList != null && cryxxbList.size() > 0) {
				for (Iterator arg13 = cryxxbList.iterator(); arg13
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					St003Cryxxb cryxxb = (St003Cryxxb) arg13.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, cryxxb.getZyid());
					g.a(row, 1, style, 1, cryxxb.getPatientName());
					g.a(row, 2, style, 1, cryxxb.getVisitId());
					g.a(row, 3, style, 1, cryxxb.getAge());
					g.a(row, 4, style, 1, cryxxb.getBedNo());
					g.a(row, 5, style, 1, cryxxb.getInDeptName());
					g.a(row, 6, style, 1, cryxxb.getOutDeptName());
					g.a(row, 7, style, 1, f.c(cryxxb.getInHospAt(), "yyyy-MM-dd HH:mm"));
					g.a(row, 8, style, 1, f.c(cryxxb.getOutAt(), "yyyy-MM-dd HH:mm"));
				}
			} else {
				g.a(e.createRow(1), 0, style, 1, "查无资料");
			}
		} catch (Exception arg15) {
			arg15.printStackTrace();
		}

		return workbook;
	}

	public List<Map<String, Object>> findqrxczxgxgr(String startDate, String endDate, String ificu) {
		return this.sp.findqrxczxgxgr(startDate, endDate, ificu);
	}

	public Map<String, Object> a(String startDate, String endDate, List<String> deptIdIn) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		return this.sp.findDcl(startDate, endDate, deptIdIn, qzValue);
	}

	public List<NicuCount> findNicuCount(String deptId, String startDate, String endDate) {
		return this.sp.findNicuCount(deptId, startDate, endDate);
	}

	public HSSFWorkbook n(String deptId, String startDate, String endDate) {
		HSSFWorkbook workbook = null;
		List nicuList = this.sp.findNicuCount(deptId, startDate, endDate);

		try {
			workbook = new HSSFWorkbook();
			HSSFSheet e = workbook.createSheet((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			HSSFCellStyle style = g.c(workbook);

			for (int rowTitle = 0; rowTitle < 17; ++rowTitle) {
				e.setColumnWidth(rowTitle, 4000);
			}

			HSSFRow arg14 = e.createRow(0);
			e.addMergedRegion(new Region(0, 0, 1, 0));
			g.a(arg14, 0, style, 1, "日期");
			e.addMergedRegion(new Region(0, 1, 0, 4));
			g.a(arg14, 1, style, 1, "BW<=1000g");
			e.addMergedRegion(new Region(0, 5, 0, 8));
			g.a(arg14, 5, style, 1, "BW1001g~1500g");
			e.addMergedRegion(new Region(0, 9, 0, 12));
			g.a(arg14, 9, style, 1, "BW1501g~2500g");
			e.addMergedRegion(new Region(0, 13, 0, 16));
			g.a(arg14, 13, style, 1, "BW>2500g");
			HSSFRow rowTitle2 = e.createRow(1);
			g.a(rowTitle2, 1, style, 1, "新入新生儿数");
			g.a(rowTitle2, 2, style, 1, "已住新生儿数");
			g.a(rowTitle2, 3, style, 1, "脐/中心静脉");
			g.a(rowTitle2, 4, style, 1, "使用呼吸机数");
			g.a(rowTitle2, 5, style, 1, "新入新生儿数");
			g.a(rowTitle2, 6, style, 1, "已住新生儿数");
			g.a(rowTitle2, 7, style, 1, "脐/中心静脉");
			g.a(rowTitle2, 8, style, 1, "使用呼吸机数");
			g.a(rowTitle2, 9, style, 1, "新入新生儿数");
			g.a(rowTitle2, 10, style, 1, "已住新生儿数");
			g.a(rowTitle2, 11, style, 1, "脐/中心静脉");
			g.a(rowTitle2, 12, style, 1, "使用呼吸机数");
			g.a(rowTitle2, 13, style, 1, "新入新生儿数");
			g.a(rowTitle2, 14, style, 1, "已住新生儿数");
			g.a(rowTitle2, 15, style, 1, "脐/中心静脉");
			g.a(rowTitle2, 16, style, 1, "使用呼吸机数");
			Integer index = Integer.valueOf(2);
			if (nicuList != null && nicuList.size() > 0) {
				for (Iterator arg11 = nicuList.iterator(); arg11
						.hasNext(); index = Integer.valueOf(index.intValue() + 1)) {
					NicuCount nicu = (NicuCount) arg11.next();
					HSSFRow row = e.createRow(index.intValue());
					g.a(row, 0, style, 1, nicu.getStrDate());
					g.a(row, 1, style, 0, nicu.getNewCount1());
					g.a(row, 2, style, 0, nicu.getInCount1());
					g.a(row, 3, style, 0, nicu.getZxjmCount1());
					g.a(row, 4, style, 0, nicu.getHxjCount1());
					g.a(row, 5, style, 0, nicu.getNewCount2());
					g.a(row, 6, style, 0, nicu.getInCount2());
					g.a(row, 7, style, 0, nicu.getZxjmCount2());
					g.a(row, 8, style, 0, nicu.getHxjCount2());
					g.a(row, 9, style, 0, nicu.getNewCount3());
					g.a(row, 10, style, 0, nicu.getInCount3());
					g.a(row, 11, style, 0, nicu.getZxjmCount3());
					g.a(row, 12, style, 0, nicu.getHxjCount3());
					g.a(row, 13, style, 0, nicu.getNewCount4());
					g.a(row, 14, style, 0, nicu.getInCount4());
					g.a(row, 15, style, 0, nicu.getZxjmCount4());
					g.a(row, 16, style, 0, nicu.getHxjCount4());
				}
			} else {
				g.a(e.createRow(2), 0, style, 1, "查无资料");
			}
		} catch (Exception arg13) {
			arg13.printStackTrace();
		}

		return workbook;
	}

	@SqlLog(p = "院感端首页--全院概况")
	public Map<String, Object> A(String startDate, String endDate) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.sp.findFloorGeneral(startDate, endDate, tws);
	}

	@SqlLog(p = "院感端首页--代办事宜")
	public Map<String, Object> a(Date startDate, Date endDate, String time) {
		String qzValue = this.j.findByParamCode(Param.NIS_GRYJ_QZ_VALUE);
		Integer days = Integer.valueOf(this.j.findByParamCode(Param.NIS_BL_FC_DAYS));
		return this.sp.findTodo(startDate, endDate, time, qzValue, days);
	}

	public Date getMonitorPatientTubeLastAt() {
		return this.sp.getMonitorPatientTubeLastAt();
	}

	public List<CgPg> findCgByZyid(String zyid, Date startDate, Date endDate, String time) {
		List cgpgList = this.sp.findCgByZyid(zyid, startDate, endDate, time);
		return cgpgList;
	}

	public List<TreeEntity> getDeptPatientMenuNode(String deptCode, String time) {
		return this.sp.getDeptPatientMenuNode(deptCode, time);
	}

	public String getPatientNum(String time, String deptId) {
		return this.sp.getPatientNum(time, deptId);
	}

	public List<Gm004Jcmx> getpatientList(String patientId, String time) {
		return this.sp.getpatientList(patientId, time);
	}

	public List<Map<String, Object>> getDeptPatient2(String time, String queryDate, String deptId) {
		return this.sp.getDeptPatient2(time, queryDate, deptId);
	}

	public String getPatientNum2(String time, String queryDate, String deptId) {
		return this.sp.getPatientNum2(time, queryDate, deptId);
	}

	public List<TreeEntity> getDeptPatientMenuNode2(String deptCode, String queryDate, String time) {
		return this.sp.getDeptPatientMenuNode2(deptCode, queryDate, time);
	}

	public int getNumByTypeId(String zyid, String typeid, Date startAt, Date stopAt) {
		return this.sp.getNumByTypeId(zyid, typeid, startAt, stopAt);
	}

	public void o(String startDate, String endDate, String id) {
		HashMap paramMap = new HashMap();
		paramMap.put("startDate", f.Y(startDate));
		paramMap.put("endDate", f.Y(endDate));
		SysJudgeLog sysJudgeLog = this.J.get(id);
		this.sp.judgeGm004(paramMap);
		System.out.println(paramMap + "--------------------------------------------------------");
		if (paramMap.get("status").toString().indexOf("ok") > -1) {
			sysJudgeLog.setEndTime(new Date());
			sysJudgeLog.setStatus("1");
			this.J.update(sysJudgeLog);
		} else if (paramMap.get("status").toString().indexOf("error") > -1) {
			sysJudgeLog.setEndTime(new Date());
			sysJudgeLog.setStatus("2");
			this.J.update(sysJudgeLog);
		}

	}

	public void deleteHxj(Gm004Jcmx gm004Jcmx) {
		this.sp.deleteHxj(gm004Jcmx);
		this.sp.updateHxjCount(gm004Jcmx);
	}

	public List<IcuCount> i(String deptId, String startDate, String endDate, String unitId) {
		String tw = this.j.findByParamCode(Param.NIS_TW_FR);
		Double tws = Double.valueOf(Double.parseDouble(tw));
		return this.sp.findBedIcuByorderName(deptId, startDate, endDate, tws, unitId);
	}
}