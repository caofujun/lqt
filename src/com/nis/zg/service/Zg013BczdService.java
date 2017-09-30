package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg013Bczd;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg013BczdService {
	void save(Zg013Bczd arg0);

	void delete(String arg0);

	void update(Zg013Bczd arg0);

	Zg013Bczd get(String arg0);

	MyPage<Zg013Bczd> a(Zg013Bczd arg0);

	List<Zg013Bczd> getAll();
}