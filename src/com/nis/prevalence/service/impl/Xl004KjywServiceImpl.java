package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.prevalence.dao.Xl004KjywDao;
import com.nis.prevalence.entity.Xl004Kjyw;
import com.nis.prevalence.service.Xl004KjywService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl004KjywServiceImpl implements Xl004KjywService {
	@Autowired
	private Xl004KjywDao wX;

	public void save(Xl004Kjyw xl004Kjyw) {
		xl004Kjyw.setYjywid(z.a(bg.nj));
		this.wX.save(xl004Kjyw);
	}

	public void delete(String yjywid) {
		this.wX.delete(yjywid);
	}

	public void update(Xl004Kjyw xl004Kjyw) {
		this.wX.update(xl004Kjyw);
	}

	public Xl004Kjyw get(String yjywid) {
		return this.wX.get(yjywid);
	}

	public MyPage<Xl004Kjyw> a(Xl004Kjyw xl004Kjyw) {
		int total = this.wX.findXl004KjywCount(xl004Kjyw);
		List data = null;
		if (total > 0) {
			data = this.wX.findXl004Kjyw(xl004Kjyw);
		}

		return new MyPage(xl004Kjyw.getPage().intValue(), xl004Kjyw.getSize().intValue(), total, data);
	}

	public List<Xl004Kjyw> getAll() {
		return this.wX.getAll();
	}

	public void delXl004Kjyw(List<String> yjywidNotIn, String brid) {
		this.wX.delXl004Kjyw(yjywidNotIn, brid);
	}

	public void deleteByBrid(String brid) {
		this.wX.deleteByBrid(brid);
	}
}