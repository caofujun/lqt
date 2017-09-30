package com.nis.zg.dao;

import com.nis.zg.entity.Zg006Grzdmx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg006GrzdmxDao {
	void save(Zg006Grzdmx arg0);

	void delete(@Param("mxId") Double arg0);

	void update(Zg006Grzdmx arg0);

	Zg006Grzdmx get(@Param("mxId") Double arg0);

	List<Zg006Grzdmx> findZg006Grzdmx(Zg006Grzdmx arg0);

	int findZg006GrzdmxCount(Zg006Grzdmx arg0);

	List<Zg006Grzdmx> getAll();
}