package com.nis.patient.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.param.service.SysParamService;
import com.nis.patient.dao.St009SjbbDao;
import com.nis.patient.entity.ResistantAnalysis;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.service.St009SjbbService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St009SjbbServiceImpl implements St009SjbbService {
	@Autowired
	private St009SjbbDao tA;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;
	@Autowired
	private Bk002GrzdService us;

	public void save(St009Sjbb st009Sjbb) {
		this.tA.save(st009Sjbb);
	}

	public void delete(String id) {
		this.tA.delete(id);
	}

	public void update(St009Sjbb st009Sjbb) {
		this.tA.update(st009Sjbb);
	}

	public void batchUpdAnalFlag(List<St009Sjbb> st009List) {
		if (st009List != null && st009List.size() > 0) {
			String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
			this.tA.batchUpdAnalFlag(st009List, yjVersion);
		}

	}

	public St009Sjbb get(String id) {
		return this.tA.get(id);
	}

	public MyPage<St009Sjbb> a(St009Sjbb st009Sjbb) {
		ArrayList remark = new ArrayList();
		List hilist = this.p.u("result_high", (String) null);
		Iterator total = hilist.iterator();

		while (total.hasNext()) {
			SysDict loList = (SysDict) total.next();
			remark.add(loList.getDictCode());
		}

		List loList1 = this.p.u("result_low", (String) null);
		Iterator data = loList1.iterator();

		while (data.hasNext()) {
			SysDict total1 = (SysDict) data.next();
			remark.add(total1.getDictCode());
		}

		st009Sjbb.setRemark(remark);
		int total2 = this.tA.findSt009SjbbCount(st009Sjbb);
		List data1 = null;
		if (total2 > 0) {
			data1 = this.tA.findSt009Sjbb(st009Sjbb);
		}

		return new MyPage(st009Sjbb.getPage().intValue(), st009Sjbb.getSize().intValue(), total2, data1);
	}

	public List<St009Sjbb> getAll() {
		return this.tA.getAll();
	}

	public List<St009Sjbb> findSjbbForInfectList(St009Sjbb st009Sjbb) {
		List findSjbbForInfectList = this.tA.findSjbbForInfectList(st009Sjbb);
		return findSjbbForInfectList;
	}

	public List<St009Sjbb> findSjbbListBytestNo(String testOrderNo) {
		return this.tA.findSjbbListBytestNo(testOrderNo);
	}

	public List<St009Sjbb> findBacteriaList(St009Sjbb st009Sjbb) {
		return this.tA.findBacteriaList(st009Sjbb);
	}

	public St009Sjbb getCheckResultTitle(St009Sjbb st009Sjbb) {
		return this.tA.getCheckResultTitle(st009Sjbb);
	}

	public Map<String, String> ct(String zyid) {
		HashMap result = new HashMap();
		String resultHigh = "";
		List hilist = this.p.u("result_high", (String) null);

		SysDict resultLow;
		for (Iterator loList = hilist.iterator(); loList
				.hasNext(); resultHigh = resultHigh + resultLow.getDictCode() + ",") {
			resultLow = (SysDict) loList.next();
		}

		String resultLow1 = "";
		List loList1 = this.p.u("result_low", (String) null);

		SysDict list;
		for (Iterator map = loList1.iterator(); map.hasNext(); resultLow1 = resultLow1 + list.getDictCode() + ",") {
			list = (SysDict) map.next();
		}

		List list1 = this.tA.findFocusFactors(zyid, resultHigh + resultLow1);
		Iterator arg8 = list1.iterator();

		while (arg8.hasNext()) {
			Map map1 = (Map) arg8.next();
			result.put((String) map1.get("item"), (String) map1.get("value"));
		}

		return result;
	}

	public List<St009Sjbb> getUnAnalysisRecord(int rows) {
		return this.tA.getUnAnalysisRecord(rows);
	}

	public String masterCdts(String patientType, String testOrderNo, String sql) {
		String testOrderNos = this.tA.masterCdts(patientType, testOrderNo, sql);
		return testOrderNos;
	}

	public int masterCdtsNew(String patientType, String testOrderNo, String sql) {
		int count = this.tA.masterCdtsNew(patientType, testOrderNo, sql);
		return count;
	}

	public Map<String, Date> getMonitorPatientTestLastAt(String deptId) {
		Object map = new HashMap();
		String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
		if (version.equals("7")) {
			map = this.tA.getMonitorPatientTestLastAt(deptId);
		} else if (version.equals("6")) {
			map = this.tA.getMonitorPatientTestLastAt6(deptId);
		}

		return (Map) map;
	}

	public Map<String, Date> getMonitorPatientTestsLastAt(String deptId) {
		Object map = new HashMap();
		String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
		if (version.equals("7")) {
			map = this.tA.getMonitorPatientTestsLastAt(deptId);
		} else if (version.equals("6")) {
			map = this.tA.getMonitorPatientTestsLastAt6(deptId);
		}

		return (Map) map;
	}

	public List<DataWarning> findPatentSjbbWarning(Date queryStartDate, Date queryEndDate) {
		Object dateWarnList = new ArrayList();
		String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
		if (version.equals("7")) {
			dateWarnList = this.tA.findPatentSjbbWarning(queryStartDate, queryEndDate);
		} else if (version.equals("6")) {
			dateWarnList = this.tA.findPatentSjbbWarning6(queryStartDate, queryEndDate);
		}

		return (List) dateWarnList;
	}

	public void updateFlagByMzzyid(St009Sjbb st009Sjbb) {
		this.tA.updateFlagByMzzyid(st009Sjbb);
	}

	public List<St009Sjbb> a(Date curr, Integer orclEndNum) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyGerm(curr, orclEndNum, yjVersion);
	}

	public List<St009Sjbb> b(Date curr, Integer orclEndNum) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyGermV6(curr, orclEndNum, yjVersion);
	}

	public int w(Date curr) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyGermCount(curr, yjVersion);
	}

	public int x(Date curr) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyGermCountV6(curr, yjVersion);
	}

	public List<St009Sjbb> c(Date curr, Integer orclEndNum) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyComm(curr, orclEndNum, yjVersion);
	}

	public List<St009Sjbb> d(Date curr, Integer orclEndNum) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyCommV6(curr, orclEndNum, yjVersion);
	}

	public int y(Date curr) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyCommCount(curr, yjVersion);
	}

	public int z(Date curr) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tA.findWaitAnalyCommCountV6(curr, yjVersion);
	}

	public List<ResistantAnalysis> findWaitAnaResis(Integer orclEndNum, Date startDate, Date endDate,
			String testOrderNo, Date curr) {
		return this.tA.findWaitAnaResis(orclEndNum, startDate, endDate, testOrderNo, curr);
	}

	public int findWaitAnaResisCount() {
		return this.tA.findWaitAnaResisCount();
	}

	public void updateFlagByTestOrderNo(St009Sjbb st009Sjbb) {
		this.tA.updateFlagByTestOrderNo(st009Sjbb);
	}

	public List<St009Sjbb> b(St009Sjbb st009Sjbb) {
		List listTestOderNo = this.tA.getTestOderNo(st009Sjbb);
		ArrayList listTestOder = new ArrayList();
		Iterator findSjbbForInfectList = listTestOderNo.iterator();

		while (findSjbbForInfectList.hasNext()) {
			Map bk002Grzd = (Map) findSjbbForInfectList.next();
			String listTestOderNoCheck = (String) bk002Grzd.get("TESTORDERNO");
			listTestOder.add(listTestOderNoCheck);
		}

		Bk002Grzd bk002Grzd1 = this.us.get(st009Sjbb.getRefid());
		if (bk002Grzd1 != null) {
			Integer findSjbbForInfectList1 = bk002Grzd1.getAuthStatus();
			if (findSjbbForInfectList1.intValue() == 1) {
				List listTestOderNoCheck1 = this.tA.isCheck(st009Sjbb);
				ArrayList listCheckOder = new ArrayList();
				Iterator arg8 = listTestOderNoCheck1.iterator();

				while (arg8.hasNext()) {
					Map map = (Map) arg8.next();
					String testNo = (String) map.get("TESTORDERNO");
					listCheckOder.add(testNo);
				}

				listTestOder.removeAll(listCheckOder);
			}
		}

		st009Sjbb.setTestOrderNoList(listTestOder);
		List findSjbbForInfectList2 = this.tA.findSjbbForInfectList(st009Sjbb);
		return findSjbbForInfectList2;
	}
}