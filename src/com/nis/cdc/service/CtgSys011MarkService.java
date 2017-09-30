package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys011Mark;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys011MarkService {
	void save(CtgSys011Mark arg0);

	void delete(String arg0);

	void deleteByOtherField(String arg0, String arg1);

	void update(CtgSys011Mark arg0);

	CtgSys011Mark get(String arg0);

	MyPage<CtgSys011Mark> a(CtgSys011Mark arg0);

	List<CtgSys011Mark> getAll();
}