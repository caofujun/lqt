package com.nis.monitor.dao;

import com.nis.monitor.entity.Bk004Sjbb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bk004SjbbDao {
	void save(Bk004Sjbb arg0);

	void delete(Bk004Sjbb arg0);

	void update(Bk004Sjbb arg0);

	Bk004Sjbb get(Bk004Sjbb arg0);

	List<Bk004Sjbb> findBk004Sjbb(Bk004Sjbb arg0);

	int findBk004SjbbCount(Bk004Sjbb arg0);

	List<Bk004Sjbb> getAll();

	int findNumBytestNo(Bk004Sjbb arg0);

	List<Bk004Sjbb> findBk004ByRefid(@Param("refid") String arg0);

	List<Bk004Sjbb> findMultiDrugResis(@Param("relid") String arg0);

	List<Bk004Sjbb> findPathogenDetection(Bk004Sjbb arg0);

	int findPathogenDetectionCount(Bk004Sjbb arg0);

	void delByRefid(@Param("refid") String arg0);

	Integer getInfectTypeByTestNo(@Param("testNo") String arg0);
}