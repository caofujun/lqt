package com.nis.zb.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ZbXhlDao {
	List<Map<String, Object>> findDicOffice();

	List<Map<String, Object>> findPatientMain(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findInfectInfo(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findPathoInfo(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findAntibInfo(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findStandDeptUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findXl2BridUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findXl3BridUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findXl3GridUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findXl4BridUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findXl4GridUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	String findXl4BytidUnMatch(@Param("startDate") Date arg0, @Param("endDate") Date arg1);
}