package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg024ImpOpe;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg024ImpOpeService {
	void save(Zg024ImpOpe arg0);

	void delete(String arg0);

	void update(Zg024ImpOpe arg0);

	Zg024ImpOpe get(String arg0);

	MyPage<Zg024ImpOpe> a(Zg024ImpOpe arg0);

	List<Zg024ImpOpe> getAll();
}