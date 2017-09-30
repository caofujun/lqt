package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys013Serial;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys013SerialDao {
	void save(CtgSys013Serial arg0);

	void delete(@Param("idnumber") String arg0);

	void update(CtgSys013Serial arg0);

	CtgSys013Serial get(@Param("idnumber") String arg0);

	List<CtgSys013Serial> findCtgSys013Serial(CtgSys013Serial arg0);

	int findCtgSys013SerialCount(CtgSys013Serial arg0);

	List<CtgSys013Serial> getAll();

	CtgSys013Serial getAUnusedRecord();

	void updateUnusedRecord(@Param("serialNumber") String arg0, @Param("masterId") String arg1,
			@Param("isreuse") String arg2);

	String isSNUsed(@Param("year") String arg0);
}