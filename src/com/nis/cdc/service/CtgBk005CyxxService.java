package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk005Cyxx;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk005CyxxService {
	void save(CtgBk005Cyxx arg0);

	void delete(String arg0);

	void update(CtgBk005Cyxx arg0);

	CtgBk005Cyxx get(String arg0);

	MyPage<CtgBk005Cyxx> a(CtgBk005Cyxx arg0);

	List<CtgBk005Cyxx> getAll();

	List<CtgBk005Cyxx> getByMastertid(String arg0);
}