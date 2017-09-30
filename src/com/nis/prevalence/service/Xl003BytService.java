package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl003Byt;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl003BytService {
	void save(Xl003Byt arg0);

	void delete(String arg0);

	void update(Xl003Byt arg0);

	Xl003Byt get(String arg0);

	MyPage<Xl003Byt> a(Xl003Byt arg0);

	List<Xl003Byt> getAll();

	void delXl003Byt(List<String> arg0, String arg1);

	void deleteByBrid(String arg0);
}