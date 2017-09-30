package com.nis.monitor.dao;

import com.nis.monitor.entity.Xn020Gadc;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn020GadcDao {
	void save(Xn020Gadc arg0);

	void delete(@Param("id") String arg0);

	void update(Xn020Gadc arg0);

	Xn020Gadc get(@Param("id") String arg0);

	List<Xn020Gadc> findXn020Gadc(Xn020Gadc arg0);

	int findXn020GadcCount(Xn020Gadc arg0);

	List<Xn020Gadc> getAll();
}