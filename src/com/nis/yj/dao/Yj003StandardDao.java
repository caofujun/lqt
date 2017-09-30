package com.nis.yj.dao;

import com.nis.yj.entity.Yj003Standard;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Yj003StandardDao {
	void save(Yj003Standard arg0);

	void delete(@Param("id") String arg0);

	void update(Yj003Standard arg0);

	Yj003Standard get(@Param("id") String arg0);

	List<Yj003Standard> findYj003Standard(Yj003Standard arg0);

	int findYj003StandardCount(Yj003Standard arg0);

	List<Yj003Standard> getAll();
}