package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk005Blxx;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk005BlxxService {
	void save(CtgBk005Blxx arg0);

	void delete(String arg0);

	void update(CtgBk005Blxx arg0);

	CtgBk005Blxx get(String arg0);

	List<CtgBk005Blxx> getByMastertid(String arg0);

	MyPage<CtgBk005Blxx> a(CtgBk005Blxx arg0);

	List<CtgBk005Blxx> getAll();
}