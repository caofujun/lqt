package com.nis.zg.dao;

import com.nis.zg.entity.Zg027LisbbPp;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Zg027LisbbPpDao {
	void save(Zg027LisbbPp arg0);

	List<Zg027LisbbPp> findZg027LisbbPp(Zg027LisbbPp arg0);

	int findZg027LisbbPpCount(Zg027LisbbPp arg0);

	void match();

	Map<String, String> queryMatched();

	List<Zg027LisbbPp> findZg027LisbbPpList(Zg027LisbbPp arg0);

	int findZg027LisbbPpListCount(Zg027LisbbPp arg0);

	List<Zg027LisbbPp> getAll();

	Zg027LisbbPp get(@Param("bbppId") String arg0);

	void update(Zg027LisbbPp arg0);

	List<Zg027LisbbPp> findbySt009();

	List<Zg027LisbbPp> matchBefore();
}