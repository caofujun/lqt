package com.nis.zg.dao;

import com.nis.zg.entity.Zg024ImpOpe;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg024ImpOpeDao {
	void save(Zg024ImpOpe arg0);

	void delete(@Param("impOpeId") String arg0);

	void update(Zg024ImpOpe arg0);

	Zg024ImpOpe get(@Param("impOpeId") String arg0);

	List<Zg024ImpOpe> findZg024ImpOpe(Zg024ImpOpe arg0);

	int findZg024ImpOpeCount(Zg024ImpOpe arg0);

	List<Zg024ImpOpe> getAll();
}