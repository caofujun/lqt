package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg012Icd10Dao;
import com.nis.zg.entity.Zg012Icd10;
import com.nis.zg.service.Zg012Icd10Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg012Icd10ServiceImpl implements Zg012Icd10Service {
	@Autowired
	private Zg012Icd10Dao tl;

	public void save(Zg012Icd10 zg012Icd10) {
		this.tl.save(zg012Icd10);
	}

	public void delete(String icdId) {
		this.tl.delete(icdId);
	}

	public void update(Zg012Icd10 zg012Icd10) {
		this.tl.update(zg012Icd10);
	}

	public Zg012Icd10 get(String icdId) {
		return this.tl.get(icdId);
	}

	public MyPage<Zg012Icd10> a(Zg012Icd10 zg012Icd10) {
		int total = this.tl.findZg012Icd10Count(zg012Icd10);
		List data = null;
		if (total > 0) {
			data = this.tl.findZg012Icd10(zg012Icd10);
		}

		return new MyPage(zg012Icd10.getPage().intValue(), zg012Icd10.getSize().intValue(), total, data);
	}

	public List<Zg012Icd10> getAll() {
		return this.tl.getAll();
	}
}