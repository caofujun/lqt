package com.nis.patient.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.dict.service.SysDictService;
import com.nis.patient.dao.St002ZdxxbDao;
import com.nis.patient.entity.St002Zdxxb;
import com.nis.patient.service.St002ZdxxbService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St002ZdxxbServiceImpl implements St002ZdxxbService {
	@Autowired
	private St002ZdxxbDao ts;
	@Autowired
	private SysDictService p;

	public void save(St002Zdxxb st002Zdxxb) {
		this.ts.save(st002Zdxxb);
	}

	public void delete(String id) {
		this.ts.delete(id);
	}

	public void update(St002Zdxxb st002Zdxxb) {
		this.ts.update(st002Zdxxb);
	}

	public St002Zdxxb get(String id) {
		return this.ts.get(id);
	}

	public MyPage<St002Zdxxb> a(St002Zdxxb st002Zdxxb) {
		int total = this.ts.findSt002ZdxxbCount(st002Zdxxb);
		List data = null;
		if (total > 0) {
			data = this.ts.findSt002Zdxxb(st002Zdxxb);
			data = this.a(data);
		}

		return new MyPage(st002Zdxxb.getPage().intValue(), st002Zdxxb.getSize().intValue(), total, data);
	}

	private List<St002Zdxxb> a(List<St002Zdxxb> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			St002Zdxxb st002Zdxxb = (St002Zdxxb) arg2.next();
			if (StringUtils.isNotBlank(st002Zdxxb.getDiagnosisType())) {
				st002Zdxxb.setShowDiagnosisType(
						this.p.b("diagnosis_type", st002Zdxxb.getDiagnosisType().trim(), (String) null, "其它诊断"));
			}
		}

		return data;
	}

	public List<St002Zdxxb> getAll() {
		return this.ts.getAll();
	}

	public List<St002Zdxxb> findListByZyid(String zyid) {
		return this.a(this.ts.findListByZyid(zyid));
	}

	public List<St002Zdxxb> findListByZyidAndName(String[] diagnosisName) {
		return this.a(this.ts.findListByZyidAndName(diagnosisName));
	}

	public String findDiagnosisName(String zyid) {
		return this.ts.findDiagnosisName(zyid);
	}

	public List<St002Zdxxb> findByZyid(String zyid) {
		return this.ts.findListByZyid(zyid);
	}

	public List<St002Zdxxb> findZyZdList(int rownum) {
		return this.ts.findZyZdList(rownum);
	}

	public List<DataWarning> findPatentZdxxbWarning(Date queryStartDate, Date queryEndDate) {
		return this.ts.findPatentZdxxbWarning(queryStartDate, queryEndDate);
	}
}