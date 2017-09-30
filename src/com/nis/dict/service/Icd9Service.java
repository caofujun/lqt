package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.Icd9;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Icd9Service {
	void save(Icd9 arg0);

	void delete(String arg0);

	void update(Icd9 arg0);

	Icd9 get(String arg0);

	MyPage<Icd9> a(Icd9 arg0);

	List<Icd9> bt(String arg0);

	List<Icd9> getAll();
}