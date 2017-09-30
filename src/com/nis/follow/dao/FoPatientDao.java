package com.nis.follow.dao;

import com.nis.follow.entity.FoPatient;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FoPatientDao {
	void save(FoPatient arg0);

	void delete(@Param("foId") String arg0);

	void update(FoPatient arg0);

	FoPatient get(@Param("foId") String arg0);

	List<FoPatient> findFoPatient(FoPatient arg0);

	int findFoPatientCount(FoPatient arg0);

	List<FoPatient> getAll();

	void deleteByPatientId(@Param("patientId") String arg0, @Param("followName") String arg1);

	List<FoPatient> getByPatientId(@Param("patientId") String arg0);

	List<St003Cryxxb> findByDeptIdDetail(FoPatient arg0);
}