package com.nis.icu.dao;

import com.nis.icu.entity.Gm001Djpdm;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gm001DjpdmDao {
	void save(Gm001Djpdm arg0);

	void update(Gm001Djpdm arg0);

	List<Gm001Djpdm> findGm001Djpdm(Gm001Djpdm arg0);

	int findGm001DjpdmCount(Gm001Djpdm arg0);

	List<Gm001Djpdm> getAll();

	Gm001Djpdm getByDeptIdAndDate(@Param("dtYear") Integer arg0, @Param("dtMonth") Integer arg1,
			@Param("deptId") String arg2, @Param("weeknumber") String arg3);
}