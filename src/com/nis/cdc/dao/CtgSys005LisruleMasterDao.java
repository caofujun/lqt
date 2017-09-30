package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys005LisruleMaster;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys005LisruleMasterDao {
	void save(CtgSys005LisruleMaster arg0);

	void delete(@Param("orderno") Long arg0);

	void update(CtgSys005LisruleMaster arg0);

	CtgSys005LisruleMaster get(@Param("orderno") Long arg0);

	List<CtgSys005LisruleMaster> findCrbYjLisruleMaster(CtgSys005LisruleMaster arg0);

	int findCrbYjLisruleMasterCount(CtgSys005LisruleMaster arg0);

	List<CtgSys005LisruleMaster> getAll();
}