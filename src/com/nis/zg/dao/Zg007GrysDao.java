package com.nis.zg.dao;

import com.nis.zg.entity.Zg007Grys;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg007GrysDao {
	void save(Zg007Grys arg0);

	void delete(@Param("elementId") String arg0);

	void update(Zg007Grys arg0);

	Zg007Grys get(@Param("elementId") String arg0);

	List<Zg007Grys> findZg007Grys(Zg007Grys arg0);

	int findZg007GrysCount(Zg007Grys arg0);

	List<Zg007Grys> getAll();

	List<Zg007Grys> findByElementIdLike(@Param("elementId") String arg0);
}