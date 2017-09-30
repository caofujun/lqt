package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl005Kjywzl;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl005KjywzlService {
	void save(Xl005Kjywzl arg0);

	void delete(String arg0);

	void update(Xl005Kjywzl arg0);

	Xl005Kjywzl get(String arg0);

	MyPage<Xl005Kjywzl> a(Xl005Kjywzl arg0);

	List<Xl005Kjywzl> getAll();
}