package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg006ZdmxFaDao;
import com.nis.zg.entity.Zg006ZdmxFa;
import com.nis.zg.service.Zg006ZdmxFaService;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg006ZdmxFaServiceImpl implements Zg006ZdmxFaService {
	@Autowired
	private Zg006ZdmxFaDao yr;

	public void save(Zg006ZdmxFa zg006ZdmxFa) {
		this.yr.save(zg006ZdmxFa);
	}

	public void delete(String id) {
		this.yr.delete(id);
	}

	public void update(Zg006ZdmxFa zg006ZdmxFa) {
		this.yr.update(zg006ZdmxFa);
	}

	public Zg006ZdmxFa get(String id) {
		return this.yr.get(id);
	}

	public MyPage<Zg006ZdmxFa> a(Zg006ZdmxFa zg006ZdmxFa) {
		int total = this.yr.findZg006ZdmxFaCount(zg006ZdmxFa);
		List data = null;
		if (total > 0) {
			data = this.yr.findZg006ZdmxFa(zg006ZdmxFa);
		}

		return new MyPage(zg006ZdmxFa.getPage().intValue(), zg006ZdmxFa.getSize().intValue(), total, data);
	}

	public List<Zg006ZdmxFa> getAll() {
		return this.yr.getAll();
	}

	public void start(String id) {
		this.yr.start(id);
		this.yr.stop(id);
	}

	public void a(String id, Integer weight, String type, String userId) {
		String field = "";
		if ("bcyx".equals(type)) {
			field = "bcyx_weight";
		} else if ("jyxx".equals(type)) {
			field = "jyxx_weight";
		} else if ("xjpp".equals(type)) {
			field = "xjpp_weight";
		} else if ("kjyy".equals(type)) {
			field = "kjyy_weight";
		} else if ("tyxzb".equals(type)) {
			field = "tyxzb_weight";
		}

		if (StringUtils.isNotBlank(field)) {
			this.yr.adjustWeight(id, field, weight, userId, new Date());
		}

	}

	public void v(String id, String faDescribe, String userId) {
		this.yr.updDescribe(id, faDescribe, userId, new Date());
	}

	public Zg006ZdmxFa getState() {
		return this.yr.getState();
	}
}