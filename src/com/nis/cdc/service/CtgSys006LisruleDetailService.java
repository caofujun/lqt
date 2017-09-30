package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys006LisruleDetail;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys006LisruleDetailService {
	void save(CtgSys006LisruleDetail arg0);

	void delete(Long arg0);

	void update(CtgSys006LisruleDetail arg0);

	List<CtgSys006LisruleDetail> get(Long arg0);

	MyPage<CtgSys006LisruleDetail> a(CtgSys006LisruleDetail arg0);

	List<CtgSys006LisruleDetail> getAll();

	List<CtgSys006LisruleDetail> getByOrderNo(String arg0);
}