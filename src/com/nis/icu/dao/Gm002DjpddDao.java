package com.nis.icu.dao;

import com.nis.icu.entity.Gm001Djpdm;
import com.nis.icu.entity.Gm002Djpdd;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gm002DjpddDao {
	void save(Gm002Djpdd arg0);

	void update(Gm002Djpdd arg0);

	List<Gm002Djpdd> findGm002Djpdd(Gm002Djpdd arg0);

	int findGm002DjpddCount(Gm002Djpdd arg0);

	List<Gm002Djpdd> getAll();

	Gm002Djpdd get(@Param("dtYear") Integer arg0, @Param("dtMonth") Integer arg1, @Param("weeknumber") Integer arg2,
			@Param("zyid") String arg3);

	List<Gm002Djpdd> getByDateAndDeptId(@Param("deptId") String arg0, @Param("strDate") String arg1,
			@Param("dtYear") Integer arg2, @Param("dtMonth") Integer arg3, @Param("weeknumber") Integer arg4);

	List<Gm002Djpdd> getBedByDateAndDeptId(@Param("deptId") String arg0, @Param("strDate") String arg1,
			@Param("dtYear") Integer arg2, @Param("dtMonth") Integer arg3, @Param("weeknumber") Integer arg4);

	int getCountByDateAndDeptId(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("dtYear") Integer arg3, @Param("dtMonth") Integer arg4,
			@Param("weeknumber") Integer arg5);

	int getBedCountByDateAndDeptId(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("dtYear") Integer arg3, @Param("dtMonth") Integer arg4,
			@Param("weeknumber") Integer arg5);

	Gm001Djpdm getByWeekAndDeptId(@Param("deptId") String arg0, @Param("dtYear") Integer arg1,
			@Param("dtMonth") Integer arg2, @Param("weeknumber") Integer arg3);

	void saveList(@Param("djpddList") List<Gm002Djpdd> arg0);
}