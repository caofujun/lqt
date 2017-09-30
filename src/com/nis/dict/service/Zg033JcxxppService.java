package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.Zg033Jcxxpp;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg033JcxxppService {
	void save(Zg033Jcxxpp arg0);

	void delete(String arg0);

	void update(Zg033Jcxxpp arg0);

	Zg033Jcxxpp get(String arg0);

	MyPage<Zg033Jcxxpp> a(Zg033Jcxxpp arg0);

	List<Zg033Jcxxpp> getAll();

	List<Zg033Jcxxpp> findBySjId(String arg0);
}