package com.nis.patient.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.patient.entity.St020ClinicPatients;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St020ClinicPatientsDao {
	void save(St020ClinicPatients arg0);

	void delete(@Param("id") String arg0);

	void update(St020ClinicPatients arg0);

	St020ClinicPatients get(@Param("id") String arg0);

	List<St020ClinicPatients> findSt020ClinicPatients(St020ClinicPatients arg0);

	List<St020ClinicPatients> findSt020ClinicPatientsForMain(St020ClinicPatients arg0);

	int findSt020ClinicPatientsCount(St020ClinicPatients arg0);

	List<St020ClinicPatients> getAll();

	int getSuspectedCaseMzCount(St020ClinicPatients arg0);

	List<St020ClinicPatients> getSuspectedCaseMz(St020ClinicPatients arg0);

	St020ClinicPatients getByMzid(@Param("mzid") String arg0, @Param("hospId") String arg1);

	int getSCSMzCount(St020ClinicPatients arg0);

	List<St020ClinicPatients> getSCSMz(St020ClinicPatients arg0);

	List<St020ClinicPatients> getByPatientId(@Param("patientId") String arg0);

	List<St020ClinicPatients> findByPatientIdAndVisitId(@Param("patientId") String arg0,
			@Param("visitId") Integer arg1);

	List<St020ClinicPatients> findMzZdList(@Param("rownum") int arg0);

	int getNewSCSCount(St020ClinicPatients arg0);

	List<St020ClinicPatients> getNewSCS(St020ClinicPatients arg0);

	List<DataWarning> findPatentClinicWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	int findSt020ClinicPatientsForMainCount(St020ClinicPatients arg0);
}