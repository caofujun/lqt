package com.nis.zg.dao;

import com.nis.comm.entity.TreeEntity;
import com.nis.zg.entity.Zg002Byks;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg002ByksDao {
	void save(Zg002Byks arg0);

	void delete(@Param("id") String arg0);

	void update(Zg002Byks arg0);

	Zg002Byks get(@Param("id") String arg0);

	Zg002Byks getByDeptId(@Param("deptId") String arg0);

	List<Zg002Byks> findZg002Byks(Zg002Byks arg0);

	int findZg002ByksCount(Zg002Byks arg0);

	List<Zg002Byks> getAll();

	List<TreeEntity> getRoot(@Param("nol") String arg0);

	List<TreeEntity> getLeaf(@Param("classify") String arg0, @Param("nol") String arg1);
}