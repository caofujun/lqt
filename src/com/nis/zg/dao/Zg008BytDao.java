package com.nis.zg.dao;

import com.nis.zg.entity.Zg008Byt;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg008BytDao {
	void save(Zg008Byt arg0);

	void delete(@Param("pathogenId") String arg0);

	void update(Zg008Byt arg0);

	Zg008Byt get(@Param("pathogenId") String arg0);

	List<Zg008Byt> findZg008Byt(Zg008Byt arg0);

	int findZg008BytCount(Zg008Byt arg0);

	List<Zg008Byt> getAll();
}