package com.nis.outbreak.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.outbreak.dao.By007DataDao;
import com.nis.outbreak.entity.By007Data;
import com.nis.outbreak.service.By007DataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class By007DataServiceImpl implements By007DataService {
	@Autowired
	private By007DataDao wl;

	public void save(By007Data by007Data) {
		this.wl.save(by007Data);
	}

	public void delete() {
		this.wl.delete();
	}

	public void update(By007Data by007Data) {
		this.wl.update(by007Data);
	}

	public By007Data get() {
		return this.wl.get();
	}

	public MyPage<By007Data> a(By007Data by007Data) {
		int total = this.wl.findBy007DataCount(by007Data);
		List data = null;
		if (total > 0) {
			data = this.wl.findBy007Data(by007Data);
		}

		return new MyPage(by007Data.getPage().intValue(), by007Data.getSize().intValue(), total, data);
	}

	public List<By007Data> getAll() {
		return this.wl.getAll();
	}
}