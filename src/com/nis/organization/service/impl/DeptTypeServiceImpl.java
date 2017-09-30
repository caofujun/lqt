package com.nis.organization.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.organization.dao.DeptTypeDao;
import com.nis.organization.entity.DeptType;
import com.nis.organization.service.DeptTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptTypeServiceImpl implements DeptTypeService {
	@Autowired
	private DeptTypeDao vX;

	public void save(DeptType deptType) {
		this.vX.save(deptType);
	}

	public void delete(String officekindid) {
		this.vX.delete(officekindid);
	}

	public void update(DeptType deptType) {
		this.vX.update(deptType);
	}

	public DeptType get(String officekindid) {
		return this.vX.get(officekindid);
	}

	public MyPage<DeptType> a(DeptType deptType) {
		int total = this.vX.findDeptTypeCount(deptType);
		List data = null;
		if (total > 0) {
			data = this.vX.findDeptType(deptType);
		}

		return new MyPage(deptType.getPage().intValue(), deptType.getSize().intValue(), total, data);
	}

	public List<DeptType> getAll() {
		return this.vX.getAll();
	}
}