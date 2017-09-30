package com.nis.zg.dao;

import com.nis.zg.entity.Zg003Yyzg;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg003YyzgDao {
	void save(Zg003Yyzg arg0);

	void delete(@Param("id") String arg0);

	void update(Zg003Yyzg arg0);

	Zg003Yyzg get(@Param("id") String arg0);

	List<Zg003Yyzg> findZg003Yyzg(Zg003Yyzg arg0);

	int findZg003YyzgCount(Zg003Yyzg arg0);

	List<Zg003Yyzg> getAll();

	List<Zg003Yyzg> getByName(@Param("employeeName") String arg0, @Param("deptId") String arg1);
}