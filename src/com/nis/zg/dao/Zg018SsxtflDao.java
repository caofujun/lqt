package com.nis.zg.dao;

import com.nis.zg.entity.Zg018Ssxtfl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg018SsxtflDao {
	void save(Zg018Ssxtfl arg0);

	void delete(@Param("opesysid") String arg0);

	void update(Zg018Ssxtfl arg0);

	Zg018Ssxtfl get(@Param("opesysid") String arg0);

	List<Zg018Ssxtfl> findZg018Ssxtfl(Zg018Ssxtfl arg0);

	int findZg018SsxtflCount(Zg018Ssxtfl arg0);

	List<Zg018Ssxtfl> getAll();
}