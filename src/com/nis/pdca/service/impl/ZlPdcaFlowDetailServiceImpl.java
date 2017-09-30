package com.nis.pdca.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.pdca.dao.ZlPdcaFlowDetailDao;
import com.nis.pdca.entity.ZlPdcaFlowDetail;
import com.nis.pdca.service.ZlPdcaFlowDetailService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZlPdcaFlowDetailServiceImpl implements ZlPdcaFlowDetailService {
	@Autowired
	private ZlPdcaFlowDetailDao wC;
	@Autowired
	private SysDictService p;

	public void save(ZlPdcaFlowDetail zlPdcaFlowDetail) {
		zlPdcaFlowDetail.setFdSubid(z.a(bg.nr));
		this.wC.save(zlPdcaFlowDetail);
	}

	public void delete(String fdSubid) {
		this.wC.delete(fdSubid);
	}

	public void update(ZlPdcaFlowDetail zlPdcaFlowDetail) {
		this.wC.update(zlPdcaFlowDetail);
	}

	public ZlPdcaFlowDetail get(String fdSubid) {
		return this.wC.get(fdSubid);
	}

	public MyPage<ZlPdcaFlowDetail> a(ZlPdcaFlowDetail zlPdcaFlowDetail) {
		int total = this.wC.findZlPdcaFlowDetailCount(zlPdcaFlowDetail);
		List data = null;
		if (total > 0) {
			data = this.wC.findZlPdcaFlowDetail(zlPdcaFlowDetail);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				ZlPdcaFlowDetail pdcaFlowDetail = (ZlPdcaFlowDetail) arg4.next();
				if (pdcaFlowDetail.getSoccr() != null) {
					pdcaFlowDetail
							.setSoccrName(this.p.k("pdca_scope", pdcaFlowDetail.getSoccr().toString(), (String) null));
				}

				if (pdcaFlowDetail.getCdr() != null) {
					pdcaFlowDetail
							.setCdrName(this.p.k("pdca_scope", pdcaFlowDetail.getCdr().toString(), (String) null));
				}
			}
		}

		return new MyPage(zlPdcaFlowDetail.getPage().intValue(), zlPdcaFlowDetail.getSize().intValue(), total, data);
	}

	public List<ZlPdcaFlowDetail> findZlPdcaFlowDetailList(ZlPdcaFlowDetail zlPdcaFlowDetail) {
		return this.wC.findZlPdcaFlowDetailList(zlPdcaFlowDetail);
	}

	public List<ZlPdcaFlowDetail> getAll() {
		return this.wC.getAll();
	}

	public List<ZlPdcaFlowDetail> getByFuid(String fuid) {
		List flowDetailList = this.wC.getByFuid(fuid);
		Iterator arg3 = flowDetailList.iterator();

		while (arg3.hasNext()) {
			ZlPdcaFlowDetail flowDetail = (ZlPdcaFlowDetail) arg3.next();
			if (ab.isEmpty(flowDetail.getProcessContent())) {
				flowDetail.setProcessContent("");
			}
		}

		return flowDetailList;
	}
}