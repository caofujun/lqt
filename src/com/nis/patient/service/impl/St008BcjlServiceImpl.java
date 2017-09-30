package com.nis.patient.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.param.service.SysParamService;
import com.nis.patient.dao.St008BcjlDao;
import com.nis.patient.entity.St008Bcjl;
import com.nis.patient.service.St008BcjlService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St008BcjlServiceImpl implements St008BcjlService {
	@Autowired
	private St008BcjlDao tz;
	@Autowired
	private SysParamService j;

	public void save(St008Bcjl st008Bcjl) {
		this.tz.save(st008Bcjl);
	}

	public void delete(String id) {
		this.tz.delete(id);
	}

	public void update(St008Bcjl st008Bcjl) {
		this.tz.update(st008Bcjl);
	}

	public St008Bcjl get(String id) {
		return this.tz.get(id);
	}

	public MyPage<St008Bcjl> a(St008Bcjl st008Bcjl) {
		int total = this.tz.findSt008BcjlCount(st008Bcjl);
		List data = null;
		if (total > 0) {
			data = this.tz.findSt008Bcjl(st008Bcjl);
		}

		return new MyPage(st008Bcjl.getPage().intValue(), st008Bcjl.getSize().intValue(), total, data);
	}

	public List<St008Bcjl> getAll() {
		return this.tz.getAll();
	}

	public List<St008Bcjl> findDisAnalysisList(St008Bcjl st008Bcjl) {
		return this.tz.findDisAnalysisList(st008Bcjl);
	}

	public void updateAnalFlag(String zyid, String analFlag) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.tz.updateAnalFlag(zyid, analFlag, yjVersion);
	}

	public List<St008Bcjl> getBcListTest(Date curr, int rownum, String version) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return "6".equals(version)
				? this.tz.getBcListTestV6(curr, rownum, yjVersion)
				: this.tz.getBcListTest(curr, rownum, yjVersion);
	}

	public void updateAnalResultTest(St008Bcjl st008Bcjl) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		st008Bcjl.setTablename(yjVersion);
		this.tz.updateAnalResultTest(st008Bcjl);
	}

	public Date getMonitorPatientBcjlLastAt() {
		Date date = new Date();
		String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
		if (version.equals("7")) {
			date = this.tz.getMonitorPatientBcjlLastAt();
		} else if (version.equals("6")) {
			date = this.tz.getMonitorPatientBcjlLastAt6();
		}

		return date;
	}

	public List<DataWarning> findPatentBcjlWarning(Date queryStartDate, Date queryEndDate) {
		Object dateWarnList = new ArrayList();
		String version = this.j.findByParamCode(Param.NIS_INTEFACE_MONITOR_VERSION);
		if (version.equals("7")) {
			dateWarnList = this.tz.findPatentBcjlWarning(queryStartDate, queryEndDate);
		} else if (version.equals("6")) {
			dateWarnList = this.tz.findPatentBcjlWarning6(queryStartDate, queryEndDate);
		}

		return (List) dateWarnList;
	}

	public List<St008Bcjl> findByZyid(String zyid) {
		return this.tz.findByZyid(zyid);
	}

	public void az() {
		String dealSql = "update st008_bcjl bc set bc.anal_flag = 0 where bc.zyid in (select b.zyid from st008_bcjl b where b.enter_date >=trunc(sysdate-5) and exists(select * from gr018_bcgrys b2 where b.zyid = b2.zyid and b2.bc_id = b.id and b2.enter_date<>b.enter_date));\n";
		dealSql = dealSql
				+ "update st014_pacs p set p.anal_flag = 0 where p.zyid in (select b.zyid from st008_bcjl b where b.enter_date >=trunc(sysdate-5) and exists(select * from gr018_bcgrys b2 where b.zyid = b2.zyid and b2.bc_id = b.id and b2.enter_date <>b.enter_date));\n";
		dealSql = dealSql
				+ "delete from gr018_ysgrys ys where ys.data_form in (\'病程\', \'影像\') and ys.zyid in (select b.zyid from st008_bcjl b where b.enter_date >=trunc(sysdate-5) and exists(select * from gr018_bcgrys b2 where b.zyid = b2.zyid and b2.bc_id = b.id and b2.enter_date <>b.enter_date));\n";
		dealSql = dealSql
				+ "delete from gr018_bcgrys where data_form in (\'病程\', \'影像\') and zyid in (select b.zyid from st008_bcjl b where b.enter_date >=trunc(sysdate-5) and exists(select * from gr018_bcgrys b2 where b.zyid = b2.zyid and b2.bc_id = b.id and b2.enter_date <>b.enter_date));\n";
		this.tz.deleteAnalData(dealSql);
	}

	public List<String> getBcZyidList(Date curr, int rownum, String version) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return "6".equals(version)
				? this.tz.getBcZyidListv6(curr, rownum, yjVersion)
				: this.tz.getBcZyidList(curr, rownum, yjVersion);
	}

	public int getBcZyidCount(Date curr, String version) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return "6".equals(version)
				? this.tz.getBcZyidCountv6(curr, yjVersion)
				: this.tz.getBcZyidCount(curr, yjVersion);
	}

	public List<St008Bcjl> getBcListByZyid(List<String> zyids, Date curr, String version) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return "6".equals(version)
				? this.tz.getBcListByZyidV6(zyids, curr, yjVersion)
				: this.tz.getBcListByZyid(zyids, curr, yjVersion);
	}

	public void updateCdcAnalFlag(St008Bcjl st008Bcjl) {
		this.tz.updateCdcAnalFlag(st008Bcjl);
	}

	public List<St008Bcjl> getBcListForCDC(int rownum) {
		return this.tz.getBcListForCDC(rownum);
	}
}