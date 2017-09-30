package com.nis.monitor.dao;

import com.nis.monitor.entity.Gr012Ymsy;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr012YmsyDao {
	void save(Gr012Ymsy arg0);

	void delete(@Param("id") Long arg0);

	void deleteByRelid(@Param("relid") String arg0, @Param("gr12Relid") String arg1);

	void update(Gr012Ymsy arg0);

	Gr012Ymsy get(@Param("id") Long arg0);

	List<Gr012Ymsy> findGr012Ymsy(Gr012Ymsy arg0);

	int findGr012YmsyCount(Gr012Ymsy arg0);

	List<Gr012Ymsy> getAll();
}