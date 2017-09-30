package com.nis.bl.dao;

import com.nis.bl.entity.Bl006Jyjg;
import com.nis.bl.entity.JyjgFc;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Bl006JyjgDao {
	void save(Bl006Jyjg arg0);

	void delete(@Param("blId") String arg0);

	void update(Bl006Jyjg arg0);

	Bl006Jyjg get(@Param("blId") String arg0, @Param("jyDh") String arg1, @Param("jyHm") String arg2);

	List<Bl006Jyjg> findBl006Jyjg(Bl006Jyjg arg0);

	int findBl006JyjgCount(Bl006Jyjg arg0);

	List<Bl006Jyjg> getAll();

	List<Bl006Jyjg> getBl006JyjgList(@Param("blId") String arg0, @Param("jyDh") String arg1,
			@Param("itemName") String arg2);

	void saveList(@Param("jyjgList") List<Bl006Jyjg> arg0);

	List<Bl006Jyjg> findByItemName(@Param("blId") String arg0, @Param("itemName1") String arg1,
			@Param("itemName2") String arg2);

	List<JyjgFc> findByTime(@Param("startDate") String arg0, @Param("endDate") String arg1);
}