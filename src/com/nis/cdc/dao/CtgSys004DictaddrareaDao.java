package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys004Dictaddrarea;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys004DictaddrareaDao {
	void save(CtgSys004Dictaddrarea arg0);

	void delete(@Param("areacode") String arg0);

	void update(CtgSys004Dictaddrarea arg0);

	CtgSys004Dictaddrarea get(@Param("areacode") String arg0);

	List<CtgSys004Dictaddrarea> findCtgSys004Dictaddrarea(CtgSys004Dictaddrarea arg0);

	int findCtgSys004DictaddrareaCount(CtgSys004Dictaddrarea arg0);

	List<CtgSys004Dictaddrarea> getAll();

	List<CtgSys004Dictaddrarea> getSheng();

	List<CtgSys004Dictaddrarea> getOther(String arg0);
}