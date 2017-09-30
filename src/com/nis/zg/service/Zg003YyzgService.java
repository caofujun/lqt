package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg003Yyzg;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg003YyzgService {
	void save(Zg003Yyzg arg0);

	void delete(String arg0);

	void update(Zg003Yyzg arg0);

	Zg003Yyzg get(String arg0);

	MyPage<Zg003Yyzg> a(Zg003Yyzg arg0);

	List<Zg003Yyzg> getAll();

	List<Zg003Yyzg> getByName(String arg0, String arg1);
}