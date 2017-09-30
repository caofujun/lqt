package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl011DicPatho;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl011DicPathoService {
	void save(Xl011DicPatho arg0);

	void delete(String arg0);

	void update(Xl011DicPatho arg0);

	Xl011DicPatho get(String arg0);

	MyPage<Xl011DicPatho> a(Xl011DicPatho arg0);

	List<Xl011DicPatho> getAll();

	List<Xl011DicPatho> b(Xl011DicPatho arg0);
}