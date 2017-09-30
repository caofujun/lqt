package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn003Kjyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn003KjywDao {
	void save(Xn003Kjyw arg0);

	void delete(@Param("drugId") String arg0);

	void update(Xn003Kjyw arg0);

	Xn003Kjyw get(@Param("drugId") String arg0);

	List<Xn003Kjyw> findXn003Kjyw(Xn003Kjyw arg0);

	int findXn003KjywCount(Xn003Kjyw arg0);

	List<Xn003Kjyw> getAll();

	List<Xn003Kjyw> findXn003KjywByByt(Xn003Kjyw arg0);
}