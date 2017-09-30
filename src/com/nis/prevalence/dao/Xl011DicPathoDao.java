package com.nis.prevalence.dao;

import com.nis.prevalence.entity.Xl011DicPatho;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Xl011DicPathoDao {
	void save(Xl011DicPatho arg0);

	void delete(@Param("pathoid") String arg0);

	void update(Xl011DicPatho arg0);

	Xl011DicPatho get(@Param("pathoid") String arg0);

	List<Xl011DicPatho> findXl011DicPatho(Xl011DicPatho arg0);

	int findXl011DicPathoCount(Xl011DicPatho arg0);

	List<Xl011DicPatho> getAll();
}