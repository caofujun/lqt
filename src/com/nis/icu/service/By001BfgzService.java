package com.nis.icu.service;

import com.nis.comm.entity.MyPage;
import com.nis.icu.entity.By001Bfgz;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface By001BfgzService {
	void save(By001Bfgz arg0);

	void delete(String arg0);

	void update(By001Bfgz arg0);

	By001Bfgz get(String arg0);

	MyPage<By001Bfgz> a(By001Bfgz arg0);

	List<By001Bfgz> getAll();
}