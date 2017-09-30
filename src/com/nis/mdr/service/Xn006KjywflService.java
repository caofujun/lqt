package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn006Kjywfl;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn006KjywflService {
	void save(Xn006Kjywfl arg0);

	void delete(String arg0);

	void update(Xn006Kjywfl arg0);

	Xn006Kjywfl get(String arg0);

	MyPage<Xn006Kjywfl> a(Xn006Kjywfl arg0);

	List<Xn006Kjywfl> getAll();
}