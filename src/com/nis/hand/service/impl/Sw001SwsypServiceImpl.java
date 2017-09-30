package com.nis.hand.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.hand.dao.Sw001SwsypDao;
import com.nis.hand.entity.Sw001Swsyp;
import com.nis.hand.service.Sw001SwsypService;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Sw001SwsypServiceImpl implements Sw001SwsypService {
	@Autowired
	private Sw001SwsypDao rd;

	public void save(Sw001Swsyp sw001Swsyp) {
		sw001Swsyp.setId(z.a(bg.nN));
		sw001Swsyp.setAddDate(new Date());
		sw001Swsyp.setThisMonthUsed(Integer.valueOf(sw001Swsyp.getLastMonthRemain().intValue()
				+ sw001Swsyp.getThisMonthGet().intValue() * sw001Swsyp.getSpecification().intValue()
				- sw001Swsyp.getThisMonthInventory().intValue()));
		this.rd.save(sw001Swsyp);
	}

	public void delete(String id) {
		this.rd.delete(id);
	}

	public void update(Sw001Swsyp sw001Swsyp) {
		sw001Swsyp.setThisMonthUsed(Integer.valueOf(sw001Swsyp.getLastMonthRemain().intValue()
				+ sw001Swsyp.getThisMonthGet().intValue() * sw001Swsyp.getSpecification().intValue()
				- sw001Swsyp.getThisMonthInventory().intValue()));
		this.rd.update(sw001Swsyp);
	}

	public void updateSpecified(Sw001Swsyp sw001Swsyp, List<String> updateAttrs) {
		sw001Swsyp.setThisMonthUsed(Integer.valueOf(sw001Swsyp.getLastMonthRemain().intValue()
				+ sw001Swsyp.getThisMonthGet().intValue() * sw001Swsyp.getSpecification().intValue()
				- sw001Swsyp.getThisMonthInventory().intValue()));
		if (updateAttrs.size() > 0) {
			this.rd.updateSpecified(sw001Swsyp, updateAttrs);
		}

	}

	public Sw001Swsyp get(String id) {
		return this.rd.get(id);
	}

	public MyPage<Sw001Swsyp> a(Sw001Swsyp sw001Swsyp) {
		int total = this.rd.findSw001SwsypCount(sw001Swsyp);
		List data = null;
		if (total > 0) {
			data = this.rd.findSw001Swsyp(sw001Swsyp);
		}

		return new MyPage(sw001Swsyp.getPage().intValue(), sw001Swsyp.getSize().intValue(), total, data);
	}

	public List<Sw001Swsyp> getAll() {
		return this.rd.getAll();
	}

	@Transactional(rollbackFor = {Exception.class})
	public void b(Sw001Swsyp sw001Swsyp) {
		List sw001List = sw001Swsyp.getSw001List();
		Iterator arg3 = sw001List.iterator();

		while (arg3.hasNext()) {
			Sw001Swsyp sw001 = (Sw001Swsyp) arg3.next();
			if (sw001 != null && sw001.getSpecification() != null) {
				sw001.setReportDate(sw001Swsyp.getReportDate());
				sw001.setReportDeptId(sw001Swsyp.getReportDeptId());
				sw001.setReportDeptName(sw001Swsyp.getReportDeptName());
				sw001.setReportUserId(sw001Swsyp.getReportUserId());
				sw001.setReportUserName(sw001Swsyp.getReportUserName());
				this.save(sw001);
			}
		}

	}

	public void c(Sw001Swsyp sw001Swsyp) {
		String updateStr = "reportDate,reportUserName,reportUserId,specification,thisMonthUsed,thisMonthInventory,lastMonthRemain,thisMonthGet";
		List updateAttrs = Arrays.asList(updateStr.split(","));
		this.updateSpecified(sw001Swsyp, updateAttrs);
	}
}