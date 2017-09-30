package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn008Jlybz;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn008JlybzService {
	void save(Xn008Jlybz arg0);

	void update(Xn008Jlybz arg0);

	MyPage<Xn008Jlybz> a(Xn008Jlybz arg0);

	List<Xn008Jlybz> getAll();

	List<Xn008Jlybz> findJlybzInfo();
}