package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw008Xmsq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw008XmsqDao {
	void save(Hw008Xmsq arg0);

	void delete(@Param("userId") String arg0, @Param("classId") String arg1);

	void update(Hw008Xmsq arg0);

	Hw008Xmsq get(@Param("userId") String arg0, @Param("classId") String arg1);

	List<Hw008Xmsq> findHw008Xmsq(Hw008Xmsq arg0);

	int findHw008XmsqCount(Hw008Xmsq arg0);

	List<Hw008Xmsq> getAll();

	void delByUserIdAndDeptId(@Param("userId") String arg0);

	List<Hw008Xmsq> findXmsqList(Hw008Xmsq arg0);

	int judgeResultsPermissions(@Param("userId") String arg0, @Param("deptId") String arg1,
			@Param("djDeptId") String arg2, @Param("classId") String arg3);

	int judgeReportPermissions(@Param("userId") String arg0, @Param("deptId") String arg1,
			@Param("djDeptId") String arg2, @Param("classId") String arg3);
}