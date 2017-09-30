package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw003Cycs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw003CycsDao {
	void save(Hw003Cycs arg0);

	void delete(@Param("placeId") String arg0);

	void update(Hw003Cycs arg0);

	Hw003Cycs get(@Param("placeId") String arg0);

	Hw003Cycs isExist(@Param("placeId") String arg0);

	List<Hw003Cycs> findHw003Cycs(Hw003Cycs arg0);

	int findHw003CycsCount(Hw003Cycs arg0);

	List<Hw003Cycs> getAll();

	List<Hw003Cycs> queryList(Hw003Cycs arg0);

	List<Hw003Cycs> findList(Hw003Cycs arg0);

	String findMaxPlaceId();

	void updFlag(Hw003Cycs arg0);
}