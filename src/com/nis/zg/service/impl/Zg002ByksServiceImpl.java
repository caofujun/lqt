package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.utils.ab;
import com.nis.zg.dao.Zg002ByksDao;
import com.nis.zg.entity.Zg002Byks;
import com.nis.zg.service.Zg002ByksService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg002ByksServiceImpl implements Zg002ByksService {
	@Autowired
	private Zg002ByksDao tm;

	public void save(Zg002Byks zg002Byks) {
		this.tm.save(zg002Byks);
	}

	public void delete(String id) {
		this.tm.delete(id);
	}

	public void update(Zg002Byks zg002Byks) {
		this.tm.update(zg002Byks);
	}

	public Zg002Byks get(String id) {
		return this.tm.get(id);
	}

	public Zg002Byks getByDeptId(String deptId) {
		return this.tm.getByDeptId(deptId);
	}

	public MyPage<Zg002Byks> a(Zg002Byks zg002Byks) {
		int total = this.tm.findZg002ByksCount(zg002Byks);
		List data = null;
		if (total > 0) {
			data = this.tm.findZg002Byks(zg002Byks);
		}

		return new MyPage(zg002Byks.getPage().intValue(), zg002Byks.getSize().intValue(), total, data);
	}

	public List<Zg002Byks> getAll() {
		return this.tm.getAll();
	}

	public List<TreeEntity> getRoot(String NOL) {
		List classify = this.tm.getRoot(NOL);

		for (int i = 0; i < classify.size(); ++i) {
			String name = ((TreeEntity) classify.get(i)).getId();
			String isfather = ((TreeEntity) classify.get(i)).getIsfather();
			if ("1".equals(isfather)) {
				((TreeEntity) classify.get(i)).setIconCls("icon-more");
				if (ab.isNotEmpty(name)) {
					List leaf = this.tm.getLeaf(name, NOL);
					((TreeEntity) classify.get(i)).setChildren(leaf);
				}
			}
		}

		return classify;
	}

	public List<TreeEntity> getLeaf(String classify, String NOL) {
		return this.tm.getLeaf(classify, NOL);
	}
}