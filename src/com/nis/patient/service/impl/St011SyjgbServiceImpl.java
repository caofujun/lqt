package com.nis.patient.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.dao.St011SyjgbDao;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St011SyjgbService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St011SyjgbServiceImpl implements St011SyjgbService {
	@Autowired
	private St011SyjgbDao tC;

	public void save(St011Syjgb st011Syjgb) {
		this.tC.save(st011Syjgb);
	}

	public void delete(String id) {
		this.tC.delete(id);
	}

	public void update(St011Syjgb st011Syjgb) {
		this.tC.update(st011Syjgb);
	}

	public St011Syjgb get(String id) {
		return this.tC.get(id);
	}

	public MyPage<St011Syjgb> a(St011Syjgb st011Syjgb) {
		int total = this.tC.findSt011SyjgbCount(st011Syjgb);
		List data = null;
		if (total > 0) {
			data = this.tC.findSt011Syjgb(st011Syjgb);
		}

		return new MyPage(st011Syjgb.getPage().intValue(), st011Syjgb.getSize().intValue(), total, data);
	}

	public List<St011Syjgb> getAll() {
		return this.tC.getAll();
	}

	public List<St011Syjgb> findSt011SyjgbList(St011Syjgb st011Syjgb) {
		return this.tC.findSt011SyjgbList(st011Syjgb);
	}

	public List<St011Syjgb> findByBDB(String zyid, String dybw, String zbName) {
		return this.tC.findByBDB(zyid, dybw, zbName);
	}

	public List<St011Syjgb> findByBXB(String zyid, String dybw, List<String> zbValue) {
		return this.tC.findByBXB(zyid, dybw, zbValue);
	}

	public List<St011Syjgb> findDrugAllergytList(String refid) {
		return this.tC.findDrugAllergytList(refid);
	}

	public List<St011Syjgb> findByOrderNoAndSn(String testOrderNo, String pathogenSn) {
		return this.tC.findByOrderNoAndSn(testOrderNo, pathogenSn);
	}

	public List<St011Syjgb> queryForYJ(String testOrderNo, String sql) {
		return this.tC.queryForYJ(testOrderNo, sql);
	}

	public List<DataWarning> findPatentSyjgbWarning(Date queryStartDate, Date queryEndDate) {
		return this.tC.findPatentSyjgbWarning(queryStartDate, queryEndDate);
	}

	public List<St011Syjgb> findByZyidTestNo(String zyid, String testOrderNo) {
		return this.tC.findByZyidTestNo(zyid, testOrderNo);
	}

	public List<St011Syjgb> findWaitAnalysis(String pathogenSn, String testOrderNo) {
		return this.tC.findWaitAnalysis(pathogenSn, testOrderNo);
	}

	public List<St011Syjgb> findSt011Syjgbqst(St011Syjgb st011Syjgb) {
		return this.tC.findSt011Syjgbqst(st011Syjgb);
	}

	public List<St011Syjgb> t(String zymzid, String patientType, String sql) {
		new ArrayList();
		List list;
		if ("MZ".equals(patientType)) {
			list = this.tC.queryForYJByMzid(zymzid, sql);
		} else {
			list = this.tC.queryForYJByZyid(zymzid, sql);
		}

		return list;
	}
}