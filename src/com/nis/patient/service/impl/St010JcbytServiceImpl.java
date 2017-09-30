package com.nis.patient.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.dao.St010JcbytDao;
import com.nis.patient.entity.St010Jcbyt;
import com.nis.patient.service.St010JcbytService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St010JcbytServiceImpl implements St010JcbytService {
	@Autowired
	private St010JcbytDao tB;

	public void save(St010Jcbyt st010Jcbyt) {
		this.tB.save(st010Jcbyt);
	}

	public void delete(String id) {
		this.tB.delete(id);
	}

	public void update(St010Jcbyt st010Jcbyt) {
		this.tB.update(st010Jcbyt);
	}

	public void batchUpdAnalFlag(List<St010Jcbyt> st010List) {
		if (st010List != null && st010List.size() > 0) {
			this.tB.batchUpdAnalFlag(st010List);
		}

	}

	public void updAnalFlag(St010Jcbyt st010Jcbyt) {
		this.tB.updAnalFlag(st010Jcbyt);
	}

	public void updAnalDt(St010Jcbyt st010Jcbyt) {
		this.tB.updAnalDt(st010Jcbyt);
	}

	public St010Jcbyt get(String id) {
		return this.tB.get(id);
	}

	public MyPage<St010Jcbyt> a(St010Jcbyt st010Jcbyt) {
		int total = this.tB.findSt010JcbytCount(st010Jcbyt);
		List data = null;
		if (total > 0) {
			data = this.tB.findSt010Jcbyt(st010Jcbyt);
		}

		return new MyPage(st010Jcbyt.getPage().intValue(), st010Jcbyt.getSize().intValue(), total, data);
	}

	public List<St010Jcbyt> getAll() {
		return this.tB.getAll();
	}

	public List<St010Jcbyt> findSt010JcbytList(St010Jcbyt st010Jcbyt) {
		return this.tB.findSt010JcbytList(st010Jcbyt);
	}

	public List<St010Jcbyt> queryForYJ(String testOrderNo, String sql) {
		return this.tB.queryForYJ(testOrderNo, sql);
	}

	public List<DataWarning> findPatentJcbytWarning(Date queryStartDate, Date queryEndDate) {
		return this.tB.findPatentJcbytWarning(queryStartDate, queryEndDate);
	}

	public List<St010Jcbyt> findByZyidTestNo(String zyid, String testOrderNo) {
		return this.tB.findByZyidTestNo(zyid, testOrderNo);
	}

	public List<St010Jcbyt> t(String zymzid, String patientType, String sql) {
		new ArrayList();
		List list;
		if ("MZ".equals(patientType)) {
			list = this.tB.queryForYJByMzid(zymzid, sql);
		} else {
			list = this.tB.queryForYJByZyid(zymzid, sql);
		}

		return list;
	}
}