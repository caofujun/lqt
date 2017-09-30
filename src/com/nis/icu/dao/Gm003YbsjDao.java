package com.nis.icu.dao;

import com.nis.icu.entity.Gm003Ybsj;
import com.nis.icu.entity.IcuCount;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Gm003YbsjDao {
	void save(Gm003Ybsj arg0);

	void delete(@Param("creationdate") Date arg0);

	void update(Gm003Ybsj arg0);

	Gm003Ybsj get(@Param("creationdate") Date arg0);

	List<Gm003Ybsj> findGm003Ybsj(Gm003Ybsj arg0);

	int findGm003YbsjCount(Gm003Ybsj arg0);

	List<Gm003Ybsj> getAll();

	List<IcuCount> findIcuCount(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("tw") Double arg3, @Param("unitId") String arg4);

	List<IcuCount> findMonitorByMonth(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("tw") Double arg3, @Param("unitId") String arg4);

	List<IcuCount> findBedMonitorByMonth(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("tw") Double arg3, @Param("unitId") String arg4);

	IcuCount findMonitorByMonthSum(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("tw") Double arg3, @Param("unitId") String arg4);

	List<IcuCount> findDayCount(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("deptType") String arg3, @Param("tw") Double arg4,
			@Param("unitId") String arg5);

	int findGm003YbsjKfjcCount(Gm003Ybsj arg0);

	List<Gm003Ybsj> findGm003YbsjKfjc(Gm003Ybsj arg0);

	void kfjcDelete(Gm003Ybsj arg0);

	List<Gm003Ybsj> findkfjcByCreationdate(Gm003Ybsj arg0);

	List<Map<String, Object>> findMainKs(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findMainFx(@Param("startDate") Date arg0, @Param("endDate") Date arg1);
}