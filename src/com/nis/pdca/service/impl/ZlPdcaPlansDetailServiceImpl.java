package com.nis.pdca.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.pdca.dao.ZlPdcaPlansDetailDao;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import com.nis.pdca.service.ZlPdcaPlansDetailService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZlPdcaPlansDetailServiceImpl implements ZlPdcaPlansDetailService {
	@Autowired
	private ZlPdcaPlansDetailDao wE;
	@Autowired
	private SysDictService p;

	public void save(ZlPdcaPlansDetail zlPdcaPlansDetail) {
		zlPdcaPlansDetail.setPdSubid(z.a(bg.nt));
		this.wE.save(zlPdcaPlansDetail);
	}

	public void delete(String pdSubid) {
		this.wE.delete(pdSubid);
	}

	public void update(ZlPdcaPlansDetail zlPdcaPlansDetail) {
		this.wE.update(zlPdcaPlansDetail);
	}

	public ZlPdcaPlansDetail get(String pdSubid) {
		return this.wE.get(pdSubid);
	}

	public MyPage<ZlPdcaPlansDetail> a(ZlPdcaPlansDetail zlPdcaPlansDetail) {
		int total = this.wE.findZlPdcaPlansDetailCount(zlPdcaPlansDetail);
		List data = null;
		if (total > 0) {
			data = this.wE.findZlPdcaPlansDetail(zlPdcaPlansDetail);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				ZlPdcaPlansDetail plansDetail = (ZlPdcaPlansDetail) arg4.next();
				plansDetail.setStatusName(
						this.p.k("pdca_plans_status", plansDetail.getStatus().toString(), (String) null));
			}
		}

		return new MyPage(zlPdcaPlansDetail.getPage().intValue(), zlPdcaPlansDetail.getSize().intValue(), total, data);
	}

	public List<ZlPdcaPlansDetail> getAll() {
		return this.wE.getAll();
	}

	public List<ZlPdcaPlansDetail> getByPuid(String puid) {
		return this.wE.getByPuid(puid);
	}

	public void deleteByPuid(String puid) {
		this.wE.deleteByPuid(puid);
	}
}