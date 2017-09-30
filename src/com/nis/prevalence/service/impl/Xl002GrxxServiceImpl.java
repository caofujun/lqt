package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.prevalence.dao.Xl002GrxxDao;
import com.nis.prevalence.entity.Xl002Grxx;
import com.nis.prevalence.service.Xl002GrxxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl002GrxxServiceImpl implements Xl002GrxxService {
	@Autowired
	private Xl002GrxxDao wV;

	public void save(Xl002Grxx xl002Grxx) {
		xl002Grxx.setGrid(z.a(bg.nh));
		this.wV.save(xl002Grxx);
	}

	public void delete(String grid) {
		this.wV.delete(grid);
	}

	public void update(Xl002Grxx xl002Grxx) {
		this.wV.update(xl002Grxx);
	}

	public void updateSpecified(Xl002Grxx xl002Grxx, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.wV.updateSpecified(xl002Grxx, updateAttrs);
		}

	}

	public Xl002Grxx get(String grid) {
		return this.wV.get(grid);
	}

	public MyPage<Xl002Grxx> a(Xl002Grxx xl002Grxx) {
		int total = this.wV.findXl002GrxxCount(xl002Grxx);
		List data = null;
		if (total > 0) {
			data = this.wV.findXl002Grxx(xl002Grxx);
		}

		return new MyPage(xl002Grxx.getPage().intValue(), xl002Grxx.getSize().intValue(), total, data);
	}

	public List<Xl002Grxx> getAll() {
		return this.wV.getAll();
	}

	public List<Xl002Grxx> queryByBrid(String brid) {
		return this.wV.queryByBrid(brid);
	}

	public void delXl002Grxx(List<String> gridNotIn, String brid) {
		this.wV.delXl002Grxx(gridNotIn, brid);
	}

	public void deleteByBrid(String brid) {
		this.wV.deleteByBrid(brid);
	}
}