package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.Icd10;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Icd10Service {
	void save(Icd10 arg0);

	void delete(String arg0);

	void update(Icd10 arg0);

	Icd10 get(String arg0);

	MyPage<Icd10> a(Icd10 arg0);

	List<Icd10> bs(String arg0);

	List<Icd10> getAll();
}