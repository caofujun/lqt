package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl003Byt;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl003BytDao {
	void save(Xl003Byt arg0);

	void delete(@Param("bytid") String arg0);

	void update(Xl003Byt arg0);

	Xl003Byt get(@Param("bytid") String arg0);

	List<Xl003Byt> findXl003Byt(Xl003Byt arg0);

	int findXl003BytCount(Xl003Byt arg0);

	List<Xl003Byt> getAll();

	void delXl003Byt(@Param("bytidNotIn") List<String> arg0, @Param("brid") String arg1);

	void deleteByBrid(@Param("brid") String arg0);
}