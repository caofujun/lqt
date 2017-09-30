package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw006Cyds;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw006CydsService {
	void save(Hw006Cyds arg0);

	void delete(String arg0);

	void update(Hw006Cyds arg0);

	Hw006Cyds get(String arg0);

	MyPage<Hw006Cyds> a(Hw006Cyds arg0);

	List<Hw006Cyds> getAll();

	List<Hw006Cyds> queryList(Hw006Cyds arg0);

	List<Hw006Cyds> findList(Hw006Cyds arg0);

	String findMaxPosId();

	void updFlag(Hw006Cyds arg0);
}