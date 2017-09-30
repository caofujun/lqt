package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys005LisruleMaster;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys005LisruleMasterService {
	void save(CtgSys005LisruleMaster arg0);

	void delete(Long arg0);

	void update(CtgSys005LisruleMaster arg0);

	CtgSys005LisruleMaster get(Long arg0);

	MyPage<CtgSys005LisruleMaster> a(CtgSys005LisruleMaster arg0);

	List<CtgSys005LisruleMaster> getAll();
}