package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl002Grxx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl002GrxxDao {
	void save(Xl002Grxx arg0);

	void delete(@Param("grid") String arg0);

	void update(Xl002Grxx arg0);

	void updateSpecified(@Param("xl002Grxx") Xl002Grxx arg0, @Param("updateAttrs") List<String> arg1);

	Xl002Grxx get(@Param("grid") String arg0);

	List<Xl002Grxx> findXl002Grxx(Xl002Grxx arg0);

	int findXl002GrxxCount(Xl002Grxx arg0);

	List<Xl002Grxx> getAll();

	List<Xl002Grxx> queryByBrid(@Param("brid") String arg0);

	void delXl002Grxx(@Param("gridNotIn") List<String> arg0, @Param("brid") String arg1);

	void deleteByBrid(@Param("brid") String arg0);
}