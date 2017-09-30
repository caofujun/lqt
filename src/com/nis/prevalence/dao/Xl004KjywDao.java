package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl004Kjyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl004KjywDao {
	void save(Xl004Kjyw arg0);

	void delete(@Param("yjywid") String arg0);

	void update(Xl004Kjyw arg0);

	Xl004Kjyw get(@Param("yjywid") String arg0);

	List<Xl004Kjyw> findXl004Kjyw(Xl004Kjyw arg0);

	int findXl004KjywCount(Xl004Kjyw arg0);

	List<Xl004Kjyw> getAll();

	void delXl004Kjyw(@Param("yjywidNotIn") List<String> arg0, @Param("brid") String arg1);

	void deleteByBrid(@Param("brid") String arg0);
}