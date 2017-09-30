package com.nis.patient.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.patient.dao.St001JbxxbDao;
import com.nis.patient.entity.PatientView;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.service.St001JbxxbService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St001JbxxbServiceImpl implements St001JbxxbService {
	@Autowired
	private St001JbxxbDao tq;
	@Autowired
	private SysDictService p;

	public void save(St001Jbxxb st001Jbxxb) {
		this.tq.save(st001Jbxxb);
	}

	public void delete(String id) {
		this.tq.delete(id);
	}

	public void update(St001Jbxxb st001Jbxxb) {
		this.tq.update(st001Jbxxb);
	}

	public St001Jbxxb get(String id) {
		return this.tq.get(id);
	}

	public MyPage<St001Jbxxb> a(St001Jbxxb st001Jbxxb) {
		int total = this.tq.findSt001JbxxbCount(st001Jbxxb);
		List data = null;
		if (total > 0) {
			data = this.tq.findSt001Jbxxb(st001Jbxxb);
		}

		return new MyPage(st001Jbxxb.getPage().intValue(), st001Jbxxb.getSize().intValue(), total, data);
	}

	public List<St001Jbxxb> getAll() {
		return this.tq.getAll();
	}

	public List<PatientView> findbyZyid(String zyid, String inHospAt, String startDate, String endDate) {
		return this.tq.findbyZyid(zyid, inHospAt, startDate, endDate);
	}

	public void updWeightByZyid(String zyid, String weight) {
		this.tq.updWeightByZyid(zyid, weight);
	}

	public List<St001Jbxxb> getByPatientId(String patientId) {
		return this.tq.getByPatientId(patientId);
	}

	public List<St009Sjbb> K(String zyid, String classCode) {
		ArrayList remark = new ArrayList();
		List hilist = this.p.u("result_high", (String) null);
		Iterator dict = hilist.iterator();

		while (dict.hasNext()) {
			SysDict loList = (SysDict) dict.next();
			remark.add(loList.getDictCode());
		}

		List loList1 = this.p.u("result_low", (String) null);
		Iterator arg6 = loList1.iterator();

		while (arg6.hasNext()) {
			SysDict dict1 = (SysDict) arg6.next();
			remark.add(dict1.getDictCode());
		}

		return this.tq.findbyZyidAndClassCode(zyid, classCode, remark);
	}
}