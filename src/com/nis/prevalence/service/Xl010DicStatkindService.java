package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl010DicStatkind;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl010DicStatkindService {
	void save(Xl010DicStatkind arg0);

	void delete(String arg0);

	void update(Xl010DicStatkind arg0);

	Xl010DicStatkind get(String arg0);

	MyPage<Xl010DicStatkind> a(Xl010DicStatkind arg0);

	List<Xl010DicStatkind> getAll();

	List<Xl010DicStatkind> b(Xl010DicStatkind arg0);
}