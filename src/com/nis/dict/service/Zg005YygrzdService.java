package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.Zg005Yygrzd;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg005YygrzdService {
	void save(Zg005Yygrzd arg0);

	void delete(String arg0);

	void update(Zg005Yygrzd arg0);

	void updFlag(String arg0, Integer arg1, Date arg2);

	Zg005Yygrzd get(String arg0);

	MyPage<Zg005Yygrzd> a(Zg005Yygrzd arg0);

	List<Zg005Yygrzd> b(Zg005Yygrzd arg0);

	List<Zg005Yygrzd> getAll();

	int findNumByInfectCode(String arg0, String arg1);
}