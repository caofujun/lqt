package com.nis.icu.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.icu.dao.By001BfgzDao;
import com.nis.icu.entity.By001Bfgz;
import com.nis.icu.service.By001BfgzService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class By001BfgzServiceImpl implements By001BfgzService {
	@Autowired
	private By001BfgzDao sk;

	public void save(By001Bfgz by001Bfgz) {
		this.sk.save(by001Bfgz);
	}

	public void delete(String outbreakTypeId) {
		this.sk.delete(outbreakTypeId);
	}

	public void update(By001Bfgz by001Bfgz) {
		this.sk.update(by001Bfgz);
	}

	public By001Bfgz get(String outbreakTypeId) {
		return this.sk.get(outbreakTypeId);
	}

	public MyPage<By001Bfgz> a(By001Bfgz by001Bfgz) {
		int total = this.sk.findBy001BfgzCount(by001Bfgz);
		List data = null;
		if (total > 0) {
			data = this.sk.findBy001Bfgz(by001Bfgz);
		}

		return new MyPage(by001Bfgz.getPage().intValue(), by001Bfgz.getSize().intValue(), total, data);
	}

	public List<By001Bfgz> getAll() {
		return this.sk.getAll();
	}
}