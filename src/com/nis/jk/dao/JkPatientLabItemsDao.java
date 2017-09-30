package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientLabItems;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientLabItemsDao {
	void save(JkPatientLabItems arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientLabItems arg0);

	JkPatientLabItems get(@Param("id") String arg0);

	List<JkPatientLabItems> findJkPatientLabItems(JkPatientLabItems arg0);

	int findJkPatientLabItemsCount(JkPatientLabItems arg0);

	List<JkPatientLabItems> getAll();

	void updateFlag(JkPatientLabItems arg0);
}