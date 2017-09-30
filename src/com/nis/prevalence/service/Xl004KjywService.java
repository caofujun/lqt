package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl004Kjyw;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl004KjywService {
	void save(Xl004Kjyw arg0);

	void delete(String arg0);

	void update(Xl004Kjyw arg0);

	Xl004Kjyw get(String arg0);

	MyPage<Xl004Kjyw> a(Xl004Kjyw arg0);

	List<Xl004Kjyw> getAll();

	void delXl004Kjyw(List<String> arg0, String arg1);

	void deleteByBrid(String arg0);
}