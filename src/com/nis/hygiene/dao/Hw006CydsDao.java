package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw006Cyds;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw006CydsDao {
	void save(Hw006Cyds arg0);

	void delete(@Param("posId") String arg0);

	void update(Hw006Cyds arg0);

	Hw006Cyds get(@Param("posId") String arg0);

	List<Hw006Cyds> findHw006Cyds(Hw006Cyds arg0);

	int findHw006CydsCount(Hw006Cyds arg0);

	List<Hw006Cyds> getAll();

	List<Hw006Cyds> queryList(Hw006Cyds arg0);

	List<Hw006Cyds> findList(Hw006Cyds arg0);

	String findMaxPosId();

	void updFlag(Hw006Cyds arg0);
}