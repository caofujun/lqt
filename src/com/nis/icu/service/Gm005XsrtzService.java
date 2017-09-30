package com.nis.icu.service;

import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.icu.entity.Gm005Xsrtz;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gm005XsrtzService {
	void save(Gm005Xsrtz arg0);

	void update(Gm005Xsrtz arg0);

	Gm005Xsrtz get(String arg0);

	MyPage<Gm005Xsrtz> a(Gm005Xsrtz arg0);

	List<Gm005Xsrtz> getAll();

	void a(Gm005Xsrtz arg0, LoginUser arg1);
}