package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw005Cyff;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw005CyffDao {
	void save(Hw005Cyff arg0);

	void delete(@Param("takeModeId") String arg0);

	void update(Hw005Cyff arg0);

	Hw005Cyff get(@Param("takeModeId") String arg0);

	List<Hw005Cyff> findHw005Cyff(Hw005Cyff arg0);

	int findHw005CyffCount(Hw005Cyff arg0);

	List<Hw005Cyff> getAll();

	List<Hw005Cyff> queryList(Hw005Cyff arg0);

	List<Hw005Cyff> findList(Hw005Cyff arg0);

	String findMaxTakeModeId();

	void updFlag(Hw005Cyff arg0);
}