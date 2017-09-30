package com.nis.organization.dao;

import com.nis.organization.entity.Unit;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnitDao {
	void save(Unit arg0);

	void delete(@Param("unitId") String arg0);

	void update(Unit arg0);

	Unit get(@Param("unitId") String arg0);

	List<Unit> findUnit(Unit arg0);

	int findUnitCount(Unit arg0);

	List<Unit> getAll();

	Unit findUnitByUnitName(@Param("hospName") String arg0);

	List<Unit> findLike(@Param("name") String arg0);

	void updateUnitState(@Param("unitId") String arg0, @Param("flag") String arg1);

	void updateAllTableUnitId1(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId2(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId3(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId4(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId5(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId6(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId7(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId8(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId9(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId10(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId11(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId12(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId13(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId14(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId15(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId16(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId17(@Param("unitId") String arg0, @Param("unitName") String arg1);

	void updateAllTableUnitId18(@Param("unitId") String arg0, @Param("unitName") String arg1);
}