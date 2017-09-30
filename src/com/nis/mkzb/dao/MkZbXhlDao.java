package com.nis.mkzb.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MkZbXhlDao {
	List<Map<String, Object>> findDicOffice();

	List<Map<String, Object>> findPatientMain(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findInfectInfo(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findPathoInfo(@Param("startDate") Date arg0, @Param("endDate") Date arg1);

	List<Map<String, Object>> findAntibInfo(@Param("startDate") Date arg0, @Param("endDate") Date arg1);
}