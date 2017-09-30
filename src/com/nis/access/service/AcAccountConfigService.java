package com.nis.access.service;

import com.nis.access.entity.AcAccountConfig;
import org.springframework.stereotype.Component;

@Component
public interface AcAccountConfigService {
	void save(AcAccountConfig arg0);

	void delete(String arg0);

	void deleteByKey(String arg0, String arg1);

	void update(AcAccountConfig arg0);

	AcAccountConfig get(String arg0);

	AcAccountConfig getByKey(String arg0, String arg1);
}