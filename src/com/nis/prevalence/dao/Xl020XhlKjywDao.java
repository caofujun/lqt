package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl020XhlKjyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl020XhlKjywDao {
	void save(Xl020XhlKjyw arg0);

	void delete(@Param("drugId") String arg0);

	void update(Xl020XhlKjyw arg0);

	Xl020XhlKjyw get(@Param("drugId") String arg0);

	List<Xl020XhlKjyw> findXl020XhlKjyw(Xl020XhlKjyw arg0);

	int findXl020XhlKjywCount(Xl020XhlKjyw arg0);

	List<Xl020XhlKjyw> getAll();
}