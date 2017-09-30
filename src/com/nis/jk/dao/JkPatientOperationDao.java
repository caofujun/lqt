package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientOperation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientOperationDao {
	void save(JkPatientOperation arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientOperation arg0);

	JkPatientOperation get(@Param("id") String arg0);

	List<JkPatientOperation> findJkPatientOperation(JkPatientOperation arg0);

	int findJkPatientOperationCount(JkPatientOperation arg0);

	List<JkPatientOperation> getAll();

	void updateFlag(JkPatientOperation arg0);
}