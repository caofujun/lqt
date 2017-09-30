package com.nis.pdca.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.pdca.dao.ZlPdcaPlansDao;
import com.nis.pdca.entity.ZlPdcaPlans;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import com.nis.pdca.service.ZlPdcaPlansDetailService;
import com.nis.pdca.service.ZlPdcaPlansService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZlPdcaPlansServiceImpl implements ZlPdcaPlansService {
	@Autowired
	private ZlPdcaPlansDao wF;
	@Autowired
	private ZlPdcaPlansDetailService wB;

	public void save(ZlPdcaPlans zlPdcaPlans) {
		zlPdcaPlans.setPuid(z.a(bg.nu));
		this.wF.save(zlPdcaPlans);
		List zlPdcaPlansDetailList = zlPdcaPlans.getZlPdcaPlansDetailList();
		if (zlPdcaPlansDetailList != null && zlPdcaPlansDetailList.size() > 0) {
			Iterator arg3 = zlPdcaPlansDetailList.iterator();

			while (arg3.hasNext()) {
				ZlPdcaPlansDetail zlPdcaPlansDetail = (ZlPdcaPlansDetail) arg3.next();
				zlPdcaPlansDetail.setPuid(zlPdcaPlans.getPuid());
				this.wB.save(zlPdcaPlansDetail);
			}
		}

	}

	public void delete(String puid) {
		this.wF.delete(puid);
		this.wB.deleteByPuid(puid);
	}

	public void update(ZlPdcaPlans zlPdcaPlans) {
		this.wF.update(zlPdcaPlans);
		this.wB.deleteByPuid(zlPdcaPlans.getPuid());
		List zlPdcaPlansDetailList = zlPdcaPlans.getZlPdcaPlansDetailList();
		if (zlPdcaPlansDetailList != null && zlPdcaPlansDetailList.size() > 0) {
			Iterator arg3 = zlPdcaPlansDetailList.iterator();

			while (arg3.hasNext()) {
				ZlPdcaPlansDetail zlPdcaPlansDetail = (ZlPdcaPlansDetail) arg3.next();
				zlPdcaPlansDetail.setPuid(zlPdcaPlans.getPuid());
				this.wB.save(zlPdcaPlansDetail);
			}
		}

	}

	public ZlPdcaPlans get(String puid) {
		return this.wF.get(puid);
	}

	public MyPage<ZlPdcaPlans> b(ZlPdcaPlans zlPdcaPlans) {
		int total = this.wF.findZlPdcaPlansCount(zlPdcaPlans);
		List data = null;
		if (total > 0) {
			data = this.wF.findZlPdcaPlans(zlPdcaPlans);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				ZlPdcaPlans plans = (ZlPdcaPlans) arg4.next();
				List plansList = this.wB.getByPuid(plans.getPuid());
				int wc = 0;
				Iterator arg8 = plansList.iterator();

				while (arg8.hasNext()) {
					ZlPdcaPlansDetail planDetail = (ZlPdcaPlansDetail) arg8.next();
					if (planDetail.getStatus() != null && planDetail.getStatus().longValue() - 1L == 0L) {
						++wc;
					}
				}

				plans.setWcd(wc + "/" + plansList.size());
			}
		}

		return new MyPage(zlPdcaPlans.getPage().intValue(), zlPdcaPlans.getSize().intValue(), total, data);
	}

	public List<ZlPdcaPlans> getAll() {
		return this.wF.getAll();
	}

	public List<ZlPdcaPlans> findByPzId(String pzId) {
		return this.wF.findByPzId(pzId);
	}
}