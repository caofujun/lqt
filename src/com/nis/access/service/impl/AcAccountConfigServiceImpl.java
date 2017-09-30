package com.nis.access.service.impl;

import com.nis.access.dao.AcAccountConfigDao;
import com.nis.access.entity.AcAccountConfig;
import com.nis.access.service.AcAccountConfigService;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcAccountConfigServiceImpl implements AcAccountConfigService {
	@Autowired
	private AcAccountConfigDao n;

	public void save(AcAccountConfig acAccountConfig) {
		acAccountConfig.setId(z.a(bg.mC));
		acAccountConfig.setUpdTime(f.getCurDate());
		this.n.save(acAccountConfig);
	}

	public void delete(String id) {
		this.n.delete(id);
	}

	public void update(AcAccountConfig acAccountConfig) {
		this.n.update(acAccountConfig);
	}

	public AcAccountConfig get(String id) {
		return this.n.get(id);
	}

	public void deleteByKey(String userId, String configKey) {
		this.n.deleteByKey(userId, configKey);
	}

	public AcAccountConfig getByKey(String userId, String configKey) {
		return this.n.getByKey(userId, configKey);
	}
}