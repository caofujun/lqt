package com.nis.cdc.service;

import com.nis.cdc.entity.CtgBk001Crbafpcard;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001CrbafpcardService {
	void save(CtgBk001Crbafpcard arg0);

	void delete(String arg0);

	void update(CtgBk001Crbafpcard arg0);

	CtgBk001Crbafpcard get(String arg0);

	MyPage<CtgBk001Crbafpcard> a(CtgBk001Crbafpcard arg0);

	List<CtgBk001Crbafpcard> getAll();
}