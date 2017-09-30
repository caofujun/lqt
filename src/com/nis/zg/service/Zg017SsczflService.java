package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg017Ssczfl;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg017SsczflService {
	void save(Zg017Ssczfl arg0);

	void delete(String arg0);

	void update(Zg017Ssczfl arg0);

	Zg017Ssczfl get(String arg0);

	MyPage<Zg017Ssczfl> a(Zg017Ssczfl arg0);

	List<Zg017Ssczfl> getAll();
}