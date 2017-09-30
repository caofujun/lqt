package com.nis.pdca.service;

import com.nis.comm.entity.MyPage;
import com.nis.pdca.entity.ZlPdcaFlow;
import com.nis.pdca.entity.ZlPdcaPlans;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ZlPdcaFlowService {
	void save(ZlPdcaFlow arg0);

	void delete(String arg0);

	void update(ZlPdcaFlow arg0);

	ZlPdcaFlow get(String arg0);

	MyPage<ZlPdcaFlow> a(ZlPdcaFlow arg0);

	List<ZlPdcaFlow> getAll(ZlPdcaFlow arg0);

	void a(ZlPdcaPlans arg0);

	List<ZlPdcaFlow> findZlPdcaFlowList(ZlPdcaFlow arg0);
}