package com.nis.patient.dao;

import com.nis.patient.entity.PatientView;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St009Sjbb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St001JbxxbDao {
	void save(St001Jbxxb arg0);

	void delete(@Param("id") String arg0);

	void update(St001Jbxxb arg0);

	St001Jbxxb get(@Param("id") String arg0);

	List<St001Jbxxb> findSt001Jbxxb(St001Jbxxb arg0);

	int findSt001JbxxbCount(St001Jbxxb arg0);

	List<St001Jbxxb> getAll();

	List<PatientView> findbyZyid(@Param("zyid") String arg0, @Param("inHospAt") String arg1,
			@Param("startDate") String arg2, @Param("endDate") String arg3);

	void updWeightByZyid(@Param("zyid") String arg0, @Param("weight") String arg1);

	List<St001Jbxxb> getByPatientId(@Param("patientId") String arg0);

	List<St009Sjbb> findbyZyidAndClassCode(@Param("zyid") String arg0, @Param("classCode") String arg1,
			@Param("remark") List<String> arg2);
}