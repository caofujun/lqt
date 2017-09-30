package com.nis.pdca.dao;

import com.nis.pdca.entity.ZlPdcaPlans;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZlPdcaPlansDao {
	void save(ZlPdcaPlans arg0);

	void delete(@Param("puid") String arg0);

	void update(ZlPdcaPlans arg0);

	ZlPdcaPlans get(@Param("puid") String arg0);

	List<ZlPdcaPlans> findZlPdcaPlans(ZlPdcaPlans arg0);

	int findZlPdcaPlansCount(ZlPdcaPlans arg0);

	List<ZlPdcaPlans> getAll();

	List<ZlPdcaPlans> findByPzId(String arg0);
}