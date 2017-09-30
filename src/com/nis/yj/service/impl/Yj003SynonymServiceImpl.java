package com.nis.yj.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.yj.dao.Yj003SynonymDao;
import com.nis.yj.entity.Yj003Synonym;
import com.nis.yj.service.Yj003SynonymService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Yj003SynonymServiceImpl implements Yj003SynonymService {
	@Autowired
	private Yj003SynonymDao yi;

	public void save(Yj003Synonym yj003Synonym) {
		yj003Synonym.setId(z.a(bg.nR));
		this.yi.save(yj003Synonym);
	}

	public void delete(String id) {
		this.yi.delete(id);
	}

	public void update(Yj003Synonym yj003Synonym) {
		this.yi.update(yj003Synonym);
	}

	public void match() {
		this.yi.match();
	}

	public Map<String, String> queryMatched() {
		return this.yi.queryMatched();
	}

	public Yj003Synonym get(String id) {
		return this.yi.get(id);
	}

	public MyPage<Yj003Synonym> a(Yj003Synonym yj003Synonym) {
		int total = this.yi.findYj003SynonymCount(yj003Synonym);
		List data = null;
		if (total > 0) {
			data = this.yi.findYj003Synonym(yj003Synonym);
		}

		return new MyPage(yj003Synonym.getPage().intValue(), yj003Synonym.getSize().intValue(), total, data);
	}

	public List<Yj003Synonym> getAll() {
		return this.yi.getAll();
	}

	public MyPage<Yj003Synonym> b(Yj003Synonym yj003Synonym) {
		int total = this.yi.findYj003SynonymListCount(yj003Synonym);
		List data = null;
		if (total > 0) {
			data = this.yi.findYj003SynonymList(yj003Synonym);
		}

		return new MyPage(yj003Synonym.getPage().intValue(), yj003Synonym.getSize().intValue(), total, data);
	}
}