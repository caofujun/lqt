package com.nis.zg.dao;

import com.nis.zg.entity.Zg021Ygys;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg021YgysDao {
	void save(Zg021Ygys arg0);

	void delete(@Param("factorId") String arg0);

	void update(Zg021Ygys arg0);

	Zg021Ygys get(@Param("factorId") String arg0);

	List<Zg021Ygys> findZg021Ygys(Zg021Ygys arg0);

	int findZg021YgysCount(Zg021Ygys arg0);

	List<Zg021Ygys> getAll();
}