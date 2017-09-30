package com.nis.organization.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.organization.dao.PubcatDao;
import com.nis.organization.entity.Pubcat;
import com.nis.organization.service.PubcatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PubcatServiceImpl implements PubcatService {
	@Autowired
	private PubcatDao wb;

	public void save(Pubcat pubcat) {
		this.wb.save(pubcat);
	}

	public void delete(Long catid) {
		this.wb.delete(catid);
	}

	public void update(Pubcat pubcat) {
		this.wb.update(pubcat);
	}

	public Pubcat get(Long catid) {
		return this.wb.get(catid);
	}

	public MyPage<Pubcat> a(Pubcat pubcat) {
		int total = this.wb.findPubcatCount(pubcat);
		List data = null;
		if (total > 0) {
			data = this.wb.findPubcat(pubcat);
		}

		return new MyPage(pubcat.getPage().intValue(), pubcat.getSize().intValue(), total, data);
	}

	public List<Pubcat> getAll() {
		return this.wb.getAll();
	}
}