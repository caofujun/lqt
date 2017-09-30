package com.nis.bl.service.impl;

import com.nis.bl.dao.Bl007FcsjDao;
import com.nis.bl.entity.Bl004CsDetailinfo;
import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.entity.JyjgFc;
import com.nis.bl.service.Bl004CsDetailinfoService;
import com.nis.bl.service.Bl007FcsjService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bl007FcsjServiceImpl implements Bl007FcsjService {
	@Autowired
	private Bl007FcsjDao de;
	@Autowired
	private Bl004CsDetailinfoService cW;
	@Autowired
	private SysParamService j;
	@Autowired
	private NyMessageDetailService cV;

	public void save(Bl007Fcsj bl007Fcsj) {
		bl007Fcsj.setRelid(z.a(bg.nM));
		this.de.save(bl007Fcsj);
	}

	public void delete(String relid) {
		this.de.delete(relid);
	}

	public void update(Bl007Fcsj bl007Fcsj) {
		this.de.update(bl007Fcsj);
	}

	public Bl007Fcsj get(String relid) {
		return this.de.get(relid);
	}

	public MyPage<Bl007Fcsj> a(Bl007Fcsj bl007Fcsj) {
		int total = this.de.findBl007FcsjCount(bl007Fcsj);
		List data = null;
		if (total > 0) {
			data = this.de.findBl007Fcsj(bl007Fcsj);
		}

		return new MyPage(bl007Fcsj.getPage().intValue(), bl007Fcsj.getSize().intValue(), total, data);
	}

	public List<Bl007Fcsj> getAll() {
		return this.de.getAll();
	}

	public List<Bl007Fcsj> findBl007FcsjList(Bl007Fcsj bl007Fcsj) {
		return this.de.findBl007FcsjList(bl007Fcsj);
	}

	public void b(String blId, List<Bl004CsDetailinfo> cList) {
		ArrayList fcsjList = new ArrayList();
		this.de.deleteByBlid(blId);
		Iterator arg4 = cList.iterator();

		while (arg4.hasNext()) {
			Bl004CsDetailinfo cs = (Bl004CsDetailinfo) arg4.next();
			Bl007Fcsj fcsj = new Bl007Fcsj();
			fcsj.setRelid(z.a(bg.nM));
			fcsj.setCsmId(cs.getCsmId());
			fcsj.setCsdId(cs.getCsdId());
			fcsj.setSjId("XY_TY_DJ");
			fcsj.setFlag(Long.valueOf(1L));
			fcsj.setFc(cs.getFcDate());
			fcsj.setFcZt(Long.valueOf(0L));
			fcsj.setBlId(blId);
			fcsjList.add(fcsj);
		}

		this.de.saveList(fcsjList);
	}

	public List<Bl007Fcsj> findByBlId(String blId) {
		return this.de.findByBlId(blId);
	}

	public List<JyjgFc> findByTime(String startDate, String endDate) {
		return this.de.findByTime(startDate, endDate);
	}

	public void o() {
		List fcsjList = this.de.findByDays(Integer.valueOf(this.j.findByParamCode(Param.NIS_BL_FC_DAYS)));
	}

	public List<Bl007Fcsj> getFcList(Integer days) {
		return this.de.getFcList(days);
	}

	public void deleteByBlid(String blId) {
		this.de.deleteByBlid(blId);
	}
}