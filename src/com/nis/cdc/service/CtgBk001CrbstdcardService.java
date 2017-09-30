package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk001Crbstdcard;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001CrbstdcardService {
	void save(CtgBk001Crbstdcard arg0);

	void delete(String arg0);

	void update(CtgBk001Crbstdcard arg0);

	CtgBk001Crbstdcard get(String arg0);

	boolean t(String arg0);

	MyPage<CtgBk001Crbstdcard> a(CtgBk001Crbstdcard arg0);

	List<CtgBk001Crbstdcard> getAll();
}