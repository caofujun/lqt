package com.nis.zg.dao;

import com.nis.zg.entity.Zg017Ssczfl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg017SsczflDao {
	void save(Zg017Ssczfl arg0);

	void delete(@Param("opepartkindid") String arg0);

	void update(Zg017Ssczfl arg0);

	Zg017Ssczfl get(@Param("opepartkindid") String arg0);

	List<Zg017Ssczfl> findZg017Ssczfl(Zg017Ssczfl arg0);

	int findZg017SsczflCount(Zg017Ssczfl arg0);

	List<Zg017Ssczfl> getAll();
}