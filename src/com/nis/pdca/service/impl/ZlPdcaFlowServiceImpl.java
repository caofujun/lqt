package com.nis.pdca.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.pdca.dao.ZlPdcaFlowDao;
import com.nis.pdca.entity.ZlPdcaFlow;
import com.nis.pdca.entity.ZlPdcaFlowDetail;
import com.nis.pdca.entity.ZlPdcaPlans;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import com.nis.pdca.service.ZlPdcaFlowDetailService;
import com.nis.pdca.service.ZlPdcaFlowService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZlPdcaFlowServiceImpl implements ZlPdcaFlowService {
	@Autowired
	private ZlPdcaFlowDao wD;
	@Autowired
	private SysDictService p;
	@Autowired
	private ZlPdcaFlowDetailService wA;

	public void save(ZlPdcaFlow zlPdcaFlow) {
		zlPdcaFlow.setFuid(z.a(bg.ns));
		this.wD.save(zlPdcaFlow);
	}

	public void delete(String fuid) {
		this.wD.delete(fuid);
	}

	public void update(ZlPdcaFlow zlPdcaFlow) {
		this.wD.update(zlPdcaFlow);
	}

	public ZlPdcaFlow get(String fuid) {
		return this.wD.get(fuid);
	}

	public MyPage<ZlPdcaFlow> a(ZlPdcaFlow zlPdcaFlow) {
		int total = this.wD.findZlPdcaFlowCount(zlPdcaFlow);
		List data = null;
		if (total > 0) {
			data = this.wD.findZlPdcaFlow(zlPdcaFlow);
			data = this.a(data);
		}

		return new MyPage(zlPdcaFlow.getPage().intValue(), zlPdcaFlow.getSize().intValue(), total, data);
	}

	public List<ZlPdcaFlow> findZlPdcaFlowList(ZlPdcaFlow zlPdcaFlow) {
		return this.a(this.wD.findZlPdcaFlowList(zlPdcaFlow));
	}

	private List<ZlPdcaFlow> a(List<ZlPdcaFlow> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			ZlPdcaFlow zlPdcaFlow = (ZlPdcaFlow) arg2.next();
			if (zlPdcaFlow.getStatus() != null) {
				zlPdcaFlow.setStatusName(this.p.k("pdca_status", zlPdcaFlow.getStatus().toString(), (String) null));
			}
		}

		return data;
	}

	public List<ZlPdcaFlow> getAll(ZlPdcaFlow zlPdcaFlow) {
		return this.wD.getAll(zlPdcaFlow);
	}

	public void a(ZlPdcaPlans zlPdcaPlans) {
		ZlPdcaFlow zlPdcaFlow = new ZlPdcaFlow();
		zlPdcaFlow.setFlowName(zlPdcaPlans.getPlanName());
		zlPdcaFlow.setCreateId(zlPdcaPlans.getCreaterId());
		zlPdcaFlow.setStatus(Long.valueOf(1L));
		zlPdcaFlow.setFlowCreatetime(new Date());
		this.save(zlPdcaFlow);
		List zlPdcaPlansDetailList = zlPdcaPlans.getZlPdcaPlansDetailList();
		if (zlPdcaPlansDetailList != null && zlPdcaPlansDetailList.size() > 0) {
			Iterator arg4 = zlPdcaPlansDetailList.iterator();

			while (arg4.hasNext()) {
				ZlPdcaPlansDetail zlPdcaPlansDetail = (ZlPdcaPlansDetail) arg4.next();
				ZlPdcaFlowDetail zlPdcaFlowDetail = new ZlPdcaFlowDetail();
				zlPdcaFlowDetail.setProcessName(zlPdcaPlansDetail.getStepName());
				zlPdcaFlowDetail.setProcessContent(zlPdcaPlansDetail.getStepContent());
				zlPdcaFlowDetail.setFuid(zlPdcaFlow.getFuid());
				zlPdcaFlowDetail.setSoccr(Long.valueOf(1L));
				this.wA.save(zlPdcaFlowDetail);
			}
		}

	}
}