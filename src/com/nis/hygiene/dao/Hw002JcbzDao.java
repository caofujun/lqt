package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw002Jcbz;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw002JcbzDao {
	void save(Hw002Jcbz arg0);

	void delete(@Param("itemId") String arg0);

	void update(Hw002Jcbz arg0);

	Hw002Jcbz get(@Param("itemId") String arg0);

	List<Hw002Jcbz> findHw002Jcbz(Hw002Jcbz arg0);

	int findHw002JcbzCount(Hw002Jcbz arg0);

	List<Hw002Jcbz> getAll();
}