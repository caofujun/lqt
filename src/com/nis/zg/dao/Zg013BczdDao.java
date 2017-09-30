package com.nis.zg.dao;

import com.nis.zg.entity.Zg013Bczd;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg013BczdDao {
	void save(Zg013Bczd arg0);

	void delete(@Param("itemName") String arg0);

	void update(Zg013Bczd arg0);

	Zg013Bczd get(@Param("itemName") String arg0);

	List<Zg013Bczd> findZg013Bczd(Zg013Bczd arg0);

	int findZg013BczdCount(Zg013Bczd arg0);

	List<Zg013Bczd> getAll();
}