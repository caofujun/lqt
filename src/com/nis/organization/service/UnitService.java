package com.nis.organization.service;

import com.nis.comm.entity.MyPage;
import com.nis.organization.entity.Unit;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface UnitService {
	void save(Unit arg0);

	void delete(String arg0);

	void update(Unit arg0);

	Unit get(String arg0);

	MyPage<Unit> a(Unit arg0);

	List<Unit> getAll();

	Unit findUnitByUnitName(String arg0);

	List<Unit> findLike(String arg0);

	void updateUnitState(String arg0, String arg1);

	List<Unit> cl(String arg0);

	String getName(String arg0);

	void I(String arg0, String arg1);
}