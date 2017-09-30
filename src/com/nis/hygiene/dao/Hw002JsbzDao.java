package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw002Jsbz;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw002JsbzDao {
	void save(Hw002Jsbz arg0);

	void delete(@Param("itemId") String arg0);

	void update(Hw002Jsbz arg0);

	Hw002Jsbz get(@Param("itemId") String arg0);

	List<Hw002Jsbz> findHw002Jsbz(Hw002Jsbz arg0);

	int findHw002JsbzCount(Hw002Jsbz arg0);

	List<Hw002Jsbz> getAll();

	List<Hw002Jsbz> findListByClassId(@Param("classId") String arg0);

	List<Hw002Jsbz> findList(Hw002Jsbz arg0);

	Hw002Jsbz getHw002Jsbz(@Param("itemId") String arg0);

	String findMaxItemId();
}