package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.dao.Icd9Dao;
import com.nis.dict.entity.Icd9;
import com.nis.dict.service.Icd9Service;
import com.nis.dict.service.SysDictService;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Icd9ServiceImpl implements Icd9Service {
	@Autowired
	private Icd9Dao qG;
	@Autowired
	private SysDictService p;

	public void save(Icd9 icd9) {
		icd9.setIcdId(z.a(bg.mQ));
		icd9.setLastAt(f.getCurDate());
		this.qG.save(icd9);
	}

	public void delete(String icdId) {
		this.qG.delete(icdId);
	}

	public void update(Icd9 icd9) {
		icd9.setLastAt(f.getCurDate());
		this.qG.update(icd9);
	}

	public Icd9 get(String icdId) {
		return this.qG.get(icdId);
	}

	public MyPage<Icd9> a(Icd9 icd9) {
		int total = this.qG.findIcd9Count(icd9);
		List data = null;
		if (total > 0) {
			data = this.qG.findIcd9(icd9);
		}

		return new MyPage(icd9.getPage().intValue(), icd9.getSize().intValue(), total, data);
	}

	private List<Icd9> a(List<Icd9> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			Icd9 icd9 = (Icd9) arg2.next();
			if (StringUtils.isNotBlank(icd9.getOpesysId())) {
				icd9.setShowOpesysId(this.p.k("icd9_type", icd9.getOpesysId().trim(), (String) null));
			}

			if (StringUtils.isNotBlank(icd9.getOpepartKindid())) {
				icd9.setShowOpepartKindid(this.p.k("icd9_opekind", icd9.getOpepartKindid().trim(), (String) null));
			}

			if (StringUtils.isNotBlank(icd9.getImpOpeId())) {
				icd9.setShowImpOpeId(this.p.k("boolean", icd9.getImpOpeId().trim(), (String) null));
			}
		}

		return data;
	}

	public List<Icd9> getAll() {
		return this.qG.getAll();
	}

	public List<Icd9> bt(String searchString) {
		Icd9 searchIcd9 = new Icd9();
		searchIcd9.setSearchString(searchString);
		return this.qG.findIcd9(searchIcd9);
	}
}