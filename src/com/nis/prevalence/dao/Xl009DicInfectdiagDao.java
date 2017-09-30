package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl009DicInfectdiag;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl009DicInfectdiagDao {
	void save(Xl009DicInfectdiag arg0);

	void delete(@Param("indiagid") String arg0);

	void update(Xl009DicInfectdiag arg0);

	Xl009DicInfectdiag get(@Param("indiagid") String arg0);

	List<Xl009DicInfectdiag> findXl009DicInfectdiag(Xl009DicInfectdiag arg0);

	int findXl009DicInfectdiagCount(Xl009DicInfectdiag arg0);

	List<Xl009DicInfectdiag> getAll();

	List<Xl009DicInfectdiag> queryTree();
}