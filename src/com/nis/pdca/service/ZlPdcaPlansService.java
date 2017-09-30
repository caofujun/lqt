package com.nis.pdca.service;

import com.nis.comm.entity.MyPage;
import com.nis.pdca.entity.ZlPdcaPlans;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ZlPdcaPlansService {
	void save(ZlPdcaPlans arg0);

	void delete(String arg0);

	void update(ZlPdcaPlans arg0);

	ZlPdcaPlans get(String arg0);

	MyPage<ZlPdcaPlans> b(ZlPdcaPlans arg0);

	List<ZlPdcaPlans> getAll();

	List<ZlPdcaPlans> findByPzId(String arg0);
}