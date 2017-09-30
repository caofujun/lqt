package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl005Kjywzl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl005KjywzlDao {
	void save(Xl005Kjywzl arg0);

	void delete(@Param("ywzlid") String arg0);

	void update(Xl005Kjywzl arg0);

	Xl005Kjywzl get(@Param("ywzlid") String arg0);

	List<Xl005Kjywzl> findXl005Kjywzl(Xl005Kjywzl arg0);

	int findXl005KjywzlCount(Xl005Kjywzl arg0);

	List<Xl005Kjywzl> getAll();
}