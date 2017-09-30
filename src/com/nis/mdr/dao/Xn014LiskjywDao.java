package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn014Liskjyw;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Xn014LiskjywDao {
	void save(Xn014Liskjyw arg0);

	void delete(@Param("drugid") String arg0);

	void update(Xn014Liskjyw arg0);

	void match();

	Map<String, String> queryMatched();

	Xn014Liskjyw get(@Param("drugid") String arg0);

	List<Xn014Liskjyw> findXn014Liskjyw(Xn014Liskjyw arg0);

	int findXn014LiskjywCount(Xn014Liskjyw arg0);

	List<Xn014Liskjyw> getAll();

	List<Xn014Liskjyw> findXn014LiskjywList(Xn014Liskjyw arg0);

	int findXn014LiskjywListCount(Xn014Liskjyw arg0);

	List<Xn014Liskjyw> matchBefore();
}