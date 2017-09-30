package com.nis.bl.dao;

import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.entity.JyjgFc;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bl007FcsjDao {
	void save(Bl007Fcsj arg0);

	void delete(@Param("relid") String arg0);

	void update(Bl007Fcsj arg0);

	Bl007Fcsj get(@Param("relid") String arg0);

	List<Bl007Fcsj> findBl007Fcsj(Bl007Fcsj arg0);

	List<Bl007Fcsj> findBl007FcsjList(Bl007Fcsj arg0);

	int findBl007FcsjCount(Bl007Fcsj arg0);

	List<Bl007Fcsj> getAll();

	void saveList(@Param("fcsjList") List<Bl007Fcsj> arg0);

	List<Bl007Fcsj> findByBlId(@Param("blId") String arg0);

	List<JyjgFc> findByTime(@Param("startDate") String arg0, @Param("endDate") String arg1);

	List<Bl007Fcsj> findByDays(@Param("days") Integer arg0);

	void deleteByBlid(@Param("blId") String arg0);

	List<Bl007Fcsj> getFcList(@Param("days") Integer arg0);
}