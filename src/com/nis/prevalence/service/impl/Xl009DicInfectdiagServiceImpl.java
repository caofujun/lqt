package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.prevalence.dao.Xl009DicInfectdiagDao;
import com.nis.prevalence.entity.Xl009DicInfectdiag;
import com.nis.prevalence.service.Xl009DicInfectdiagService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl009DicInfectdiagServiceImpl implements Xl009DicInfectdiagService {
	@Autowired
	private Xl009DicInfectdiagDao wZ;

	public void save(Xl009DicInfectdiag xl009DicInfectdiag) {
		this.wZ.save(xl009DicInfectdiag);
	}

	public void delete(String indiagid) {
		this.wZ.delete(indiagid);
	}

	public void update(Xl009DicInfectdiag xl009DicInfectdiag) {
		this.wZ.update(xl009DicInfectdiag);
	}

	public Xl009DicInfectdiag get(String indiagid) {
		return this.wZ.get(indiagid);
	}

	public MyPage<Xl009DicInfectdiag> a(Xl009DicInfectdiag xl009DicInfectdiag) {
		int total = this.wZ.findXl009DicInfectdiagCount(xl009DicInfectdiag);
		List data = null;
		if (total > 0) {
			data = this.wZ.findXl009DicInfectdiag(xl009DicInfectdiag);
		}

		return new MyPage(xl009DicInfectdiag.getPage().intValue(), xl009DicInfectdiag.getSize().intValue(), total,
				data);
	}

	public List<Xl009DicInfectdiag> getAll() {
		return this.wZ.getAll();
	}

	public List<Xl009DicInfectdiag> b(Xl009DicInfectdiag xl009DicInfectdiag) {
		return this.wZ.findXl009DicInfectdiag(xl009DicInfectdiag);
	}

	public List<TreeEntity> queryTree() {
		List list = this.wZ.queryTree();
		ArrayList treeList = new ArrayList();
		LinkedHashMap m = new LinkedHashMap();
		Iterator arg4 = list.iterator();

		while (arg4.hasNext()) {
			Xl009DicInfectdiag s = (Xl009DicInfectdiag) arg4.next();
			m.put(s.getSystemkindid(), s.getSystemkindname());
		}

		arg4 = m.keySet().iterator();

		while (arg4.hasNext()) {
			String s1 = (String) arg4.next();
			TreeEntity d = new TreeEntity();
			d.setId(s1);
			d.setText((String) m.get(s1));
			d.setState("closed");
			ArrayList leaf = new ArrayList();
			Iterator arg8 = list.iterator();

			while (arg8.hasNext()) {
				Xl009DicInfectdiag z = (Xl009DicInfectdiag) arg8.next();
				if (z.getSystemkindid().equals(s1)) {
					TreeEntity t = new TreeEntity();
					t.setId(z.getId());
					t.setText(z.getText());
					leaf.add(t);
				}
			}

			d.setChildren(leaf);
			treeList.add(d);
		}

		return treeList;
	}
}