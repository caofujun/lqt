package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.comm.utils.z;
import com.nis.dict.dao.MonitorOrderDao;
import com.nis.dict.entity.MonitorOrder;
import com.nis.dict.service.MonitorOrderService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorOrderServiceImpl implements MonitorOrderService {
	@Autowired
	private MonitorOrderDao qH;

	public void save(MonitorOrder monitorOrder) {
		monitorOrder.setOrderCode(z.a(bg.mK));
		monitorOrder.setUpdTime(f.getCurDate());
		if (StringUtils.isBlank(monitorOrder.getSpCode())) {
			monitorOrder.setSpCode(t.aE(monitorOrder.getOrderName()));
		}

		this.qH.save(monitorOrder);
	}

	public void delete(String orderCode) {
		this.qH.delete(orderCode);
	}

	public void update(MonitorOrder monitorOrder) {
		monitorOrder.setUpdTime(f.getCurDate());
		this.qH.update(monitorOrder);
	}

	public MonitorOrder get(String orderCode) {
		return this.qH.get(orderCode);
	}

	public MyPage<MonitorOrder> a(MonitorOrder monitorOrder) {
		int total = this.qH.findMonitorOrderCount(monitorOrder);
		List data = null;
		if (total > 0) {
			data = this.qH.findMonitorOrder(monitorOrder);
		}

		return new MyPage(monitorOrder.getPage().intValue(), monitorOrder.getSize().intValue(), total, data);
	}

	public List<MonitorOrder> getAll() {
		return this.qH.getAll();
	}
}