package com.nis.yj.dao;

import com.nis.yj.entity.Yj003RegularLis;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Yj003RegularLisDao {
	void save(Yj003RegularLis arg0);

	void delete(@Param("id") String arg0);

	void update(Yj003RegularLis arg0);

	Yj003RegularLis get(@Param("id") String arg0);

	List<Yj003RegularLis> findYj003RegularLis(Yj003RegularLis arg0);

	int findYj003RegularLisCount(Yj003RegularLis arg0);

	List<Yj003RegularLis> getAll();
}