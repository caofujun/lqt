package com.nis.access.service.impl;

import com.nis.access.dao.SysActionDao;
import com.nis.access.entity.SysAction;
import com.nis.access.service.SysActionService;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysActionServiceImpl implements SysActionService {
	@Autowired
	private SysActionDao w;

	public void save(SysAction sysAction) {
		sysAction.setId(z.a(bg.mx));
		this.w.save(sysAction);
	}

	public void delete(String id) {
		this.w.delete(id);
	}

	public void update(SysAction sysAction) {
		this.w.update(sysAction);
	}

	public SysAction get(String id) {
		SysAction sysAction = this.w.get(id);
		return sysAction;
	}

	public SysAction findSysAction(SysAction sysAction) {
		SysAction sys = this.w.findSysAction(sysAction);
		return sys;
	}

	public List<SysAction> findSysActionList(SysAction sysAction) {
		return this.w.findSysActionList(sysAction);
	}

	public SysAction findByParamCode(String funcType, Long sourceHospital, String sourceDepno, String sourceUser) {
		if (ab.isEmpty(funcType)) {
			funcType = "";
		}

		if (ab.isEmpty(sourceDepno)) {
			sourceDepno = "";
		}

		if (ab.isEmpty(sourceUser)) {
			sourceUser = "";
		}

		if (sourceHospital == null) {
			sourceHospital = Long.valueOf(0L);
		}

		return this.w.findByParamCode(funcType, sourceHospital, sourceDepno, sourceUser);
	}
}