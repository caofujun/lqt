package com.nis.intervene.dao;

import com.nis.intervene.entity.FxPatient;
import com.nis.intervene.entity.FxPatientIndex;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FxPatientDao {
	void save(FxPatient arg0);

	void delete(@Param("fxId") String arg0);

	void update(FxPatient arg0);

	FxPatient get(@Param("fxId") String arg0);

	List<FxPatient> findFxPatient(FxPatient arg0);

	int findFxPatientCount(FxPatient arg0);

	List<FxPatient> getAll();

	FxPatient findByZyid(@Param("zyid") String arg0);

	int findDepFxCount(FxPatient arg0);

	List<FxPatientIndex> findDepFx(FxPatient arg0);

	List<FxPatient> findFxPatientList(FxPatient arg0);

	int findCountbyDate(FxPatient arg0);

	List<FxPatientIndex> findDepFxList(FxPatient arg0);

	List<FxPatient> findFxPatientByList(@Param("cryxxbList") List<St003Cryxxb> arg0);
}