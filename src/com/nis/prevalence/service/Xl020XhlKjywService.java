package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl020XhlKjyw;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl020XhlKjywService {
	void save(Xl020XhlKjyw arg0);

	void delete(String arg0);

	void update(Xl020XhlKjyw arg0);

	Xl020XhlKjyw get(String arg0);

	MyPage<Xl020XhlKjyw> a(Xl020XhlKjyw arg0);

	List<Xl020XhlKjyw> getAll();

	List<Xl020XhlKjyw> b(Xl020XhlKjyw arg0);
}