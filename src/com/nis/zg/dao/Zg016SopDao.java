package com.nis.zg.dao;

import com.nis.zg.entity.Zg016Sop;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg016SopDao {
	void save(Zg016Sop arg0);

	void delete(@Param("fileId") String arg0);

	void update(Zg016Sop arg0);

	Zg016Sop get(@Param("fileId") String arg0);

	List<Zg016Sop> findZg016Sop(Zg016Sop arg0);

	int findZg016SopCount(Zg016Sop arg0);

	List<Zg016Sop> getAll();
}