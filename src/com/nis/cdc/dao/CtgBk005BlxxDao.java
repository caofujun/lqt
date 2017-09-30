package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk005Blxx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk005BlxxDao {
	void save(CtgBk005Blxx arg0);

	void delete(@Param("subid") String arg0);

	void deleteByMasterid(@Param("masterid") String arg0);

	void update(CtgBk005Blxx arg0);

	CtgBk005Blxx get(@Param("subid") String arg0);

	List<CtgBk005Blxx> findCtgBk005Blxx(CtgBk005Blxx arg0);

	int findCtgBk005BlxxCount(CtgBk005Blxx arg0);

	List<CtgBk005Blxx> getAll();

	List<CtgBk005Blxx> getByMastertid(@Param("masterid") String arg0);
}