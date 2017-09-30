package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk005Cyxx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk005CyxxDao {
	void save(CtgBk005Cyxx arg0);

	void delete(@Param("subid") String arg0);

	void deleteByMasterid(@Param("masterid") String arg0);

	void update(CtgBk005Cyxx arg0);

	CtgBk005Cyxx get(@Param("subid") String arg0);

	List<CtgBk005Cyxx> findCtgBk005Cyxx(CtgBk005Cyxx arg0);

	int findCtgBk005CyxxCount(CtgBk005Cyxx arg0);

	List<CtgBk005Cyxx> getAll();

	List<CtgBk005Cyxx> getByMastertid(@Param("masterid") String arg0);
}