package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw001JcxmDao;
import com.nis.hygiene.entity.Hw001Jcxm;
import com.nis.hygiene.service.Hw001JcxmService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw001JcxmServiceImpl implements Hw001JcxmService {
	@Autowired
	private Hw001JcxmDao rI;

	public void save(Hw001Jcxm hw001Jcxm) {
		if (StringUtils.isBlank(hw001Jcxm.getPclassId())) {
			hw001Jcxm.setPclassId("0");
		}

		if (hw001Jcxm.getFlag() == null) {
			hw001Jcxm.setFlag(Integer.valueOf(1));
		}

		if (hw001Jcxm.getLastAt() == null) {
			hw001Jcxm.setLastAt(new Date());
		}

		this.rI.save(hw001Jcxm);
	}

	public void delete(String classId) {
		this.rI.delete(classId);
	}

	public void update(Hw001Jcxm hw001Jcxm) {
		if (StringUtils.isBlank(hw001Jcxm.getPclassId())) {
			hw001Jcxm.setPclassId("0");
		}

		if (hw001Jcxm.getFlag() == null) {
			hw001Jcxm.setFlag(Integer.valueOf(0));
		}

		if (hw001Jcxm.getLastAt() == null) {
			hw001Jcxm.setLastAt(new Date());
		}

		this.rI.update(hw001Jcxm);
	}

	public void updateSpecified(Hw001Jcxm hw001Jcxm, List<String> updateAttrs) {
		if (updateAttrs.size() > 0) {
			this.rI.updateSpecified(hw001Jcxm, updateAttrs);
		}

	}

	public Hw001Jcxm get(String classId) {
		return this.rI.get(classId);
	}

	public MyPage<Hw001Jcxm> a(Hw001Jcxm hw001Jcxm) {
		int total = this.rI.findHw001JcxmCount(hw001Jcxm);
		List data = null;
		if (total > 0) {
			data = this.rI.findHw001Jcxm(hw001Jcxm);
		}

		return new MyPage(hw001Jcxm.getPage().intValue(), hw001Jcxm.getSize().intValue(), total, data);
	}

	public List<Hw001Jcxm> getAll() {
		return this.rI.getAll();
	}

	public List<Hw001Jcxm> queryTree(Hw001Jcxm hw001Jcxm) {
		List list = this.rI.queryTree(hw001Jcxm);
		LinkedList nextList = new LinkedList(list);
		LinkedList reList = new LinkedList();
		String pclassId = "0";
		Iterator arg6 = list.iterator();

		Hw001Jcxm hw001;
		while (arg6.hasNext()) {
			hw001 = (Hw001Jcxm) arg6.next();
			if (pclassId.equals(hw001.getPclassId())) {
				reList.add(hw001);
				nextList.remove(hw001);
			}
		}

		arg6 = reList.iterator();

		while (arg6.hasNext()) {
			hw001 = (Hw001Jcxm) arg6.next();
			hw001.setState("closed");
			this.a(nextList, hw001, hw001.getId());
		}

		return reList;
	}

	private void a(List<Hw001Jcxm> list, Hw001Jcxm hw1Jcxm, String pclassId) {
		LinkedList cldList = new LinkedList();
		hw1Jcxm.setChildren(cldList);

		for (int hw001 = 0; hw001 < list.size(); ++hw001) {
			Hw001Jcxm hw0011 = (Hw001Jcxm) list.get(hw001);
			if (pclassId.equals(hw0011.getPclassId())) {
				cldList.add(hw0011);
			}
		}

		if (cldList.size() == 0) {
			hw1Jcxm.setState("open");
		} else {
			Iterator arg7 = cldList.iterator();

			Hw001Jcxm arg6;
			while (arg7.hasNext()) {
				arg6 = (Hw001Jcxm) arg7.next();
				list.remove(arg6);
			}

			arg7 = cldList.iterator();

			while (arg7.hasNext()) {
				arg6 = (Hw001Jcxm) arg7.next();
				arg6.setState("open");
				this.a(list, arg6, arg6.getId());
			}

		}
	}

	public List<Hw001Jcxm> findList(Hw001Jcxm hw001Jcxm) {
		List list = this.rI.findList(hw001Jcxm);
		LinkedList nextList = new LinkedList(list);
		LinkedList reList = new LinkedList();
		String pclassId = "0";
		Iterator arg6 = list.iterator();

		Hw001Jcxm hw001;
		while (arg6.hasNext()) {
			hw001 = (Hw001Jcxm) arg6.next();
			if (pclassId.equals(hw001.getPclassId())) {
				reList.add(hw001);
				nextList.remove(hw001);
			}
		}

		arg6 = reList.iterator();

		while (arg6.hasNext()) {
			hw001 = (Hw001Jcxm) arg6.next();
			this.b(nextList, hw001, hw001.getClassId());
		}

		return reList;
	}

	private void b(List<Hw001Jcxm> list, Hw001Jcxm hw1Jcxm, String pclassId) {
		LinkedList cldList = new LinkedList();
		hw1Jcxm.setChildren(cldList);

		for (int hw001 = 0; hw001 < list.size(); ++hw001) {
			Hw001Jcxm hw0011 = (Hw001Jcxm) list.get(hw001);
			if (pclassId.equals(hw0011.getPclassId())) {
				cldList.add(hw0011);
			} else if (hw001 == 0) {
				return;
			}
		}

		if (cldList.size() != 0) {
			Iterator arg7 = cldList.iterator();

			Hw001Jcxm arg6;
			while (arg7.hasNext()) {
				arg6 = (Hw001Jcxm) arg7.next();
				list.remove(arg6);
			}

			arg7 = cldList.iterator();

			while (arg7.hasNext()) {
				arg6 = (Hw001Jcxm) arg7.next();
				this.b(list, arg6, arg6.getClassId());
			}

		}
	}

	public void b(Hw001Jcxm hw001Jcxm) {
		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("flag");
		updateAttrs.add("lastAt");
		hw001Jcxm.setLastAt(new Date());
		this.updateSpecified(hw001Jcxm, updateAttrs);
	}

	public void c(Hw001Jcxm hw001Jcxm) {
		String updAttr = "className,type,spCode,material,takeArea,takeMode,testMode,lastAt";
		List updateAttrs = Arrays.asList(updAttr.split(","));
		hw001Jcxm.setLastAt(new Date());
		this.updateSpecified(hw001Jcxm, updateAttrs);
	}

	public List<Hw001Jcxm> queryList(Hw001Jcxm hw001Jcxm) {
		return this.rI.queryList(hw001Jcxm);
	}

	public List<Hw001Jcxm> findSubclass(String pclassId) {
		return this.rI.findSubclass(pclassId);
	}

	public void delByClassIdPclassId(String classId) {
		this.rI.delByClassIdPclassId(classId);
	}
}