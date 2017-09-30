package com.nis.monitor.dao;

import com.nis.monitor.entity.Gr016BkKjyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr016BkKjywDao {
	void save(Gr016BkKjyw arg0);

	void delete(@Param("relid") String arg0);

	void update(Gr016BkKjyw arg0);

	Gr016BkKjyw get(@Param("relid") String arg0);

	List<Gr016BkKjyw> findGr016BkKjyw(Gr016BkKjyw arg0);

	int findGr016BkKjywCount(Gr016BkKjyw arg0);

	List<Gr016BkKjyw> getAll();

	List<Gr016BkKjyw> query(Gr016BkKjyw arg0);

	void deleteByRefid(String arg0);
}