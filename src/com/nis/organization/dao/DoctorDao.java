package com.nis.organization.dao;

import com.nis.organization.entity.Doctor;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorDao {
	void save(Doctor arg0);

	void delete(@Param("employeeId") String arg0);

	void update(Doctor arg0);

	Doctor get(@Param("employeeId") String arg0);

	Doctor getDoctorByHisDocNo(Doctor arg0);

	List<Doctor> findDoctor(Doctor arg0);

	int findDoctorCount(Doctor arg0);

	List<Doctor> getAll(@Param("hospId") String arg0);

	List<Doctor> listDoctor(@Param("hospId") String arg0, @Param("doctorIdList") List<String> arg1);

	List<Doctor> findDoctorListByDept(@Param("hospId") String arg0, @Param("deptIds") List<String> arg1,
			@Param("deptId") String arg2);

	List<Doctor> findLike(@Param("hospId") String arg0, @Param("deptId") String arg1, @Param("name") String arg2);

	List<Doctor> login(@Param("hospId") String arg0, @Param("employeeId") String arg1, @Param("pwd") String arg2);

	void updatePwd(@Param("hospId") String arg0, @Param("employeeId") String arg1, @Param("authCode") String arg2);

	Doctor getUnitIdDocId(@Param("hospId") String arg0, @Param("deptId") String arg1, @Param("employeeId") String arg2);

	List<Doctor> findExtis(Doctor arg0);

	int findNosetDepDoctorCount(@Param("doctor") Doctor arg0, @Param("deptId") String arg1);

	List<Doctor> findNosetDepDoctor(@Param("doctor") Doctor arg0, @Param("deptId") String arg1);

	List<Doctor> queryToSelect(Doctor arg0);

	List<Doctor> queryToSelect1(Doctor arg0);
}