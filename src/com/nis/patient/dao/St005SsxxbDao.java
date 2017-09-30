package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St005Ssxxb;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface St005SsxxbDao {
	void save(St005Ssxxb arg0);

	void delete(@Param("id") String arg0);

	void update(St005Ssxxb arg0);

	void updateOutHosp(St005Ssxxb arg0);

	void updateNnis(St005Ssxxb arg0);

	void updateSpecified(@Param("st005Ssxxb") St005Ssxxb arg0, @Param("updateAttrs") List<String> arg1);

	St005Ssxxb get(@Param("id") String arg0);

	List<St005Ssxxb> findSt005Ssxxb(St005Ssxxb arg0);

	int findSt005SsxxbCount(St005Ssxxb arg0);

	List<St005Ssxxb> getAll();

	List<St005Ssxxb> findListByZyid(@Param("zyid") String arg0);

	List<St005Ssxxb> findListByPatientId(@Param("patientId") String arg0, @Param("operAt") Date arg1);

	List<St005Ssxxb> findListByName(@Param("operNames") String[] arg0);

	Integer findGradeType(@Param("zyid") String arg0);

	int findSurgeryListCount(St005Ssxxb arg0);

	List<St005Ssxxb> findSurgeryList(St005Ssxxb arg0);

	List<St005Ssxxb> findWaitCalcNnis(@Param("status") String arg0, @Param("nnis") String arg1);

	List<St005Ssxxb> findSurgeryListExcel(St005Ssxxb arg0);

	int findSurgeryDayListCount(St005Ssxxb arg0);

	List<St005Ssxxb> findSurgeryDayList(St005Ssxxb arg0);

	List<St005Ssxxb> findSurgeryDayListExcel(St005Ssxxb arg0);

	Map<String, Object> findSurgeryZdCount(St005Ssxxb arg0);

	Map<String, Object> findSurgeryQkCount(St005Ssxxb arg0);

	Date getMonitorPatientSsxxLastAt(@Param("deptId") String arg0);

	List<DataWarning> findPatentSsxxbWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	Date getNumBefore(@Param("zyid") String arg0, @Param("updDate") Date arg1, @Param("day") int arg2);

	Integer getNnisPer(@Param("impType") Integer arg0, @Param("impOpeId") String arg1);

	St005Ssxxb getByRelid(@Param("relid") String arg0);

	Date getRecentOperAt(@Param("zyid") String arg0, @Param("operAt") Date arg1);
}