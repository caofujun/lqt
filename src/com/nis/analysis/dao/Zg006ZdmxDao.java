package com.nis.analysis.dao;

import com.nis.analysis.entity.Zg006Zdmx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg006ZdmxDao {
	void save(Zg006Zdmx arg0);

	void delete(@Param("mxId") String arg0);

	void update(Zg006Zdmx arg0);

	Zg006Zdmx get(@Param("mxId") String arg0);

	List<Zg006Zdmx> findZg006Zdmx(Zg006Zdmx arg0);

	int findZg006ZdmxCount(Zg006Zdmx arg0);

	List<Zg006Zdmx> getAll();

	Zg006Zdmx findByInfectCodeAndNodeId(@Param("infectCode") String arg0, @Param("nodeId") String arg1);

	List<Zg006Zdmx> findbyElementId(@Param("elementId") String arg0);

	List<Zg006Zdmx> findMustByInfectCodeAndPNodeId(@Param("infectCode") String arg0, @Param("pNodeId") String arg1);

	List<Zg006Zdmx> findMustList();

	List<Zg006Zdmx> findNeedCountList();
}