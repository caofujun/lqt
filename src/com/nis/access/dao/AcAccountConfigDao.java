package com.nis.access.dao;

import com.nis.access.entity.AcAccountConfig;
import org.apache.ibatis.annotations.Param;

public interface AcAccountConfigDao {
	void save(AcAccountConfig arg0);

	void delete(@Param("id") String arg0);

	void deleteByKey(@Param("userId") String arg0, @Param("configKey") String arg1);

	void update(AcAccountConfig arg0);

	AcAccountConfig get(@Param("id") String arg0);

	AcAccountConfig getByKey(@Param("userId") String arg0, @Param("configKey") String arg1);
}