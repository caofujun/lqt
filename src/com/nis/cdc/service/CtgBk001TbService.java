package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk001Tb;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001TbService {
	void save(CtgBk001Tb arg0);

	void delete(String arg0);

	void update(CtgBk001Tb arg0);

	CtgBk001Tb get(String arg0);

	MyPage<CtgBk001Tb> a(CtgBk001Tb arg0);

	List<CtgBk001Tb> getAll();

	boolean u(String arg0);
}