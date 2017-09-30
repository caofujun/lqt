package com.nis.outbreak.service;

import com.nis.comm.entity.MyPage;
import com.nis.outbreak.entity.By007Data;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface By007DataService {
	void save(By007Data arg0);

	void delete();

	void update(By007Data arg0);

	By007Data get();

	MyPage<By007Data> a(By007Data arg0);

	List<By007Data> getAll();
}