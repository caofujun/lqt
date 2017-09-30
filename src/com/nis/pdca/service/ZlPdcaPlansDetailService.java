package com.nis.pdca.service;

import com.nis.comm.entity.MyPage;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ZlPdcaPlansDetailService {
	void save(ZlPdcaPlansDetail arg0);

	void delete(String arg0);

	void update(ZlPdcaPlansDetail arg0);

	ZlPdcaPlansDetail get(String arg0);

	MyPage<ZlPdcaPlansDetail> a(ZlPdcaPlansDetail arg0);

	List<ZlPdcaPlansDetail> getAll();

	List<ZlPdcaPlansDetail> getByPuid(String arg0);

	void deleteByPuid(String arg0);
}