package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.hygiene.dao.Hw104JcdjgXjDao;
import com.nis.hygiene.entity.Hw104JcdjgXj;
import com.nis.hygiene.service.Hw104JcdjgXjService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw104JcdjgXjServiceImpl implements Hw104JcdjgXjService {
	@Autowired
	private Hw104JcdjgXjDao rZ;

	public void save(Hw104JcdjgXj hw104JcdjgXj) {
		hw104JcdjgXj.setId(z.a(bg.nI));
		this.rZ.save(hw104JcdjgXj);
	}

	public void delete(String id) {
		this.rZ.delete(id);
	}

	public void update(Hw104JcdjgXj hw104JcdjgXj) {
		this.rZ.update(hw104JcdjgXj);
	}

	public Hw104JcdjgXj get(String id) {
		return this.rZ.get(id);
	}

	public MyPage<Hw104JcdjgXj> a(Hw104JcdjgXj hw104JcdjgXj) {
		int total = this.rZ.findHw104JcdjgXjCount(hw104JcdjgXj);
		List data = null;
		if (total > 0) {
			data = this.rZ.findHw104JcdjgXj(hw104JcdjgXj);
		}

		return new MyPage(hw104JcdjgXj.getPage().intValue(), hw104JcdjgXj.getSize().intValue(), total, data);
	}

	public List<Hw104JcdjgXj> getAll() {
		return this.rZ.getAll();
	}

	public void delHw104(List<String> pathoIdNotIn, String hw102Id) {
		this.rZ.delHw104(pathoIdNotIn, hw102Id);
	}

	public void delByHw102Id(String hw102Id) {
		this.rZ.delByHw102Id(hw102Id);
	}

	public List<String> findPathoIdByHw102Id(String hw102Id) {
		return this.rZ.findPathoIdByHw102Id(hw102Id);
	}

	public List<Hw104JcdjgXj> findListByHw102Id(String hw102Id) {
		return this.rZ.findListByHw102Id(hw102Id);
	}

	public List<String> findPathoNameByHw102Id(String hw102Id) {
		return this.rZ.findPathoNameByHw102Id(hw102Id);
	}
}