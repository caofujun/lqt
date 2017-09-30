package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw010Zzry;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw010ZzryDao {
	void save(Hw010Zzry arg0);

	void delete(@Param("employeeId") String arg0);

	void update(Hw010Zzry arg0);

	Hw010Zzry get(@Param("employeeId") String arg0);

	List<Hw010Zzry> findHw010Zzry(Hw010Zzry arg0);

	int findHw010ZzryCount(Hw010Zzry arg0);

	List<Hw010Zzry> getAll();

	List<Hw010Zzry> queryList(Hw010Zzry arg0);

	List<Hw010Zzry> findAccreditList(Hw010Zzry arg0);

	int findAccreditListCount(Hw010Zzry arg0);
}