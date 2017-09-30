package com.nis.patient.service;

import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St012Kjyw;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public interface St012KjywService {
	void save(St012Kjyw arg0);

	void delete(String arg0);

	void update(St012Kjyw arg0);

	St012Kjyw get(String arg0);

	MyPage<St012Kjyw> a(St012Kjyw arg0);

	List<St012Kjyw> getAll();

	Set<String> getByLevel(Integer arg0);

	void c(String arg0, Integer arg1);

	Integer getDrugLine(String arg0);

	Map<String, String> queryMatched();

	void match();
}