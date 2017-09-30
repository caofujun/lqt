package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Gr018BcgrysDao;
import com.nis.analysis.entity.Gr018Bcgrys;
import com.nis.analysis.service.Gr018BcgrysService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.param.service.SysParamService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr018BcgrysServiceImpl implements Gr018BcgrysService {
	@Autowired
	private Gr018BcgrysDao cr;
	@Autowired
	private SysParamService cs;

	public void save(Gr018Bcgrys gr018Bcgrys) {
		this.cr.save(gr018Bcgrys);
	}

	public void saveList(List<Gr018Bcgrys> gr018BcgrysList) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.cr.saveList(gr018BcgrysList, yjVersion);
	}

	public void delete(String zyid, String bcid) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		this.cr.delete(zyid, bcid, yjVersion);
	}

	public void update(Gr018Bcgrys gr018Bcgrys) {
		this.cr.update(gr018Bcgrys);
	}

	public Gr018Bcgrys get(String zyid) {
		return this.cr.get(zyid);
	}

	public MyPage<Gr018Bcgrys> a(Gr018Bcgrys gr018Bcgrys) {
		int total = this.cr.findGr018BcgrysCount(gr018Bcgrys);
		List data = null;
		if (total > 0) {
			data = this.cr.findGr018Bcgrys(gr018Bcgrys);
		}

		return new MyPage(gr018Bcgrys.getPage().intValue(), gr018Bcgrys.getSize().intValue(), total, data);
	}

	public List<Gr018Bcgrys> getAll() {
		return this.cr.getAll();
	}

	public Date getMonitorPatientBcLastAt() {
		return this.cr.getMonitorPatientBcLastAt();
	}

	public int findGrysCount(String zyid, String bcid) {
		String yjVersion = this.cs.findByParamCode(Param.NIS_YJ_IS_VERSION);
		return this.cr.findGrysCount(zyid, bcid, yjVersion);
	}
}