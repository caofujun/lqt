package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.dict.dao.Zg005YygrzdDao;
import com.nis.dict.entity.Zg005Yygrzd;
import com.nis.dict.service.Zg005YygrzdService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg005YygrzdServiceImpl implements Zg005YygrzdService {
	@Autowired
	private Zg005YygrzdDao qN;

	public void save(Zg005Yygrzd zg005Yygrzd) {
		zg005Yygrzd.setLastAt(f.getCurDate());
		if (StringUtils.isBlank(zg005Yygrzd.getSpCode())) {
			zg005Yygrzd.setSpCode(t.aE(zg005Yygrzd.getInfectName()));
		}

		this.qN.save(zg005Yygrzd);
	}

	public void delete(String infectCode) {
		this.qN.delete(infectCode);
	}

	public void update(Zg005Yygrzd zg005Yygrzd) {
		zg005Yygrzd.setLastAt(f.getCurDate());
		this.qN.update(zg005Yygrzd);
	}

	public void updFlag(String infectCode, Integer flag, Date lastAt) {
		this.qN.updFlag(infectCode, flag, lastAt);
	}

	public Zg005Yygrzd get(String infectCode) {
		return this.qN.get(infectCode);
	}

	public MyPage<Zg005Yygrzd> a(Zg005Yygrzd zg005Yygrzd) {
		int total = this.qN.findZg005YygrzdCount(zg005Yygrzd);
		List data = null;
		if (total > 0) {
			data = this.qN.findZg005Yygrzd(zg005Yygrzd);
		}

		return new MyPage(zg005Yygrzd.getPage().intValue(), zg005Yygrzd.getSize().intValue(), total, data);
	}

	public List<Zg005Yygrzd> getAll() {
		return this.qN.getAll();
	}

	public List<Zg005Yygrzd> b(Zg005Yygrzd zg005Yygrzd) {
		List list = this.qN.queryTree(zg005Yygrzd);
		ArrayList nextList = new ArrayList(list);
		ArrayList reList = new ArrayList();
		String pclassId = zg005Yygrzd.getPInfectCode();
		Iterator arg6 = list.iterator();

		Zg005Yygrzd zg005;
		while (arg6.hasNext()) {
			zg005 = (Zg005Yygrzd) arg6.next();
			if (pclassId.equals(zg005.getPInfectCode())) {
				reList.add(zg005);
				nextList.remove(zg005);
			}
		}

		arg6 = reList.iterator();

		while (arg6.hasNext()) {
			zg005 = (Zg005Yygrzd) arg6.next();
			zg005.setState("closed");
			this.a(nextList, zg005, zg005.getId(), zg005Yygrzd.getInfectCode());
		}

		return reList;
	}

	private void a(List<Zg005Yygrzd> list, Zg005Yygrzd zg005Yygrzd, String pInfectCode, String infectCode) {
		ArrayList cldList = new ArrayList();
		zg005Yygrzd.setCldList(cldList);

		for (int zg005 = 0; zg005 < list.size(); ++zg005) {
			Zg005Yygrzd zg0051 = (Zg005Yygrzd) list.get(zg005);
			if (pInfectCode.equals(zg0051.getPInfectCode())) {
				cldList.add(zg0051);
			}
		}

		if (cldList.size() == 0) {
			zg005Yygrzd.setState("open");
		} else {
			Iterator arg8 = cldList.iterator();

			Zg005Yygrzd arg7;
			while (arg8.hasNext()) {
				arg7 = (Zg005Yygrzd) arg8.next();
				list.remove(arg7);
			}

			arg8 = cldList.iterator();

			while (arg8.hasNext()) {
				arg7 = (Zg005Yygrzd) arg8.next();
				if (StringUtils.isNotBlank(infectCode) && infectCode.equals(arg7.getInfectCode())) {
					zg005Yygrzd.setState("open");
				}

				arg7.setState("open");
				this.a(list, arg7, arg7.getInfectCode(), infectCode);
			}

		}
	}

	private List<Zg005Yygrzd> B(List<Zg005Yygrzd> zg005YygrzdList) {
		Iterator arg2 = zg005YygrzdList.iterator();

		while (arg2.hasNext()) {
			Zg005Yygrzd zg005Yygrzd = (Zg005Yygrzd) arg2.next();
			Zg005Yygrzd tempZg005Yygrzd = new Zg005Yygrzd();
			tempZg005Yygrzd.setPInfectCode(zg005Yygrzd.getInfectCode());
			List tmpZg005YygrzdList = this.qN.findZg005Yygrzd(tempZg005Yygrzd);
			zg005Yygrzd.setCldList(tmpZg005YygrzdList);
			zg005Yygrzd.setState("open");
			if (!tmpZg005YygrzdList.isEmpty()) {
				this.B(tmpZg005YygrzdList);
				zg005Yygrzd.setState("closed");
			}
		}

		return zg005YygrzdList;
	}

	public int findNumByInfectCode(String pInfectCode, String infectCode) {
		return this.qN.findNumByInfectCode(pInfectCode, infectCode);
	}
}