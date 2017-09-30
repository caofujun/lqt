package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw008Xmsq;
import com.nis.hygiene.entity.Hw009Kssq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw009KssqDao {
	void save(Hw009Kssq arg0);

	void delete(@Param("userId") String arg0, @Param("deptId") String arg1);

	void deleteByUserId(@Param("userId") String arg0);

	void update(Hw009Kssq arg0);

	Hw009Kssq get(@Param("userId") String arg0, @Param("deptId") String arg1);

	List<Hw009Kssq> findHw009Kssq(Hw009Kssq arg0);

	int findHw009KssqCount(Hw009Kssq arg0);

	List<Hw009Kssq> getAll();

	List<Hw009Kssq> queryList(Hw009Kssq arg0);

	List<Hw009Kssq> findAccreditDept(Hw009Kssq arg0);

	void delHw009KssqNotIn(Hw008Xmsq arg0);
}