package com.nis.jk.dao;

import com.nis.jk.entity.JkDicDoctor;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkDicDoctorDao {
	void save(JkDicDoctor arg0);

	void delete(@Param("id") String arg0);

	void update(JkDicDoctor arg0);

	JkDicDoctor get(@Param("id") String arg0);

	List<JkDicDoctor> findJkDicDoctor(JkDicDoctor arg0);

	int findJkDicDoctorCount(JkDicDoctor arg0);

	List<JkDicDoctor> getAll();

	void updateFlag(JkDicDoctor arg0);
}