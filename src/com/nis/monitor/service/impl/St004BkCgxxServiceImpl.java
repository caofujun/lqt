package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.St004BkCgxxDao;
import com.nis.monitor.entity.St004BkCgxx;
import com.nis.monitor.service.St004BkCgxxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St004BkCgxxServiceImpl implements St004BkCgxxService {
	@Autowired
	private St004BkCgxxDao uN;

	public void save(St004BkCgxx st004BkCgxx) {
		st004BkCgxx.setRelid(z.a(bg.nc));
		this.uN.save(st004BkCgxx);
	}

	public void delete(String relid) {
		this.uN.delete(relid);
	}

	public void update(St004BkCgxx st004BkCgxx) {
		this.uN.update(st004BkCgxx);
	}

	public St004BkCgxx get(String relid) {
		return this.uN.get(relid);
	}

	public MyPage<St004BkCgxx> a(St004BkCgxx st004BkCgxx) {
		int total = this.uN.findSt004BkCgxxCount(st004BkCgxx);
		List data = null;
		if (total > 0) {
			data = this.uN.findSt004BkCgxx(st004BkCgxx);
		}

		return new MyPage(st004BkCgxx.getPage().intValue(), st004BkCgxx.getSize().intValue(), total, data);
	}

	public List<St004BkCgxx> getAll() {
		return this.uN.getAll();
	}

	public List<St004BkCgxx> query(St004BkCgxx st004BkCgxx) {
		return this.uN.query(st004BkCgxx);
	}

	public void deleteByRefid(String refid) {
		this.uN.deleteByRefid(refid);
	}

	public List<St004BkCgxx> findCgsybyZyid(St004BkCgxx st004BkCgxx) {
		return this.uN.findCgsybyZyid(st004BkCgxx);
	}

	public List<St004BkCgxx> findcgsyByRefid(St004BkCgxx st004BkCgxx) {
		return this.uN.findcgsyByRefid(st004BkCgxx);
	}

	public List<St004BkCgxx> findCgsyInfo(St004BkCgxx st004BkCgxx) {
		return this.uN.findCgsyInfo(st004BkCgxx);
	}
}