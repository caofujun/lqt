package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl019Xjnyqk;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl019XjnyqkDao {
	void save(Xl019Xjnyqk arg0);

	void delete(@Param("id") String arg0);

	void update(Xl019Xjnyqk arg0);

	Xl019Xjnyqk get(@Param("id") String arg0);

	List<Xl019Xjnyqk> findXl019Xjnyqk(Xl019Xjnyqk arg0);

	int findXl019XjnyqkCount(Xl019Xjnyqk arg0);

	List<Xl019Xjnyqk> getAll();
}