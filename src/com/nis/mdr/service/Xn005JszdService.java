package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn005Jszd;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn005JszdService {
	void save(Xn005Jszd arg0);

	void delete(String arg0);

	void update(Xn005Jszd arg0);

	Xn005Jszd get(String arg0);

	MyPage<Xn005Jszd> a(Xn005Jszd arg0);

	List<Xn005Jszd> getAll();
}