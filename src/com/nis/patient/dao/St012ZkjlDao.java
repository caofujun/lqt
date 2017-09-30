package com.nis.patient.dao;

import com.nis.patient.entity.St012Zkjl;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St012ZkjlDao {
	void save(St012Zkjl arg0);

	void delete(@Param("id") String arg0);

	void update(St012Zkjl arg0);

	St012Zkjl get(@Param("id") String arg0);

	List<St012Zkjl> findSt012Zkjl(St012Zkjl arg0);

	int findSt012ZkjlCount(St012Zkjl arg0);

	List<St012Zkjl> getAll();

	List<St012Zkjl> findInAndOutDepList(String arg0);

	Date getMonitorPatientInZkxxLastAt(@Param("deptId") String arg0);

	Date getMonitorPatientOutZkxxLastAt(@Param("deptId") String arg0);
}