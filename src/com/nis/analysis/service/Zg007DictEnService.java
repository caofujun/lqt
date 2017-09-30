package com.nis.analysis.service;

import com.nis.analysis.entity.Zg007DictEn;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg007DictEnService {
	void save(Zg007DictEn arg0);

	void delete(String arg0);

	void update(Zg007DictEn arg0);

	Zg007DictEn get(String arg0);

	MyPage<Zg007DictEn> a(Zg007DictEn arg0);

	List<Zg007DictEn> getAll();
}