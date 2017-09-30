package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.hygiene.dao.Hw103JcdjgDao;
import com.nis.hygiene.entity.Hw103Jcdjg;
import com.nis.hygiene.service.Hw103JcdjgService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw103JcdjgServiceImpl implements Hw103JcdjgService {
	@Autowired
	private Hw103JcdjgDao rY;

	public void save(Hw103Jcdjg hw103Jcdjg) {
		hw103Jcdjg.setId(z.a(bg.nH));
		if (hw103Jcdjg.getResultFlag() == null) {
			hw103Jcdjg.setResultFlag(Integer.valueOf(-1));
		}

		this.rY.save(hw103Jcdjg);
	}

	public void delete(String id) {
		this.rY.delete(id);
	}

	public void update(Hw103Jcdjg hw103Jcdjg) {
		this.rY.update(hw103Jcdjg);
	}

	public void updateSpecified(Hw103Jcdjg hw103Jcdjg, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.rY.updateSpecified(hw103Jcdjg, updateAttrs);
		}

	}

	public Hw103Jcdjg get(String id) {
		return this.rY.get(id);
	}

	public MyPage<Hw103Jcdjg> a(Hw103Jcdjg hw103Jcdjg) {
		int total = this.rY.findHw103JcdjgCount(hw103Jcdjg);
		List data = null;
		if (total > 0) {
			data = this.rY.findHw103Jcdjg(hw103Jcdjg);
		}

		return new MyPage(hw103Jcdjg.getPage().intValue(), hw103Jcdjg.getSize().intValue(), total, data);
	}

	public List<Hw103Jcdjg> getAll() {
		return this.rY.getAll();
	}

	public List<Hw103Jcdjg> findListByHw102Id(String hw102Id, String result) {
		return this.rY.findListByHw102Id(hw102Id, result);
	}

	public void delByHw102Id(String hw102Id) {
		this.rY.delByHw102Id(hw102Id);
	}
}