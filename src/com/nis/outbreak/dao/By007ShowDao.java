package com.nis.outbreak.dao;

import com.nis.outbreak.entity.By007Show;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface By007ShowDao {
	void save(By007Show arg0);

	void delete(@Param("id") String arg0);

	void update(By007Show arg0);

	By007Show get(@Param("id") String arg0);

	List<By007Show> findBy007Show(By007Show arg0);

	int findBy007ShowCount(By007Show arg0);

	List<By007Show> getAll();

	void callPrStatBfyjAbsoluteWarn(Map<String, Object> arg0);

	void callPrStatBfyjAbsoluteDept(Map<String, Object> arg0);

	void callPrStatMasterAbsolute(Map<String, Object> arg0);

	void callPrStatBfyjRelativeWarn(Map<String, Object> arg0);

	void callPrStatBfyjRelativeDept(Map<String, Object> arg0);

	void callPrStatMasterRelative(Map<String, Object> arg0);

	List<By007Show> findShowFields(@Param("showType") Integer arg0);

	List<St003Cryxxb> findPatientInfo(By007Show arg0);

	List<St003Cryxxb> findBloodCulturePatient(By007Show arg0);

	List<St003Cryxxb> findPathogenPatient(By007Show arg0);

	List<Map<String, Object>> callPrStatBy0007Detail(Map<String, Object> arg0);

	List<St003Cryxxb> findInfectionSiteList(By007Show arg0);
}