package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys006LisruleDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys006LisruleDetailDao {
	void save(CtgSys006LisruleDetail arg0);

	void delete(@Param("orderno") Long arg0);

	void update(CtgSys006LisruleDetail arg0);

	List<CtgSys006LisruleDetail> get(@Param("orderno") Long arg0);

	List<CtgSys006LisruleDetail> findCrbYjLisruleDetail(CtgSys006LisruleDetail arg0);

	int findCrbYjLisruleDetailCount(CtgSys006LisruleDetail arg0);

	List<CtgSys006LisruleDetail> getAll();

	List<CtgSys006LisruleDetail> getByOrderNo(@Param("ons") String arg0);
}