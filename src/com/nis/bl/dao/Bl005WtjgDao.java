package com.nis.bl.dao;

import com.nis.bl.entity.Bl005Wtjg;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bl005WtjgDao {
	void save(Bl005Wtjg arg0);

	void delete(@Param("blId") String arg0, @Param("stId") Long arg1);

	void update(Bl005Wtjg arg0);

	Bl005Wtjg get(@Param("blId") String arg0, @Param("stId") Long arg1);

	List<Bl005Wtjg> findBl005Wtjg(Bl005Wtjg arg0);

	int findBl005WtjgCount(Bl005Wtjg arg0);

	List<Bl005Wtjg> getAll();

	List<Bl005Wtjg> findByBlId(@Param("blId") String arg0);
}