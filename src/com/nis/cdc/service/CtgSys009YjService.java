package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys009YjService {
	void save(CtgSys009Yj arg0);

	void delete(String arg0);

	void update(CtgSys009Yj arg0);

	CtgSys009Yj get(String arg0);

	MyPage<CtgSys009Yj> a(CtgSys009Yj arg0);

	List<CtgSys009Yj> getAll();

	List<CtgSys009Yj> isExit(CtgSys009Yj arg0);
}