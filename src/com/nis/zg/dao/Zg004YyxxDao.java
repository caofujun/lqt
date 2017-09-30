package com.nis.zg.dao;

import com.nis.zg.entity.Zg004Yyxx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg004YyxxDao {
	void save(Zg004Yyxx arg0);

	void delete(@Param("hospId") String arg0);

	void update(Zg004Yyxx arg0);

	Zg004Yyxx get(@Param("hospId") String arg0);

	List<Zg004Yyxx> findZg004Yyxx(Zg004Yyxx arg0);

	int findZg004YyxxCount(Zg004Yyxx arg0);

	List<Zg004Yyxx> getAll();
}