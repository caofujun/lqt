package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.ResistantAnalysis;
import com.nis.patient.entity.St009Sjbb;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface St009SjbbDao {
	void save(St009Sjbb arg0);

	void delete(@Param("id") String arg0);

	void update(St009Sjbb arg0);

	void batchUpdAnalFlag(@Param("st009List") List<St009Sjbb> arg0, @Param("fieldname") String arg1);

	void updateFlagByMzzyid(St009Sjbb arg0);

	void updateFlagByTestOrderNo(St009Sjbb arg0);

	St009Sjbb get(@Param("id") String arg0);

	List<St009Sjbb> findSt009Sjbb(St009Sjbb arg0);

	int findSt009SjbbCount(St009Sjbb arg0);

	List<St009Sjbb> getAll();

	List<St009Sjbb> findSjbbForInfectList(St009Sjbb arg0);

	List<St009Sjbb> findSjbbListBytestNo(@Param("testOrderNo") String arg0);

	List<St009Sjbb> findBacteriaList(St009Sjbb arg0);

	St009Sjbb getCheckResultTitle(St009Sjbb arg0);

	List<Map<String, String>> findFocusFactors(@Param("zyid") String arg0, @Param("excStr") String arg1);

	List<St009Sjbb> getUnAnalysisRecord(@Param("rows") int arg0);

	String masterCdts(@Param("patientType") String arg0, @Param("testOrderNos") String arg1, @Param("sql") String arg2);

	int masterCdtsNew(@Param("patientType") String arg0, @Param("testOrderNos") String arg1, @Param("sql") String arg2);

	Map<String, Date> getMonitorPatientTestLastAt(@Param("deptId") String arg0);

	Map<String, Date> getMonitorPatientTestLastAt6(@Param("deptId") String arg0);

	Map<String, Date> getMonitorPatientTestsLastAt(@Param("deptId") String arg0);

	Map<String, Date> getMonitorPatientTestsLastAt6(@Param("deptId") String arg0);

	List<DataWarning> findPatentSjbbWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<DataWarning> findPatentSjbbWarning6(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<St009Sjbb> findWaitAnalyGerm(@Param("curr") Date arg0, @Param("orclEndNum") Integer arg1,
			@Param("fieldname") String arg2);

	List<St009Sjbb> findWaitAnalyGermV6(@Param("curr") Date arg0, @Param("orclEndNum") Integer arg1,
			@Param("fieldname") String arg2);

	int findWaitAnalyGermCount(@Param("curr") Date arg0, @Param("fieldname") String arg1);

	int findWaitAnalyGermCountV6(@Param("curr") Date arg0, @Param("fieldname") String arg1);

	List<St009Sjbb> findWaitAnalyComm(@Param("curr") Date arg0, @Param("orclEndNum") Integer arg1,
			@Param("fieldname") String arg2);

	List<St009Sjbb> findWaitAnalyCommV6(@Param("curr") Date arg0, @Param("orclEndNum") Integer arg1,
			@Param("fieldname") String arg2);

	int findWaitAnalyCommCount(@Param("curr") Date arg0, @Param("fieldname") String arg1);

	int findWaitAnalyCommCountV6(@Param("curr") Date arg0, @Param("fieldname") String arg1);

	int findWaitAnaResisCount();

	List<ResistantAnalysis> findWaitAnaResis(@Param("orclEndNum") Integer arg0, @Param("startDate") Date arg1,
			@Param("endDate") Date arg2, @Param("testOrderNo") String arg3, @Param("curr") Date arg4);

	List<Map<String, String>> isCheck(St009Sjbb arg0);

	List<Map<String, String>> getTestOderNo(St009Sjbb arg0);
}