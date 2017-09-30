package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn002Byt;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn002BytService {
	void save(Xn002Byt arg0);

	void delete(String arg0);

	void update(Xn002Byt arg0);

	Xn002Byt get(String arg0);

	MyPage<Xn002Byt> a(Xn002Byt arg0);

	List<Xn002Byt> getAll();

	MyPage<Xn002Byt> b(Xn002Byt arg0);

	MyPage<Xn002Byt> c(Xn002Byt arg0);

	List<HashMap<String, Object>> getGlsfl();

	Xn002Byt findXn002BytEdit(Xn002Byt arg0);
}