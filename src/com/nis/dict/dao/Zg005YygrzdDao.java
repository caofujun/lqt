package com.nis.dict.dao;

import com.nis.dict.entity.Zg005Yygrzd;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg005YygrzdDao {
	void save(Zg005Yygrzd arg0);

	void delete(@Param("infectCode") String arg0);

	void update(Zg005Yygrzd arg0);

	void updFlag(@Param("infectCode") String arg0, @Param("flag") Integer arg1, @Param("lastAt") Date arg2);

	Zg005Yygrzd get(@Param("infectCode") String arg0);

	List<Zg005Yygrzd> findZg005Yygrzd(Zg005Yygrzd arg0);

	int findZg005YygrzdCount(Zg005Yygrzd arg0);

	List<Zg005Yygrzd> getAll();

	int findNumByInfectCode(@Param("pInfectCode") String arg0, @Param("infectCode") String arg1);

	List<Zg005Yygrzd> queryTree(Zg005Yygrzd arg0);
}