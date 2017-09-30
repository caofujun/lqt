package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw008XmsqDao;
import com.nis.hygiene.dao.Hw009KssqDao;
import com.nis.hygiene.dao.Hw010ZzryDao;
import com.nis.hygiene.dao.Hw018RoleUserDao;
import com.nis.hygiene.entity.Hw010Zzry;
import com.nis.hygiene.service.Hw010ZzryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw010ZzryServiceImpl implements Hw010ZzryService {
	@Autowired
	private Hw010ZzryDao rT;
	@Autowired
	private Hw008XmsqDao rQ;
	@Autowired
	private Hw009KssqDao rS;
	@Autowired
	private Hw018RoleUserDao rR;

	public void save(Hw010Zzry hw010Zzry) {
		this.rT.save(hw010Zzry);
	}

	public void delete(String employeeId) {
		this.rT.delete(employeeId);
	}

	public void update(Hw010Zzry hw010Zzry) {
		this.rT.update(hw010Zzry);
	}

	public Hw010Zzry get(String employeeId) {
		return this.rT.get(employeeId);
	}

	public MyPage<Hw010Zzry> a(Hw010Zzry hw010Zzry) {
		int total = this.rT.findHw010ZzryCount(hw010Zzry);
		List data = null;
		if (total > 0) {
			data = this.rT.findHw010Zzry(hw010Zzry);
		}

		return new MyPage(hw010Zzry.getPage().intValue(), hw010Zzry.getSize().intValue(), total, data);
	}

	public List<Hw010Zzry> getAll() {
		return this.rT.getAll();
	}

	public List<Hw010Zzry> queryList(Hw010Zzry hw010Zzry) {
		return this.rT.queryList(hw010Zzry);
	}

	public MyPage<Hw010Zzry> b(Hw010Zzry hw010Zzry) {
		int total = this.rT.findAccreditListCount(hw010Zzry);
		List data = null;
		if (total > 0) {
			data = this.rT.findAccreditList(hw010Zzry);
		}

		return new MyPage(hw010Zzry.getPage().intValue(), hw010Zzry.getSize().intValue(), total, data);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void x(String employeeId, String deptId) {
		this.delete(employeeId);
		this.rQ.delByUserIdAndDeptId(employeeId);
		this.rS.deleteByUserId(employeeId);
		this.rR.delByUserIdAndDeptId(employeeId);
	}
}