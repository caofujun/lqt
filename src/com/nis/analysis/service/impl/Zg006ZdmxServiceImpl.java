package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Zg006ZdmxDao;
import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.analysis.entity.Zg006ZdmxEn;
import com.nis.analysis.service.Zg006ZdmxEnService;
import com.nis.analysis.service.Zg006ZdmxService;
import com.nis.comm.entity.MyPage;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg006ZdmxServiceImpl implements Zg006ZdmxService {
	private static final Logger c = Logger.getLogger(Zg006ZdmxServiceImpl.class);
	@Autowired
	private Zg006ZdmxDao x;
	@Autowired
	private Zg006ZdmxEnService cD;

	public void save(Zg006Zdmx zg006Zdmx) {
		this.x.save(zg006Zdmx);
	}

	public void delete(String mxId) {
		this.x.delete(mxId);
	}

	public void update(Zg006Zdmx zg006Zdmx) {
		this.x.update(zg006Zdmx);
	}

	public Zg006Zdmx get(String mxId) {
		return this.x.get(mxId);
	}

	public MyPage<Zg006Zdmx> a(Zg006Zdmx zg006Zdmx) {
		int total = this.x.findZg006ZdmxCount(zg006Zdmx);
		List data = null;
		if (total > 0) {
			data = this.x.findZg006Zdmx(zg006Zdmx);
		}

		return new MyPage(zg006Zdmx.getPage().intValue(), zg006Zdmx.getSize().intValue(), total, data);
	}

	public List<Zg006Zdmx> getAll() {
		return this.x.getAll();
	}

	public boolean g() {
		try {
			List e = this.cD.getAll();
			if (e != null) {
				Zg006Zdmx zg006Zdmx = null;
				Iterator arg3 = e.iterator();

				while (arg3.hasNext()) {
					Zg006ZdmxEn zdmx = (Zg006ZdmxEn) arg3.next();
					zg006Zdmx = new Zg006Zdmx();
					this.delete(zdmx.getMxId());
					BeanUtils.copyProperties(zdmx, zg006Zdmx);
					zg006Zdmx.encode();
					this.save(zg006Zdmx);
				}
			}

			return true;
		} catch (Exception arg4) {
			c.error("处理异常!", arg4);
			return false;
		}
	}

	public Zg006Zdmx e(String infectCode, String pNodeId) {
		return this.x.findByInfectCodeAndNodeId(infectCode, pNodeId);
	}

	public List<Zg006Zdmx> findbyElementId(String elementId) {
		return this.x.findbyElementId(elementId);
	}

	public List<Zg006Zdmx> findMustList() {
		return this.x.findMustList();
	}

	public List<Zg006Zdmx> findNeedCountList() {
		return this.x.findNeedCountList();
	}
}