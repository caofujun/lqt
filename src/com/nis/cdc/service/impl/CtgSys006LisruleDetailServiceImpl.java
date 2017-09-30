package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys006LisruleDetailDao;
import com.nis.cdc.entity.CtgSys006LisruleDetail;
import com.nis.cdc.service.CtgSys006LisruleDetailService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys006LisruleDetailServiceImpl implements CtgSys006LisruleDetailService {
	@Autowired
	private CtgSys006LisruleDetailDao eh;

	public void save(CtgSys006LisruleDetail crbYjLisruleDetail) {
		this.eh.save(crbYjLisruleDetail);
	}

	public void delete(Long orderno) {
		this.eh.delete(orderno);
	}

	public void update(CtgSys006LisruleDetail crbYjLisruleDetail) {
		this.eh.update(crbYjLisruleDetail);
	}

	public List<CtgSys006LisruleDetail> get(Long orderno) {
		return this.eh.get(orderno);
	}

	public MyPage<CtgSys006LisruleDetail> a(CtgSys006LisruleDetail crbYjLisruleDetail) {
		int total = this.eh.findCrbYjLisruleDetailCount(crbYjLisruleDetail);
		List data = null;
		if (total > 0) {
			data = this.eh.findCrbYjLisruleDetail(crbYjLisruleDetail);
		}

		return new MyPage(crbYjLisruleDetail.getPage().intValue(), crbYjLisruleDetail.getSize().intValue(), total,
				data);
	}

	public List<CtgSys006LisruleDetail> getAll() {
		return this.eh.getAll();
	}

	public List<CtgSys006LisruleDetail> getByOrderNo(String ons) {
		return this.eh.getByOrderNo(ons);
	}
}