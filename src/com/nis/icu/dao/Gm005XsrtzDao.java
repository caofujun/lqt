package com.nis.icu.dao;

import com.nis.icu.entity.Gm005Xsrtz;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gm005XsrtzDao {
	void save(Gm005Xsrtz arg0);

	void update(Gm005Xsrtz arg0);

	Gm005Xsrtz get(@Param("zyid") String arg0);

	List<Gm005Xsrtz> findGm005Xsrtz(Gm005Xsrtz arg0);

	int findGm005XsrtzCount(Gm005Xsrtz arg0);

	List<Gm005Xsrtz> getAll();
}