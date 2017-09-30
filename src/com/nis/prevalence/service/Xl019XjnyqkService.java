package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl019Xjnyqk;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl019XjnyqkService {
	void save(Xl019Xjnyqk arg0);

	void delete(String arg0);

	void update(Xl019Xjnyqk arg0);

	Xl019Xjnyqk get(String arg0);

	MyPage<Xl019Xjnyqk> a(Xl019Xjnyqk arg0);

	List<Xl019Xjnyqk> getAll();
}