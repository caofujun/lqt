package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys014TodolistDao;
import com.nis.cdc.entity.CtgSys014Todolist;
import com.nis.cdc.service.CtgSys014TodolistService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.af;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys014TodolistServiceImpl implements CtgSys014TodolistService {
	@Autowired
	private CtgSys014TodolistDao eo;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;

	public void save(CtgSys014Todolist ctgSys014Todolist) {
		this.eo.save(ctgSys014Todolist);
	}

	public void delete(String keyid) {
		this.eo.delete(keyid);
	}

	public void update(CtgSys014Todolist ctgSys014Todolist) {
		this.eo.update(ctgSys014Todolist);
	}

	public CtgSys014Todolist get(String keyid) {
		return this.eo.get(keyid);
	}

	public MyPage<CtgSys014Todolist> c(CtgSys014Todolist ctgSys014Todolist) {
		int total = this.eo.findCtgSys014TodolistCount(ctgSys014Todolist);
		List data = null;
		if (total > 0) {
			data = this.eo.findCtgSys014Todolist(ctgSys014Todolist);
		}

		return new MyPage(ctgSys014Todolist.getPage().intValue(), ctgSys014Todolist.getSize().intValue(), total, data);
	}

	public List<CtgSys014Todolist> getAll() {
		return this.eo.getAll();
	}

	public Result<String> a(CtgSys014Todolist ctgSys014Todolist) {
		Result result = new Result();

		try {
			ctgSys014Todolist.setKeyid(af.getUUID32());
			System.err.println("============" + ctgSys014Todolist.getPatientType().toUpperCase());
			ctgSys014Todolist.setPatientType(ctgSys014Todolist.getPatientType().toUpperCase());
			List e = this.eo.isExist(ctgSys014Todolist);
			if (e != null && e.isEmpty()) {
				String pt = ctgSys014Todolist.getPatientType();
				if ("ZY".equals(pt)) {
					St003Cryxxb clinicPatients = this.bg.get(ctgSys014Todolist.getMzzyid());
					if (clinicPatients == null) {
						result.setResult("error");
						result.setMsg("保存失败，未找到该患者信息！");
					} else {
						ctgSys014Todolist.setPatientName(clinicPatients.getPatientName());
						this.eo.save(ctgSys014Todolist);
						result.setResult("success");
					}
				} else if ("MZ".equals(pt)) {
					St020ClinicPatients clinicPatients1 = this.bh.get(ctgSys014Todolist.getMzzyid());
					if (clinicPatients1 == null) {
						result.setResult("error");
						result.setMsg("保存失败，未找到该患者信息！");
					} else {
						ctgSys014Todolist.setPatientName(clinicPatients1.getPatientName());
						this.eo.save(ctgSys014Todolist);
						result.setResult("success");
					}
				}
			}
		} catch (Exception arg5) {
			arg5.printStackTrace();
			result.setResult("error");
			result.setMsg("保存失败，错误信息：" + arg5.getMessage());
		}

		return result;
	}

	public List<CtgSys014Todolist> isExist(CtgSys014Todolist ctgSys014Todolist) {
		return this.eo.isExist(ctgSys014Todolist);
	}

	public Result<String> b(CtgSys014Todolist ctgSys014Todolist) {
		Result result = new Result();

		try {
			this.eo.removeByZyidPatientType(ctgSys014Todolist);
			result.setResult("success");
		} catch (Exception arg3) {
			arg3.printStackTrace();
			result.setResult("error");
			result.setMsg("移除记录失败，错误信息：" + arg3.getMessage());
		}

		return result;
	}
}