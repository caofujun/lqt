package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn013Dbyw;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn013DbywDao {
	void save(Xn013Dbyw arg0);

	void delete(@Param("itemId") Long arg0);

	void update(Xn013Dbyw arg0);

	Xn013Dbyw get(@Param("itemId") Long arg0);

	List<Xn013Dbyw> findXn013Dbyw(Xn013Dbyw arg0);

	int findXn013DbywCount(Xn013Dbyw arg0);

	List<Xn013Dbyw> getAll();
}