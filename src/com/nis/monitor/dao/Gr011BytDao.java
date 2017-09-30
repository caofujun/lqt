package com.nis.monitor.dao;

import com.nis.monitor.entity.Gr011Byt;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr011BytDao {
	void save(Gr011Byt arg0);

	void delete(@Param("id") Long arg0);

	void deleteByRelid(@Param("relid") String arg0, @Param("gr11Relid") String arg1);

	void update(Gr011Byt arg0);

	Gr011Byt get(@Param("id") Long arg0);

	List<Gr011Byt> findGr011Byt(Gr011Byt arg0);

	int findGr011BytCount(Gr011Byt arg0);

	List<Gr011Byt> getAll();
}