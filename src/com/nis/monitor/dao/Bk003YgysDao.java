package com.nis.monitor.dao;

import com.nis.monitor.entity.Bk003Ygys;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bk003YgysDao {
	void save(Bk003Ygys arg0);

	void delete(@Param("id") String arg0);

	void update(Bk003Ygys arg0);

	Bk003Ygys get(@Param("id") String arg0);

	List<Bk003Ygys> findBk003Ygys(Bk003Ygys arg0);

	List<Bk003Ygys> findBk003YgysList(Bk003Ygys arg0);

	int findBk003YgysCount(Bk003Ygys arg0);

	List<Bk003Ygys> getAll();

	List<Bk003Ygys> findSusceptFactors(Bk003Ygys arg0);

	Bk003Ygys findByRefidAndFactorId(Bk003Ygys arg0);

	void delSusceptFactors(Bk003Ygys arg0);

	void delByRefid(@Param("refid") String arg0);
}