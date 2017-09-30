package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg012Icd10;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg012Icd10Service {
	void save(Zg012Icd10 arg0);

	void delete(String arg0);

	void update(Zg012Icd10 arg0);

	Zg012Icd10 get(String arg0);

	MyPage<Zg012Icd10> a(Zg012Icd10 arg0);

	List<Zg012Icd10> getAll();
}