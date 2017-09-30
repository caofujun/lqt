package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk001Crbhbvcard;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001CrbhbvcardService {
	void save(CtgBk001Crbhbvcard arg0);

	void delete(String arg0);

	void update(CtgBk001Crbhbvcard arg0);

	CtgBk001Crbhbvcard get(String arg0);

	CtgBk001Crbhbvcard getByMasterId(String arg0);

	MyPage<CtgBk001Crbhbvcard> a(CtgBk001Crbhbvcard arg0);

	List<CtgBk001Crbhbvcard> getAll();

	boolean s(String arg0);
}