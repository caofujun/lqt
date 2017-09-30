package com.nis.zg.dao;

import com.nis.zg.entity.Zg010Kjyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg010KjywDao {
	void save(Zg010Kjyw arg0);

	void delete(@Param("drugId") String arg0);

	void update(Zg010Kjyw arg0);

	Zg010Kjyw get(@Param("drugId") String arg0);

	List<Zg010Kjyw> findZg010Kjyw(Zg010Kjyw arg0);

	int findZg010KjywCount(Zg010Kjyw arg0);

	List<Zg010Kjyw> getAll();
}