package com.nis.prevalence.service;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.entity.Xl002Grxx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xl002GrxxService {
	void save(Xl002Grxx arg0);

	void delete(String arg0);

	void update(Xl002Grxx arg0);

	void updateSpecified(Xl002Grxx arg0, List<String> arg1);

	Xl002Grxx get(String arg0);

	MyPage<Xl002Grxx> a(Xl002Grxx arg0);

	List<Xl002Grxx> getAll();

	List<Xl002Grxx> queryByBrid(String arg0);

	void delXl002Grxx(List<String> arg0, String arg1);

	void deleteByBrid(String arg0);
}