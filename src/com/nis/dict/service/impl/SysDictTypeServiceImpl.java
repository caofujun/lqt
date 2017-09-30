package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.dict.dao.SysDictTypeDao;
import com.nis.dict.entity.SysDictType;
import com.nis.dict.service.SysDictTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysDictTypeServiceImpl implements SysDictTypeService {
	@Autowired
	private SysDictTypeDao qL;

	public void save(SysDictType sysDictType) {
		sysDictType.setId(z.a(bg.mt));
		this.qL.save(sysDictType);
	}

	public void delete(String id) {
		this.qL.delete(id);
	}

	public void update(SysDictType sysDictType) {
		this.qL.update(sysDictType);
	}

	public SysDictType get(String id) {
		return this.qL.get(id);
	}

	public MyPage<SysDictType> a(SysDictType sysDictType) {
		int total = this.qL.findSysDictTypeCount(sysDictType);
		List data = null;
		if (total > 0) {
			data = this.qL.findSysDictType(sysDictType);
		}

		return new MyPage(sysDictType.getPage().intValue(), sysDictType.getSize().intValue(), total, data);
	}

	public List<SysDictType> getAll(SysDictType sysDictType) {
		return this.qL.getAll(sysDictType);
	}
}