package com.nis.zg.dao;

import com.nis.zg.entity.Zg011Ss;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg011SsDao {
	void save(Zg011Ss arg0);

	void delete(@Param("icdId") String arg0);

	Zg011Ss getByOperId(@Param("operId") String arg0);

	void update(Zg011Ss arg0);

	Zg011Ss get(@Param("icdId") String arg0);

	List<Zg011Ss> findZg011Ss(Zg011Ss arg0);

	int findZg011SsCount(Zg011Ss arg0);

	List<Zg011Ss> getAll();

	List<Zg011Ss> getOperaInfo(@Param("operName") String arg0);

	List<Zg011Ss> query(Zg011Ss arg0);
}