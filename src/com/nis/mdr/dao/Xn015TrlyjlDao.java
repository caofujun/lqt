package com.nis.mdr.dao;

import com.nis.mdr.entity.Xn015Trlyjl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xn015TrlyjlDao {
	void save(Xn015Trlyjl arg0);

	void update(Xn015Trlyjl arg0);

	Xn015Trlyjl get(@Param("testOrderNo") String arg0, @Param("pathoCode") String arg1, @Param("antiCode") String arg2);

	List<Xn015Trlyjl> findXn015Trlyjl(Xn015Trlyjl arg0);

	int findXn015TrlyjlCount(Xn015Trlyjl arg0);

	List<Xn015Trlyjl> getAll();
}