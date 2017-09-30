package com.nis.zg.dao;

import com.nis.zg.entity.Zg006ZdmxFa;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg006ZdmxFaDao {
	void save(Zg006ZdmxFa arg0);

	void delete(@Param("id") String arg0);

	void update(Zg006ZdmxFa arg0);

	Zg006ZdmxFa get(@Param("id") String arg0);

	List<Zg006ZdmxFa> findZg006ZdmxFa(Zg006ZdmxFa arg0);

	int findZg006ZdmxFaCount(Zg006ZdmxFa arg0);

	List<Zg006ZdmxFa> getAll();

	void start(@Param("id") String arg0);

	void stop(@Param("id") String arg0);

	void adjustWeight(@Param("id") String arg0, @Param("field") String arg1, @Param("weight") Integer arg2,
			@Param("createUser") String arg3, @Param("createTime") Date arg4);

	void updDescribe(@Param("id") String arg0, @Param("faDescribe") String arg1, @Param("createUser") String arg2,
			@Param("createTime") Date arg3);

	Zg006ZdmxFa getState();
}