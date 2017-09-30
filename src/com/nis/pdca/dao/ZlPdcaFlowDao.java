package com.nis.pdca.dao;

import com.nis.pdca.entity.ZlPdcaFlow;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZlPdcaFlowDao {
	void save(ZlPdcaFlow arg0);

	void delete(@Param("fuid") String arg0);

	void update(ZlPdcaFlow arg0);

	ZlPdcaFlow get(@Param("fuid") String arg0);

	List<ZlPdcaFlow> findZlPdcaFlow(ZlPdcaFlow arg0);

	int findZlPdcaFlowCount(ZlPdcaFlow arg0);

	List<ZlPdcaFlow> getAll(ZlPdcaFlow arg0);

	List<ZlPdcaFlow> findZlPdcaFlowList(ZlPdcaFlow arg0);
}