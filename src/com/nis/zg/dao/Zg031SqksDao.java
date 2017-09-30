package com.nis.zg.dao;

import com.nis.zg.entity.Zg031Sqks;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg031SqksDao {
	void save(Zg031Sqks arg0);

	void delete(Zg031Sqks arg0);

	List<Zg031Sqks> getAll(@Param("userId") String arg0);

	int isTableExist(@Param("tableName") String arg0);

	String getDepts(@Param("userId") String arg0);
}