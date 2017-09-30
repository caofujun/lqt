package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw009Kssq;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw009KssqService {
	void save(Hw009Kssq arg0);

	void delete(String arg0, String arg1);

	void update(Hw009Kssq arg0);

	Hw009Kssq get(String arg0, String arg1);

	MyPage<Hw009Kssq> a(Hw009Kssq arg0);

	List<Hw009Kssq> getAll();

	List<Hw009Kssq> queryList(Hw009Kssq arg0);

	List<Hw009Kssq> findAccreditDept(Hw009Kssq arg0);
}