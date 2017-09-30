package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.comm.utils.z;
import com.nis.dict.dao.Icd10Dao;
import com.nis.dict.entity.Icd10;
import com.nis.dict.service.Icd10Service;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Icd10ServiceImpl implements Icd10Service {
	@Autowired
	private Icd10Dao qF;

	public void save(Icd10 icd10) {
		icd10.setIcdId(z.a(bg.mP));
		icd10.setLastAt(f.getCurDate());
		if (StringUtils.isBlank(icd10.getSpCode())) {
			icd10.setSpCode(t.aE(icd10.getIcdName()));
		}

		this.qF.save(icd10);
	}

	public void delete(String icdId) {
		this.qF.delete(icdId);
	}

	public void update(Icd10 icd10) {
		icd10.setLastAt(f.getCurDate());
		this.qF.update(icd10);
	}

	public Icd10 get(String icdId) {
		return this.qF.get(icdId);
	}

	public MyPage<Icd10> a(Icd10 icd10) {
		int total = this.qF.findIcd10Count(icd10);
		List data = null;
		if (total > 0) {
			data = this.qF.findIcd10(icd10);
		}

		return new MyPage(icd10.getPage().intValue(), icd10.getSize().intValue(), total, data);
	}

	public List<Icd10> getAll() {
		return this.qF.getAll();
	}

	public List<Icd10> bs(String searchString) {
		Icd10 searchIcd10 = new Icd10();
		searchIcd10.setSearchString(searchString);
		return this.qF.findIcd10(searchIcd10);
	}
}