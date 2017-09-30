package com.nis.dict.dao;

import com.nis.dict.entity.Sop;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SopDao {
	void save(Sop arg0);

	void delete(@Param("fileId") String arg0);

	void update(Sop arg0);

	Sop get(@Param("fileId") String arg0);

	List<Sop> findSop(Sop arg0);

	int findSopCount(Sop arg0);

	List<Sop> getAll();
}