package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw001Jcxm;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw001JcxmDao {
	void save(Hw001Jcxm arg0);

	void delete(@Param("classId") String arg0);

	void delByClassIdPclassId(@Param("classId") String arg0);

	void update(Hw001Jcxm arg0);

	void updateSpecified(@Param("hw001Jcxm") Hw001Jcxm arg0, @Param("updateAttrs") List<String> arg1);

	Hw001Jcxm get(@Param("classId") String arg0);

	List<Hw001Jcxm> findHw001Jcxm(Hw001Jcxm arg0);

	int findHw001JcxmCount(Hw001Jcxm arg0);

	List<Hw001Jcxm> getAll();

	List<Hw001Jcxm> queryTree(Hw001Jcxm arg0);

	List<Hw001Jcxm> findList(Hw001Jcxm arg0);

	List<Hw001Jcxm> queryList(Hw001Jcxm arg0);

	List<Hw001Jcxm> findSubclass(@Param("pclassId") String arg0);
}