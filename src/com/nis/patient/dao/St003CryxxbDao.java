package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St003CryxxbDao {
	void save(St003Cryxxb arg0);

	void delete(@Param("zyid") String arg0);

	void update(St003Cryxxb arg0);

	St003Cryxxb get(@Param("zyid") String arg0);

	St003Cryxxb getLastSt003CryxxbByPatientId(@Param("patientId") String arg0);

	List<St003Cryxxb> findSt003Cryxxb(St003Cryxxb arg0);

	int findSt003CryxxbCount(St003Cryxxb arg0);

	List<St003Cryxxb> getAll();

	St003Cryxxb getPatientRecords(@Param("zyid") String arg0);

	List<St003Cryxxb> findCryxxByPatientId(@Param("patientId") String arg0);

	List<St003Cryxxb> findCryxxByFX();

	int findCountbyDate(St003Cryxxb arg0);

	St003Cryxxb getSt003Cryxxb(@Param("zyid") String arg0);

	List<St003Cryxxb> findByTypeIdAndDeptId(@Param("unitId") String arg0, @Param("typeid") String arg1,
			@Param("deptid") String arg2, @Param("neonatebw") String arg3, @Param("startdate") Date arg4,
			@Param("enddate") Date arg5, @Param("orclBegNum") Integer arg6, @Param("orclEndNum") Integer arg7);

	int findByTypeIdAndDeptIdCount(@Param("unitId") String arg0, @Param("typeid") String arg1,
			@Param("deptid") String arg2, @Param("neonatebw") String arg3, @Param("startdate") Date arg4,
			@Param("enddate") Date arg5);

	List<St003Cryxxb> findByDateAndDeptId(@Param("unitId") String arg0, @Param("deptid") String arg1,
			@Param("startdate") Date arg2, @Param("enddate") Date arg3, @Param("tw") Double arg4,
			@Param("orclBegNum") Integer arg5, @Param("orclEndNum") Integer arg6);

	int findByDateAndDeptIdCount(@Param("unitId") String arg0, @Param("deptid") String arg1,
			@Param("startdate") Date arg2, @Param("enddate") Date arg3, @Param("tw") Double arg4);

	List<St003Cryxxb> findBedByTypeIdAndDeptId(@Param("unitId") String arg0, @Param("typeid") String arg1,
			@Param("deptid") String arg2, @Param("neonatebw") String arg3, @Param("startdate") Date arg4,
			@Param("enddate") Date arg5, @Param("orclBegNum") Integer arg6, @Param("orclEndNum") Integer arg7);

	int findBedByTypeIdAndDeptIdCount(@Param("unitId") String arg0, @Param("typeid") String arg1,
			@Param("deptid") String arg2, @Param("neonatebw") String arg3, @Param("startdate") Date arg4,
			@Param("enddate") Date arg5);

	List<St003Cryxxb> findBedByDateAndDeptId(@Param("unitId") String arg0, @Param("deptid") String arg1,
			@Param("startdate") Date arg2, @Param("enddate") Date arg3, @Param("tw") Double arg4,
			@Param("orclBegNum") Integer arg5, @Param("orclEndNum") Integer arg6);

	int findBedByDateAndDeptIdCount(@Param("unitId") String arg0, @Param("deptid") String arg1,
			@Param("startdate") Date arg2, @Param("enddate") Date arg3, @Param("tw") Double arg4);

	List<St003Cryxxb> findPatientList(St003Cryxxb arg0);

	int findPatientListCount(St003Cryxxb arg0);

	List<St003Cryxxb> findInHospPatientListByDeptId(@Param("deptId") String arg0, @Param("searchString") String arg1,
			@Param("showCrb") String arg2, @Param("qzValue") String arg3);

	List<St003Cryxxb> findInHospPatientListByDeptIdNew(@Param("deptId") String arg0, @Param("searchString") String arg1,
			@Param("showCrb") String arg2, @Param("patientRange") String arg3, @Param("docNo") String arg4,
			@Param("ygyj") String arg5, @Param("ygqr") String arg6, @Param("dnlx") String arg7,
			@Param("ydnlx") String arg8, @Param("dbhz") String arg9, @Param("key") String arg10,
			@Param("qzValue") String arg11);

	List<St003Cryxxb> findNewbornList(St003Cryxxb arg0);

	int findNewbornListCount(St003Cryxxb arg0);

	int getSuspectedCaseZyCount(St020ClinicPatients arg0);

	List<St003Cryxxb> getSuspectedCaseZy(St020ClinicPatients arg0);

	List<St003Cryxxb> findOutHospPatientListByDeptId(@Param("deptId") String arg0, @Param("searchString") String arg1,
			@Param("startDate") String arg2, @Param("endDate") String arg3, @Param("showCrb") String arg4,
			@Param("qzValue") String arg5);

	List<St003Cryxxb> findOutHospPatientListByDeptIdNew(@Param("deptId") String arg0,
			@Param("searchString") String arg1, @Param("startDate") String arg2, @Param("endDate") String arg3,
			@Param("showCrb") String arg4, @Param("patientRange") String arg5, @Param("docNo") String arg6,
			@Param("ygyj") String arg7, @Param("ygqr") String arg8, @Param("dnlx") String arg9,
			@Param("ydnlx") String arg10, @Param("key") String arg11, @Param("qzValue") String arg12);

	List<St003Cryxxb> findYjCalInfo(@Param("rownum") int arg0);

	int getSCSZyCount(St020ClinicPatients arg0);

	List<St003Cryxxb> getSCSZy(St020ClinicPatients arg0);

	List<St003Cryxxb> findByPatientIdAndVisitId(@Param("patientId") String arg0, @Param("visitId") Integer arg1);

	Date getMonitorPatientCountByInAt(@Param("deptId") String arg0);

	Date getMonitorPatientCountByOutAt(@Param("deptId") String arg0);

	List<DataWarning> findPatentCryxxbWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<St003Cryxxb> ewListForInterface(St003Cryxxb arg0);
}