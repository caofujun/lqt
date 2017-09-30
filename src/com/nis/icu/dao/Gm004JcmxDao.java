package com.nis.icu.dao;

import com.nis.comm.entity.TreeEntity;
import com.nis.icu.entity.CgPg;
import com.nis.icu.entity.Gm004Jcmx;
import com.nis.icu.entity.IcuCount;
import com.nis.icu.entity.NicuCount;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface Gm004JcmxDao {
	void save(Gm004Jcmx arg0);

	void delete(@Param("creationdate") Date arg0);

	void update(Gm004Jcmx arg0);

	List<Map<String, Object>> getDeptPatient(@Param("time") String arg0, @Param("deptId") String arg1);

	String getPatientNum(@Param("time") String arg0, @Param("deptId") String arg1);

	List<TreeEntity> getDeptPatientMenuNode(@Param("deptCode") String arg0, @Param("time") String arg1);

	void updateSpecified(@Param("gm004Jcmx") Gm004Jcmx arg0, @Param("updateAttrs") List<String> arg1);

	Gm004Jcmx get(@Param("creationdate") Date arg0);

	List<Gm004Jcmx> findGm004Jcmx(Gm004Jcmx arg0);

	int findGm004JcmxCount(Gm004Jcmx arg0);

	List<Gm004Jcmx> getAll();

	Map<String, Object> findTodo(@Param("startDate") Date arg0, @Param("endDate") Date arg1, @Param("time") String arg2,
			@Param("qzValue") String arg3, @Param("days") Integer arg4);

	List<Gm004Jcmx> findByZyid(@Param("zyid") String arg0, @Param("time") String arg1);

	List<Map<String, Object>> findqrxczxgxgr(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("ificu") String arg2);

	Map<String, Object> findDcl(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("deptIdIn") List<String> arg2, @Param("qzValue") String arg3);

	List<NicuCount> findNicuCount(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2);

	Map<String, Object> findFloorGeneral(@Param("startDate") String arg0, @Param("endDate") String arg1,
			@Param("tw") Double arg2);

	Map<String, Object> findTodo(@Param("startDate") Date arg0, @Param("endDate") Date arg1, @Param("time") String arg2,
			@Param("qzValue") String arg3);

	Date getMonitorPatientTubeLastAt();

	List<CgPg> findCgByZyid(@Param("zyid") String arg0, @Param("startDate") Date arg1, @Param("endDate") Date arg2,
			@Param("time") String arg3);

	List<Gm004Jcmx> getpatientList(@Param("patientId") String arg0, @Param("time") String arg1);

	List<Map<String, Object>> getDeptPatient2(@Param("time") String arg0, @Param("queryDate") String arg1,
			@Param("deptId") String arg2);

	String getPatientNum2(@Param("time") String arg0, @Param("queryDate") String arg1, @Param("deptId") String arg2);

	List<TreeEntity> getDeptPatientMenuNode2(@Param("deptCode") String arg0, @Param("queryDate") String arg1,
			@Param("time") String arg2);

	int getNumByTypeId(@Param("zyid") String arg0, @Param("typeid") String arg1, @Param("startAt") Date arg2,
			@Param("stopAt") Date arg3);

	void judgeGm004(Map<String, Object> arg0);

	void deleteHxj(Gm004Jcmx arg0);

	void updateHxjCount(Gm004Jcmx arg0);

	List<IcuCount> findBedIcuByorderName(@Param("deptId") String arg0, @Param("startDate") String arg1,
			@Param("endDate") String arg2, @Param("tw") Double arg3, @Param("unitId") String arg4);
}