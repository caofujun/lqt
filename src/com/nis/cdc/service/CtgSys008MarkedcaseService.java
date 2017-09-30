package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys008Markedcase;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys008MarkedcaseService {
	void save(CtgSys008Markedcase arg0);

	void u(List<CtgSys008Markedcase> arg0);

	void delete(String arg0);

	void update(CtgSys008Markedcase arg0);

	CtgSys008Markedcase get(String arg0);

	MyPage<CtgSys008Markedcase> a(CtgSys008Markedcase arg0);

	List<CtgSys008Markedcase> getAll();
}