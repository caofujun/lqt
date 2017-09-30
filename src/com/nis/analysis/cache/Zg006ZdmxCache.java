package com.nis.analysis.cache;

import com.nis.analysis.dao.Zg006ZdmxDao;
import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.d;
import com.nis.zg.entity.Zg007Grys;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg006ZdmxCache {
	@Autowired
	private Zg006ZdmxDao x;

	public void e(List<Zg006Zdmx> zdmxList) {
		Iterator arg2 = zdmxList.iterator();

		while (arg2.hasNext()) {
			Zg006Zdmx zdmx = (Zg006Zdmx) arg2.next();
			String key = zdmx.getInfectCode() + "_" + zdmx.getNodeId();
			d.set(key, zdmx);
		}

	}

	public void a(List<Zg006Zdmx> zdmxList, List<Zg007Grys> grysList) {
		Iterator arg3 = grysList.iterator();

		while (arg3.hasNext()) {
			Zg007Grys grys = (Zg007Grys) arg3.next();
			String key = EncryptUtils.ad(grys.getElementId());
			ArrayList zg006List = new ArrayList();
			Iterator arg7 = zdmxList.iterator();

			while (arg7.hasNext()) {
				Zg006Zdmx zdmx = (Zg006Zdmx) arg7.next();
				if (key.equals(zdmx.getElementId())) {
					zg006List.add(zdmx);
				}
			}

			d.set(key, zg006List);
		}

	}

	public Zg006Zdmx j(String key) {
		Zg006Zdmx zdmx = (Zg006Zdmx) d.get(key);
		if (zdmx == null) {
			String[] keys = key.split("_");
			zdmx = this.x.findByInfectCodeAndNodeId(EncryptUtils.ad(keys[0]), EncryptUtils.ad(keys[1]));
			zdmx.decode();
		}

		return zdmx;
	}

	public List<Zg006Zdmx> k(String key) {
		List zdmxList = (List) d.get(key);
		if (zdmxList == null) {
			zdmxList = this.x.findbyElementId(EncryptUtils.ad(key));
			Iterator arg3 = zdmxList.iterator();

			while (arg3.hasNext()) {
				Zg006Zdmx zdmx = (Zg006Zdmx) arg3.next();
				zdmx.decode();
			}
		}

		return zdmxList;
	}
}