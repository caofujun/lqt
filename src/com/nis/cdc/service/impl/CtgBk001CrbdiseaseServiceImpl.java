package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk001CrbdiseaseDao;
import com.nis.cdc.entity.CtgBk001Crbdisease;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.service.CtgBk001CrbdiseaseService;
import com.nis.comm.entity.MyPage;
import com.nis.param.service.SysParamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk001CrbdiseaseServiceImpl implements CtgBk001CrbdiseaseService {
	@Autowired
	private CtgBk001CrbdiseaseDao dK;
	@Autowired
	private SysParamService j;

	public void save(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.save(ctgBk001Crbdisease);
	}

	public void delete(String masterid) {
		this.dK.delete(masterid);
	}

	public void update(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.update(ctgBk001Crbdisease);
	}

	public List<CtgBk001Crbdisease> get(String masterid) {
		return this.dK.get(masterid);
	}

	public MyPage<CtgBk001Crbdisease> a(CtgBk001Crbdisease ctgBk001Crbdisease) {
		int total = this.dK.findCtgBk001CrbdiseaseCount(ctgBk001Crbdisease);
		List data = null;
		if (total > 0) {
			data = this.dK.findCtgBk001Crbdisease(ctgBk001Crbdisease);
		}

		return new MyPage(ctgBk001Crbdisease.getPage().intValue(), ctgBk001Crbdisease.getSize().intValue(), total,
				data);
	}

	public List<CtgBk001Crbdisease> getAll() {
		return this.dK.getAll();
	}

	public void audit(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.audit(ctgBk001Crbdisease);
	}

	public void retreat(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.retreat(ctgBk001Crbdisease);
	}

	public void cancel(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.cancel(ctgBk001Crbdisease);
	}

	public void remove(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.remove(ctgBk001Crbdisease);
	}

	public void updateDiseaseNotes(String subid, String diseasenotes) {
		this.dK.updateDiseaseNotes(subid, diseasenotes);
	}

	public void batchAudit(CtgBk001Crbdisease ctgBk001Crbdisease) {
		this.dK.batchAudit(ctgBk001Crbdisease);
	}

	public void a(CtgBk001Crbmaster ctgBk001Crbmaster) {
		this.dK.updateZBFlag(ctgBk001Crbmaster.getSubid());
	}
}