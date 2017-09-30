package com.nis.icu.service.impl;

import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.am;
import com.nis.icu.dao.Gm005XsrtzDao;
import com.nis.icu.entity.Gm004Jcmx;
import com.nis.icu.entity.Gm005Xsrtz;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.icu.service.Gm005XsrtzService;
import com.nis.patient.service.St001JbxxbService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Gm005XsrtzServiceImpl implements Gm005XsrtzService {
	@Autowired
	private Gm005XsrtzDao sq;
	@Autowired
	private Gm004JcmxService bP;
	@Autowired
	private St001JbxxbService dg;

	public void save(Gm005Xsrtz gm005Xsrtz) {
		this.sq.save(gm005Xsrtz);
	}

	public void update(Gm005Xsrtz gm005Xsrtz) {
		this.sq.update(gm005Xsrtz);
	}

	public Gm005Xsrtz get(String zyid) {
		return this.sq.get(zyid);
	}

	public MyPage<Gm005Xsrtz> a(Gm005Xsrtz gm005Xsrtz) {
		int total = this.sq.findGm005XsrtzCount(gm005Xsrtz);
		List data = null;
		if (total > 0) {
			data = this.sq.findGm005Xsrtz(gm005Xsrtz);
		}

		return new MyPage(gm005Xsrtz.getPage().intValue(), gm005Xsrtz.getSize().intValue(), total, data);
	}

	public List<Gm005Xsrtz> getAll() {
		return this.sq.getAll();
	}

	@Transactional(rollbackFor = {Exception.class})
	public void a(Gm005Xsrtz gm005Xsrtz, LoginUser user) {
		gm005Xsrtz.setWeight(Double.valueOf(Double.parseDouble(String.valueOf(gm005Xsrtz.getWeight().intValue()))));
		gm005Xsrtz.setNeonatebw(am.a(gm005Xsrtz.getWeight()).getValue());
		gm005Xsrtz.setIsnewborn(Integer.valueOf(1));
		Gm005Xsrtz gm005 = this.get(gm005Xsrtz.getZyid());
		if (gm005 == null) {
			gm005Xsrtz.setOperatoinBy(user.getUsername());
			gm005Xsrtz.setOperatorAt(new Date());
			this.save(gm005Xsrtz);
		} else {
			gm005.setNeonatebw(gm005Xsrtz.getNeonatebw());
			gm005.setWeight(gm005Xsrtz.getWeight());
			gm005.setOperatoinBy(user.getUnitName());
			gm005.setOperatorAt(new Date());
			this.update(gm005);
		}

		ArrayList updateAttrs = new ArrayList();
		updateAttrs.add("isnewborn");
		updateAttrs.add("neonatebw");
		Gm004Jcmx gm004Jcmx = new Gm004Jcmx();
		gm004Jcmx.setZyid(gm005Xsrtz.getZyid());
		gm004Jcmx.setIsnewborn(gm005Xsrtz.getIsnewborn());
		gm004Jcmx.setNeonatebw(gm005Xsrtz.getNeonatebw());
		this.bP.updateSpecified(gm004Jcmx, updateAttrs);
		this.dg.updWeightByZyid(gm005Xsrtz.getZyid(), String.valueOf(gm005Xsrtz.getWeight().intValue()));
	}
}