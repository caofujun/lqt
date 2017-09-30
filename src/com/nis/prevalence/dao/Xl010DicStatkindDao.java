package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl010DicStatkind;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl010DicStatkindDao {
	void save(Xl010DicStatkind arg0);

	void delete(@Param("statid") String arg0);

	void update(Xl010DicStatkind arg0);

	Xl010DicStatkind get(@Param("statid") String arg0);

	List<Xl010DicStatkind> findXl010DicStatkind(Xl010DicStatkind arg0);

	int findXl010DicStatkindCount(Xl010DicStatkind arg0);

	List<Xl010DicStatkind> getAll();
}