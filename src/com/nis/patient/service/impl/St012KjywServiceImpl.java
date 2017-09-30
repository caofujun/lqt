package com.nis.patient.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.comm.utils.z;
import com.nis.patient.dao.St012KjywDao;
import com.nis.patient.entity.St012Kjyw;
import com.nis.patient.service.St012KjywService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St012KjywServiceImpl implements St012KjywService {
	@Autowired
	private St012KjywDao tD;

	public void save(St012Kjyw st012Kjyw) {
		st012Kjyw.setId(z.a(bg.mR));
		if (ab.isEmpty(st012Kjyw.getPycode())) {
			st012Kjyw.setPycode(t.aE(st012Kjyw.getDrugName()));
		}

		st012Kjyw.setLastAt(f.getCurDate());
		this.tD.save(st012Kjyw);
	}

	public void delete(String id) {
		this.tD.delete(id);
	}

	public void update(St012Kjyw st012Kjyw) {
		this.tD.update(st012Kjyw);
	}

	public St012Kjyw get(String id) {
		return this.tD.get(id);
	}

	public MyPage<St012Kjyw> a(St012Kjyw st012Kjyw) {
		int total = this.tD.findSt012KjywCount(st012Kjyw);
		List data = null;
		if (total > 0) {
			data = this.tD.findSt012Kjyw(st012Kjyw);
		}

		return new MyPage(st012Kjyw.getPage().intValue(), st012Kjyw.getSize().intValue(), total, data);
	}

	public List<St012Kjyw> getAll() {
		return this.tD.getAll();
	}

	public Set<String> getByLevel(Integer drugLine) {
		return this.tD.getByLevel(drugLine);
	}

	public void c(String id, Integer drugLine) {
		this.tD.updDrugLine(id, drugLine, new Date());
	}

	public Integer getDrugLine(String drugName) {
		return StringUtils.isNotBlank(drugName) ? this.tD.getDrugLine(drugName) : null;
	}

	public Map<String, String> queryMatched() {
		return this.tD.queryMatched();
	}

	public void match() {
		this.tD.match();
	}
}