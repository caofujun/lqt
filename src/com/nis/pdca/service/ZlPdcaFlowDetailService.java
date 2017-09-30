package com.nis.pdca.service;

import com.nis.comm.entity.MyPage;
import com.nis.pdca.entity.ZlPdcaFlowDetail;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ZlPdcaFlowDetailService {
	void save(ZlPdcaFlowDetail arg0);

	void delete(String arg0);

	void update(ZlPdcaFlowDetail arg0);

	ZlPdcaFlowDetail get(String arg0);

	MyPage<ZlPdcaFlowDetail> a(ZlPdcaFlowDetail arg0);

	List<ZlPdcaFlowDetail> getAll();

	List<ZlPdcaFlowDetail> getByFuid(String arg0);

	List<ZlPdcaFlowDetail> findZlPdcaFlowDetailList(ZlPdcaFlowDetail arg0);
}