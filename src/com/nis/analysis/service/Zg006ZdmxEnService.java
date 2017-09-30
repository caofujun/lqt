package com.nis.analysis.service;

import com.nis.analysis.entity.Zg006ZdmxEn;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg006ZdmxEnService {
	void save(Zg006ZdmxEn arg0);

	void delete(String arg0);

	void update(Zg006ZdmxEn arg0);

	Zg006ZdmxEn get(String arg0);

	MyPage<Zg006ZdmxEn> a(Zg006ZdmxEn arg0);

	List<Zg006ZdmxEn> getAll();
}