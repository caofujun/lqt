package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys005Dictdept1;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys005Dictdept1Service {
	void save(CtgSys005Dictdept1 arg0);

	void delete(String arg0);

	void update(CtgSys005Dictdept1 arg0);

	CtgSys005Dictdept1 get(String arg0);

	MyPage<CtgSys005Dictdept1> a(CtgSys005Dictdept1 arg0);

	List<CtgSys005Dictdept1> getAll();
}