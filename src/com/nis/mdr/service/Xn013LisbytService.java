package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn013Lisbyt;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Xn013LisbytService {
	void save(Xn013Lisbyt arg0);

	void delete(String arg0);

	void update(Xn013Lisbyt arg0);

	void match();

	Map<String, String> queryMatched();

	Xn013Lisbyt get(String arg0);

	MyPage<Xn013Lisbyt> a(Xn013Lisbyt arg0);

	List<Xn013Lisbyt> getAll();

	MyPage<Xn013Lisbyt> b(Xn013Lisbyt arg0);

	void ab();
}