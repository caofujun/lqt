package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St002Zdxxb;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St002ZdxxbDao {
	void save(St002Zdxxb arg0);

	void delete(@Param("id") String arg0);

	void update(St002Zdxxb arg0);

	St002Zdxxb get(@Param("id") String arg0);

	List<St002Zdxxb> findSt002Zdxxb(St002Zdxxb arg0);

	int findSt002ZdxxbCount(St002Zdxxb arg0);

	List<St002Zdxxb> getAll();

	List<St002Zdxxb> findListByZyid(@Param("zyid") String arg0);

	List<St002Zdxxb> findListByZyidAndName(@Param("diagnosisNames") String[] arg0);

	String findDiagnosisName(@Param("zyid") String arg0);

	List<St002Zdxxb> findZyZdList(@Param("rownum") int arg0);

	List<DataWarning> findPatentZdxxbWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);
}