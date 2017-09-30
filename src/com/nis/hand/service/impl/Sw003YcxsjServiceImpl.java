package com.nis.hand.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hand.dao.Sw003YcxsjDao;
import com.nis.hand.entity.Sw003Ycxsj;
import com.nis.hand.service.Sw003YcxsjService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sw003YcxsjServiceImpl implements Sw003YcxsjService {
	@Autowired
	private Sw003YcxsjDao rf;

	public void save(Sw003Ycxsj sw003Ycxsj) {
		this.rf.save(sw003Ycxsj);
	}

	public void delete(String dcid) {
		this.rf.delete(dcid);
	}

	public void update(Sw003Ycxsj sw003Ycxsj) {
		this.rf.update(sw003Ycxsj);
	}

	public Sw003Ycxsj get(String sjid) {
		return this.rf.get(sjid);
	}

	public MyPage<Sw003Ycxsj> a(Sw003Ycxsj sw003Ycxsj) {
		int total = this.rf.findSw003YcxsjCount(sw003Ycxsj);
		List data = null;
		if (total > 0) {
			data = this.rf.findSw003Ycxsj(sw003Ycxsj);
		}

		return new MyPage(sw003Ycxsj.getPage().intValue(), sw003Ycxsj.getSize().intValue(), total, data);
	}

	public List<Sw003Ycxsj> getAll() {
		return this.rf.getAll();
	}

	public List<Sw003Ycxsj> getByDcid(String dcId) {
		return this.rf.getByDcid(dcId);
	}

	public void deleteByDcid(String dcId) {
		this.rf.deleteByDcid(dcId);
	}
}