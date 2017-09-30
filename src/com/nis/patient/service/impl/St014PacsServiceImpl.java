package com.nis.patient.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.param.service.SysParamService;
import com.nis.patient.dao.St014PacsDao;
import com.nis.patient.entity.St014Pacs;
import com.nis.patient.service.St014PacsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St014PacsServiceImpl implements St014PacsService {
	@Autowired
	private St014PacsDao tE;
	@Autowired
	private SysParamService j;

	public void save(St014Pacs st014Pacs) {
		this.tE.save(st014Pacs);
	}

	public void delete(String id) {
		this.tE.delete(id);
	}

	public void update(St014Pacs st014Pacs) {
		this.tE.update(st014Pacs);
	}

	public St014Pacs get(String id) {
		return this.tE.get(id);
	}

	public MyPage<St014Pacs> a(St014Pacs st014Pacs) {
		int total = this.tE.findSt014PacsCount(st014Pacs);
		List data = null;
		if (total > 0) {
			data = this.tE.findSt014Pacs(st014Pacs);
		}

		return new MyPage(st014Pacs.getPage().intValue(), st014Pacs.getSize().intValue(), total, data);
	}

	public List<St014Pacs> getAll() {
		return this.tE.getAll();
	}

	public List<St014Pacs> findImageResultsList(St014Pacs st014Pacs) {
		return this.tE.findImageResultsList(st014Pacs);
	}

	public void updateAnalFlag(String zyid, String analFlag) {
		this.tE.updateAnalFlag(zyid, analFlag);
	}

	public void updateAnalResultTest(St014Pacs st014Pacs) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		st014Pacs.setTablename(yjVersion);
		this.tE.updateAnalResultTest(st014Pacs);
	}

	public List<St014Pacs> d(int rownum) {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tE.getPacsTestList(rownum, yjVersion);
	}

	public int getPacsCount() {
		String yjVersion = this.j.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.tE.getPacsCount(yjVersion);
	}

	public List<St014Pacs> getPacsListForCDC(int rownum) {
		return this.tE.getPacsListForCDC(rownum);
	}

	public Date getMonitorPatientPacsLastAt() {
		return this.tE.getMonitorPatientPacsLastAt();
	}

	public Date getMonitorPatientZkxxsLastAt() {
		return this.tE.getMonitorPatientZkxxsLastAt();
	}

	public void updateAnalFlagAndDate(St014Pacs st014Pacs) {
		this.tE.updateAnalFlagAndDate(st014Pacs);
	}
}